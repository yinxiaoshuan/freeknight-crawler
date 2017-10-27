
package org.freeknight.music.task.parser;

import java.util.List;

class QQMusicModel
{

	private long										albumid;

	private String									albummid;

	private long										songid;

	private String									songmid;

	private String									songname;

	private List< SingerOutModel >	singer;

	public long getAlbumid ( )
	{
		return albumid;
	}

	public void setAlbumid (
			long albumid )
	{
		this.albumid = albumid;
	}

	public String getAlbummid ( )
	{
		return albummid;
	}

	public void setAlbummid (
			String albummid )
	{
		this.albummid = albummid;
	}

	public long getSongId ( )
	{
		return songid;
	}

	public void setSongId (
			long songId )
	{
		this.songid = songId;
	}

	public String getSongmid ( )
	{
		return songmid;
	}

	public void setSongmid (
			String songmid )
	{
		this.songmid = songmid;
	}

	public String getSongname ( )
	{
		return songname;
	}

	public void setSongname (
			String songname )
	{
		this.songname = songname;
	}

	public List< SingerOutModel > getSinger ( )
	{
		return singer;
	}

	public void setSinger (
			List< SingerOutModel > singer )
	{
		this.singer = singer;
	}

	@Override
	public String toString ( )
	{
		StringBuilder builder = new StringBuilder ( );
		builder.append ( "QQMusicModel [albumid=" );
		builder.append ( albumid );
		builder.append ( ", albummid=" );
		builder.append ( albummid );
		builder.append ( ", songId=" );
		builder.append ( songid );
		builder.append ( ", songmid=" );
		builder.append ( songmid );
		builder.append ( ", songname=" );
		builder.append ( songname );
		builder.append ( "]" );
		return builder.toString ( );
	}

}
