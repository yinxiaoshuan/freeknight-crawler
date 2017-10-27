
package org.freeknight.music.task.parser;

import java.util.List;

class QQMusicDataModel
{

	private String									singer_id;

	private String									singer_mid;

	private String									singer_name;

	private int											total;

	private List< QQMusicResource >	list;

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

	public String getSinger_name ( )
	{
		return singer_name;
	}

	public void setSinger_name (
			String singer_name )
	{
		this.singer_name = singer_name;
	}

	public int getTotal ( )
	{
		return total;
	}

	public void setTotal (
			int total )
	{
		this.total = total;
	}

	public List< QQMusicResource > getList ( )
	{
		return list;
	}

	public void setList (
			List< QQMusicResource > list )
	{
		this.list = list;
	}
}
