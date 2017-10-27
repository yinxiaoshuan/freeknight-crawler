
package org.freeknight.forum.task.model;

public class TopicTotalPageTaskInModel
{

	String	forumName;

	String	target;

	int			totalPage;

	public TopicTotalPageTaskInModel (
			final String forumName,
			final String target ) {
		this ( forumName, target, 1 );
	}

	public TopicTotalPageTaskInModel (
			final String forumName,
			final String target,
			final int totalPage ) {
		this.forumName = forumName;
		this.target = target;
		this.totalPage = totalPage < 1 ? 1 : totalPage;
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
