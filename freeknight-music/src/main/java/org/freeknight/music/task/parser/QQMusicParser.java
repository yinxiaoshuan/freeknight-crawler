
package org.freeknight.music.task.parser;

import java.util.ArrayList;
import java.util.List;

import org.freeknight.music.daemon.DaemonWorkerEngine;
import org.freeknight.music.task.model.MusicTaskOutModel;
import org.freeknight.music.toolkit.GsonConverter;

public class QQMusicParser
		implements MusicParser
{

	@Override
	public List< MusicTaskOutModel > parse (
			String respBody )
	{
		QQMusicRespModel respModel = GsonConverter.toBean ( respBody, QQMusicRespModel.class );
		if ( respModel == null || respModel.getCode ( ) != 0 )
		{
			return null;
		}

		QQMusicDataModel musicResources = respModel.getData ( );
		if ( musicResources == null )
		{
			return null;
		}

		List< QQMusicResource > musics = musicResources.getList ( );
		if ( musics == null || musics.isEmpty ( ) )
		{
			return null;
		}

		List< MusicTaskOutModel > outModels = new ArrayList< MusicTaskOutModel > ( );
		for ( QQMusicResource music : musics )
		{
			QQMusicModel song = music.getMusicData ( );
			MusicTaskOutModel outModel = new MusicTaskOutModel ( );

			outModel.setMusic_id ( String.valueOf ( song.getSongId ( ) ) );
			outModel.setMusic_mid ( song.getSongmid ( ) );
			outModel.setMusic_name ( song.getSongname ( ) );
			outModel.setSite ( DaemonWorkerEngine.QQ.getSite ( ) );

			outModel.setAlbum_id ( String.valueOf ( song.getAlbumid ( ) ) );
			outModel.setAlbum_mid ( song.getAlbummid ( ) );

			List< SingerOutModel > singers = song.getSinger ( );
			if ( singers != null && singers.size ( ) > 0 )
			{
				SingerOutModel singer = singers.get ( 0 );
				outModel.setSinger_id ( String.valueOf ( singer.getId ( ) ) );
				outModel.setSinger_mid ( singer.getMid ( ) );
			}

			outModels.add ( outModel );
		}

		return outModels;
	}
}
