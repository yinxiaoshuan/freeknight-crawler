
package org.freeknight.music.task.model;

public class MusicTaskOutModel
{

	private String	music_id;

	private String	music_mid;

	private String	music_name;

	private String	album_id;

	private String	album_mid;

	private String	singer_id;

	private String	singer_mid;

	private int			site;

	public String getMusic_id ( )
	{
		return music_id;
	}

	public void setMusic_id (
			String music_id )
	{
		this.music_id = music_id;
	}

	public String getMusic_mid ( )
	{
		return music_mid;
	}

	public void setMusic_mid (
			String music_mid )
	{
		this.music_mid = music_mid;
	}

	public String getMusic_name ( )
	{
		return music_name;
	}

	public void setMusic_name (
			String music_name )
	{
		this.music_name = music_name;
	}

	public String getAlbum_id ( )
	{
		return album_id;
	}

	public void setAlbum_id (
			String album_id )
	{
		this.album_id = album_id;
	}

	public String getAlbum_mid ( )
	{
		return album_mid;
	}

	public void setAlbum_mid (
			String album_mid )
	{
		this.album_mid = album_mid;
	}

	public String getSinger_id ( )
	{
		return singer_id;
	}

	public void setSinger_id (
			String singer_id )
	{
		this.singer_id = singer_id;
	}

	public String getSinger_mid ( )
	{
		return singer_mid;
	}

	public void setSinger_mid (
			String singer_mid )
	{
		this.singer_mid = singer_mid;
	}

	public int getSite ( )
	{
		return site;
	}

	public void setSite (
			int site )
	{
		this.site = site;
	}

	@Override
	public String toString ( )
	{
		StringBuilder builder = new StringBuilder ( );
		builder.append ( "MusicTaskOutModel [music_id=" );
		builder.append ( music_id );
		builder.append ( ", music_mid=" );
		builder.append ( music_mid );
		builder.append ( ", music_name=" );
		builder.append ( music_name );
		builder.append ( ", album_id=" );
		builder.append ( album_id );
		builder.append ( ", album_mid=" );
		builder.append ( album_mid );
		builder.append ( ", singer_id=" );
		builder.append ( singer_id );
		builder.append ( ", singer_mid=" );
		builder.append ( singer_mid );
		builder.append ( ", site=" );
		builder.append ( site );
		builder.append ( "]" );
		return builder.toString ( );
	}
}
