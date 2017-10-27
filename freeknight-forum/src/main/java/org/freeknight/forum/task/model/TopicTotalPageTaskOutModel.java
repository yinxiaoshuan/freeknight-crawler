
package org.freeknight.forum.task.model;

public class TopicTotalPageTaskOutModel
{

	String	forumName;

	String	target;

	int			totalPage;

	public TopicTotalPageTaskOutModel (
			final String forumName,
			final String target,
			final int totalPage ) {
		this.forumName = forumName;
		this.target = target;
		this.totalPage = totalPage;
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
