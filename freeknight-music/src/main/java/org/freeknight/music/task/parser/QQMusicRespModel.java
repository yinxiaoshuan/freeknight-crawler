
package org.freeknight.music.task.parser;

class QQMusicRespModel
{

	private int								code;

	private QQMusicDataModel	data;

	public int getCode ( )
	{
		return code;
	}

	public void setCode (
			int code )
	{
		this.code = code;
	}

	public QQMusicDataModel getData ( )
	{
		return data;
	}

	public void setData (
			QQMusicDataModel data )
	{
		this.data = data;
	}
}
