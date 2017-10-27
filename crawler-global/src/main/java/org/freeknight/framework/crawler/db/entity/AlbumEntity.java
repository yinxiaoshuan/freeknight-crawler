
package org.freeknight.framework.crawler.db.entity;

import java.util.Date;

public class AlbumEntity
{

	String	album_id;

	String	album_mid;

	String	album_name;

	int			site	= 1;

	String	url;

	String	s_id;

	String	genre;

	String	language;

	String	lssue_comp;

	String	type;

	Date		lssue_time;

	public String getAlbum_id ( )
	{
		return album_id;
	}

	public void setAlbum_id (
			final String album_id )
	{
		this.album_id = album_id;
	}

	public String getAlbum_mid ( )
	{
		return album_mid;
	}

	public void setAlbum_mid (
			final String album_mid )
	{
		this.album_mid = album_mid;
	}

	public String getAlbum_name ( )
	{
		return album_name;
	}

	public void setAlbum_name (
			final String album_name )
	{
		this.album_name = album_name;
	}

	public int getSite ( )
	{
		return site;
	}

	public void setSite (
			final int site )
	{
		this.site = site;
	}

	public String getS_id ( )
	{
		return s_id;
	}

	public void setS_id (
			final String s_id )
	{
		this.s_id = s_id;
	}

	public String getGenre ( )
	{
		return genre;
	}

	public void setGenre (
			final String genre )
	{
		this.genre = genre;
	}

	public String getLanguage ( )
	{
		return language;
	}

	public void setLanguage (
			final String language )
	{
		this.language = language;
	}

	public String getLssue_comp ( )
	{
		return lssue_comp;
	}

	public void setLssue_comp (
			final String lssue_comp )
	{
		this.lssue_comp = lssue_comp;
	}

	public String getType ( )
	{
		return type;
	}

	public void setType (
			final String type )
	{
		this.type = type;
	}

	public java.sql.Date getLssue_time ( )
	{
		return new java.sql.Date ( lssue_time.getTime ( ) );
	}

	public void setLssue_time (
			final Date lssue_time )
	{
		this.lssue_time = lssue_time;
	}
}
