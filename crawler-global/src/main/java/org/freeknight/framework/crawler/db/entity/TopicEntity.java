
package org.freeknight.framework.crawler.db.entity;

import java.sql.Timestamp;

public class TopicEntity
{
	long			forumId;

	String		topicId;

	String		title;

	String		authorId;

	String		authorName;

	Timestamp	issueTime;

	int				replyNum;

	int				readNum;

	int				hotVal;

	int				recommend	= 0;

	public long getForumId ( )
	{
		return forumId;
	}

	public void setForumId (
			final long forumId )
	{
		this.forumId = forumId;
	}

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
		this.title = title;
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

	public String getAuthorName ( )
	{
		return authorName;
	}

	public void setAuthorName (
			final String authorName )
	{
		this.authorName = authorName;
	}

	public Timestamp getIssueTime ( )
	{
		return issueTime;
	}

	public void setIssueTime (
			final Timestamp issueTime )
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

	public int getHotVal ( )
	{
		return hotVal;
	}

	public void setHotVal (
			final int hotVal )
	{
		this.hotVal = hotVal;
	}

	public int getRecommend ( )
	{
		return recommend;
	}

	public void setRecommend (
			final int recommend )
	{
		this.recommend = recommend;
	}
}
