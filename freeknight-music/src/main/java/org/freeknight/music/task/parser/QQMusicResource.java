
package org.freeknight.music.task.parser;

class QQMusicResource
{

	private String				Fupload_time;

	private QQMusicModel	musicData;

	public String getFupload_time ( )
	{
		return Fupload_time;
	}

	public void setFupload_time (
			String fupload_time )
	{
		Fupload_time = fupload_time;
	}

	public QQMusicModel getMusicData ( )
	{
		return musicData;
	}

	public void setMusicData (
			QQMusicModel musicData )
	{
		this.musicData = musicData;
	}
}
