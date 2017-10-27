
package org.freeknight.forum.task.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopicTaskOutModel
{
	String	topicId;

	String	title;

	String	authorId;

	String	author;

	String	issueTime;

	int			replyNum;

	int			readNum;

	public String getTopicId ( )
	{
		return topicId;
	}

	public void setTopicId (
			final String topicId )
	{
		this.topicId = topicId;
	}

	public String getTitle ( )
	{
		return title;
	}

	public void setTitle (
			final String title )
	{
		this.title = title != null ? title.trim ( ) : title;
	}

	public String getAuthorId ( )
	{
		return authorId;
	}

	public void setAuthorId (
			final String authorId )
	{
		this.authorId = authorId;
	}

	public String getAuthor ( )
	{
		return author;
	}

	public void setAuthor (
			final String author )
	{
		this.author = author;
	}

	public Timestamp issueTime ( )
	{
		DateFormat df = new SimpleDateFormat ( "yyyy-MM-dd" );
		if ( issueTime.length ( ) > 12 )
		{
			df = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
		}

		try
		{
			final Date issue = df.parse ( issueTime );
			return new Timestamp ( issue.getTime ( ) );
		}
		catch ( final ParseException e )
		{
			e.printStackTrace ( );
		}
		return new Timestamp ( System.currentTimeMillis ( ) );
	}

	public String getIssueTime ( )
	{
		return issueTime;
	}

	public void setIssueTime (
			final String issueTime )
	{
		this.issueTime = issueTime;
	}

	public int getReplyNum ( )
	{
		return replyNum;
	}

	public void setReplyNum (
			final int replyNum )
	{
		this.replyNum = replyNum;
	}

	public int getReadNum ( )
	{
		return readNum;
	}

	public void setReadNum (
			final int readNum )
	{
		this.readNum = readNum;
	}
}
