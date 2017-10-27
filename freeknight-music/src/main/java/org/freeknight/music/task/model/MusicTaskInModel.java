
package org.freeknight.music.task.model;

public class MusicTaskInModel
{

	int			singerId;

	String	singerMid;

	String	singerName;

	public MusicTaskInModel (
			int singerId,
			String singerMid,
			String singerName ) {
		this.singerId = singerId;
		this.singerMid = singerMid;
		this.singerName = singerName;
	}

	public int getSingerId ( )
	{
		return singerId;
	}

	public String getSingerMid ( )
	{
		return singerMid;
	}

	public String getSingerName ( )
	{
		return singerName;
	}

	@Override
	public String toString ( )
	{
		StringBuilder builder = new StringBuilder ( );
		builder.append ( "MusicTaskInModel [singerId=" );
		builder.append ( singerId );
		builder.append ( ", singerMid=" );
		builder.append ( singerMid );
		builder.append ( ", singerName=" );
		builder.append ( singerName );
		builder.append ( "]" );
		return builder.toString ( );
	}
}
