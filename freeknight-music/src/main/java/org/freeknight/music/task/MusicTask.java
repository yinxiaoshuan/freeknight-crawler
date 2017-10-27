
package org.freeknight.music.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.freeknight.framework.crawler.db.MusicMapper;
import org.freeknight.framework.crawler.db.entity.MusicEntity;
import org.freeknight.music.task.fetch.MusicFetcher;
import org.freeknight.music.task.model.MusicTaskInModel;
import org.freeknight.music.task.model.MusicTaskOutModel;
import org.freeknight.music.task.parser.MusicParser;
import org.freeknight.music.task.resource.QQMusicPageNumResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicTask
		implements Runnable
{

	Logger										logger	= LoggerFactory.getLogger ( MusicTask.class );

	private MusicTaskInModel	taskModel;

	MusicFetcher							musicFetcher;

	MusicParser								musicParser;

	public MusicTask (
			MusicTaskInModel taskModel,
			MusicParser musicParser ) {
		this.taskModel = taskModel;
		this.musicFetcher = new MusicFetcher ( );
		this.musicParser = musicParser;
	}

	@Override
	public void run ( )
	{
		int totalNum = 0;
		long startTime = System.currentTimeMillis ( );
		for ( int pageNum = 1; pageNum < Integer.MAX_VALUE; pageNum++ )
		{
			String target = QQMusicPageNumResource.getTargetResource ( taskModel.getSingerMid ( ), pageNum );
			String respBody = fetch ( target );

			List< MusicTaskOutModel > outModels = musicParser.parse ( respBody );
			if ( outModels == null )
			{
				break;
			}

			List< MusicEntity > musics = new ArrayList< MusicEntity > ( );
			for ( MusicTaskOutModel outModel : outModels )
			{
				MusicEntity music = new MusicEntity ( );
				music.setAlbum_id ( outModel.getAlbum_id ( ) );
				music.setAlbum_mid ( outModel.getAlbum_mid ( ) );
				music.setMusic_id ( outModel.getMusic_id ( ) );
				music.setMusic_mid ( outModel.getMusic_mid ( ) );
				music.setMusic_name ( outModel.getMusic_name ( ) );
				music.setSinger_id ( outModel.getSinger_id ( ) );
				music.setSinger_mid ( outModel.getSinger_mid ( ) );
				music.setSite ( outModel.getSite ( ) );
				musics.add ( music );
			}
			MusicMapper.insertMusics ( musics );

			totalNum += musics.size ( );
		}
		long endTime = System.currentTimeMillis ( );
		logger.info ( "singer: "
				+ taskModel.getSingerName ( )
				+ "mid: "
				+ taskModel.getSingerMid ( )
				+ ", total: "
				+ totalNum
				+ ", speed: "
				+ ( endTime - startTime )
				+ "ms" );
	}

	private String fetch (
			String target )
	{
		String respBody = "";
		try
		{
			respBody = musicFetcher.doFetch ( target );
		}
		catch ( IOException e )
		{
			logger.error ( "fetch failture, target: " + target, e );
		}
		return respBody;
	}
}
