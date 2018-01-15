
package org.freeknight.music.mysql.entity;

public class SingerEntity
{

	private int			singerId;

	private String	s_name;

	private String	alias;

	private int			site	= 1;

	private String	ss_id;

	private String	sm_id;

	private String	portrait;

	public int getSingerId ( )
	{
		return singerId;
	}

	public void setSingerId (
			int singerId )
	{
		this.singerId = singerId;
	}

	public String getS_name ( )
	{
		return s_name == null ? "" : ( s_name.length ( ) > 50 ? s_name.substring ( 0, 50 ) : s_name );
	}

	public void setS_name (
			final String s_name )
	{
		this.s_name = s_name;
	}

	public String getAlias ( )
	{
		return alias == null ? "" : ( alias.length ( ) > 50 ? alias.substring ( 0, 50 ) : alias );
	}

	public void setAlias (
			final String alias )
	{
		this.alias = alias;
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

	public String getSs_id ( )
	{
		return ss_id;
	}

	public void setSs_id (
			final String ss_id )
	{
		this.ss_id = ss_id;
	}

	public String getSm_id ( )
	{
		return sm_id;
	}

	public void setSm_id (
			final String sm_id )
	{
		this.sm_id = sm_id;
	}

	public String getPortrait ( )
	{
		return portrait;
	}

	public void setPortrait (
			final String portrait )
	{
		this.portrait = portrait;
	}

}
