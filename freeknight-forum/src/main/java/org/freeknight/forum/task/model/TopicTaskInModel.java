
package org.freeknight.forum.task.model;

public class TopicTaskInModel
{
	long		forumId;

	String	forumSrcId;

	String	forumName;

	String	target;

	int			totalPage;

	public TopicTaskInModel (
			final String forumName,
			final String target ) {
		this ( forumName, target, 1 );
	}

	public TopicTaskInModel (
			final String forumName,
			final String target,
			final int totalPage ) {
		this.forumName = forumName;
		this.target = target;
		this.totalPage = totalPage < 1 ? 1 : totalPage;
	}

	public long getForumId ( )
	{
		return forumId;
	}

	public String getForumSrcId ( )
	{
		return forumSrcId;
	}

	public String getForumName ( )
	{
		return forumName;
	}

	public String getTarget ( )
	{
		return target;
	}

	public int getTotalPage ( )
	{
		return totalPage;
	}
}
