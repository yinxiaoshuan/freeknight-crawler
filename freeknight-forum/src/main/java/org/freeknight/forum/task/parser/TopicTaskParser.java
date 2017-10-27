
package org.freeknight.forum.task.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;

public class TopicTaskParser
		implements Parser< String, Void, List< TopicTaskOutModel > >
{

	final Pattern	TITLE						=
																		Pattern
																				.compile ( "<a class=\"a_topic\"(\\s*)target=\"_blank\"(\\s*)href=\"/bbs/thread(.*)-(.*)-([0-9]+)-1.html(.*)\">(\\s*)(.*)(\\s*)</a>" );

	int						topicIdGroup		= 5;

	int						titleGroup			= 8;

	final Pattern	AUTHOR					=
																		Pattern
																				.compile ( "<a href=\"//i.autohome.com.cn/([0-9]+)\" target=\"_blank\" class=\"linkblack\">(.*)</a><span class=\"tdate\">(.*)</span>" );

	int						authorIdGroup		= 1;

	int						authorNameGroup	= 2;

	int						issueTimeGroup	= 3;

	@Override
	public List< TopicTaskOutModel > doParse (
			final String respBody, final Void n )
	{
		final List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );
		final Matcher titleMatcher = TITLE.matcher ( respBody );
		Matcher authorMatcher = AUTHOR.matcher ( respBody );

		int start = 0;
		boolean exist = false;
		do
		{
			exist = titleMatcher.find ( start );
			if ( !exist )
			{
				break;
			}

			final int end = titleMatcher.end ( titleGroup );
			start = end;

			final TopicTaskOutModel topic = new TopicTaskOutModel ( );
			final String topicId = titleMatcher.group ( topicIdGroup );
			final String title = titleMatcher.group ( titleGroup );
			topic.setTopicId ( topicId );
			topic.setTitle ( title );

			if ( authorMatcher.find ( start ) )
			{
				String authorId = authorMatcher.group ( authorIdGroup );
				String authorName = authorMatcher.group ( authorNameGroup );
				String issueTime = authorMatcher.group ( issueTimeGroup );
				topic.setAuthorId ( authorId );
				topic.setAuthor ( authorName );
				topic.setIssueTime ( issueTime );
			}

			topics.add ( topic );
		}
		while ( exist );

		return topics;
	}
}
