
package org.freeknight.forum.task.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.framework.math.Digit;

public class B55TopicParser
		implements Parser< String, Long, List< TopicTaskOutModel > >
{

	Pattern	titlePattern		=
															Pattern
																	.compile (
																			"<span id=\"thread_([0-9]+)\">(\\s*)<a href=\"thread-([0-9]+)-1-1.html\"(\\s*)(.*)>(.*)</a></span>",
																			Pattern.CASE_INSENSITIVE );

	int			topicIdGroup		= 3;

	int			titleGroup			= 6;

	Pattern	authorPattern		=
															Pattern
																	.compile ( "<cite>(\\s*)<a target=\"_blank\" href=\"http://my.55bbs.com/feeds-([0-9]+).html\">(.*)</a>(\\s*)</cite>(\\s*)<em>(.*)</em>" );

	int			authorIdGroup		= 2;

	int			authorGroup			= 3;

	int			issueTimeGroup	= 6;

	Pattern	replyPattern		= Pattern.compile ( "<td class=\"nums\"><strong>([0-9]+)</strong> / <em>([0-9]+)</em></td>" );

	int			replyNumGroup		= 1;

	int			readNumGroup		= 2;

	@Override
	public List< TopicTaskOutModel > doParse (
			String respBody, Long forumId )
	{
		Matcher titleMatcher = titlePattern.matcher ( respBody );
		Matcher authorMatcher = authorPattern.matcher ( respBody );
		Matcher replyMatcher = replyPattern.matcher ( respBody );

		int start = 0;
		boolean find = false;
		List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );
		do
		{
			find = titleMatcher.find ( start );
			if ( !find )
			{
				break;
			}
			TopicTaskOutModel topic = new TopicTaskOutModel ( );
			topic.setTopicId ( titleMatcher.group ( topicIdGroup ) );
			topic.setTitle ( titleMatcher.group ( titleGroup ) );
			start = titleMatcher.end ( titleMatcher.groupCount ( ) );

			if ( !authorMatcher.find ( start ) )
			{
				break;
			}
			topic.setAuthorId ( authorMatcher.group ( authorIdGroup ) );
			topic.setAuthor ( authorMatcher.group ( authorGroup ) );
			topic.setIssueTime ( authorMatcher.group ( issueTimeGroup ) );
			start = authorMatcher.end ( authorMatcher.groupCount ( ) );

			if ( replyMatcher.find ( start ) )
			{
				topic.setReplyNum ( Digit.parseInt ( replyMatcher.group ( replyNumGroup ), 0 ) );
				topic.setReadNum ( Digit.parseInt ( replyMatcher.group ( readNumGroup ), 0 ) );
				start = replyMatcher.end ( replyMatcher.groupCount ( ) );
			}

			topics.add ( topic );
		}
		while ( find );
		return topics;
	}

}
