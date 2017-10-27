
package org.freeknight.framework.crawler.db.entity;

public class ForumEntity
{

	private long		fid;

	private String	fsrcid;

	private String	prev;

	private String	fname;

	private String	url;

	private String	site;

	public long getFid ( )
	{
		return fid;
	}

	public void setFid (
			final long fid )
	{
		this.fid = fid;
	}

	public String getFsrcid ( )
	{
		return fsrcid;
	}

	public void setFsrcid (
			final String fsrcid )
	{
		this.fsrcid = fsrcid;
	}

	public String getPrev ( )
	{
		return prev;
	}

	public void setPrev (
			final String prev )
	{
		this.prev = prev;
	}

	public String getFname ( )
	{
		return fname;
	}

	public void setFname (
			final String fname )
	{
		this.fname = fname;
	}

	public String getUrl ( )
	{
		return url;
	}

	public void setUrl (
			final String url )
	{
		this.url = url;
	}

	public String getSite ( )
	{
		return site;
	}

	public void setSite (
			final String site )
	{
		this.site = site;
	}
}
