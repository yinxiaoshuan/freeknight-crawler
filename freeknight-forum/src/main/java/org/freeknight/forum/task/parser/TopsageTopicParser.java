
package org.freeknight.forum.task.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;

public class TopsageTopicParser
		implements Parser< String, Void, List< TopicTaskOutModel > >
{

	Pattern				titlePattern			=
																			Pattern
																					.compile ( "<a href=\"http://club.topsage.com/thread-([0-9]+)-([0-9]+)-([0-9]+).html\"(\\s*)(.*)class=\"s xst\">(.*)</a>" );

	final Pattern	authorPattern			=
																			Pattern
																					.compile ( "<a href=\"http://club.topsage.com/space-uid-([0-9]+).html\" c=\"1\"(.*)>(.*)</a>" );

	final Pattern	issueTimePattern	= Pattern.compile ( "<em><span>(.*)</span></em>" );

	final Pattern	replyPattern			=
																			Pattern
																					.compile ( "<td class=\"num\"><a href=\"http://club.topsage.com/thread-([0-9]+)-([0-9]+)-([0-9]+).html\" class=\"xi2\">([0-9]+)</a><em>([0-9]+)</em></td>" );

	@Override
	public List< TopicTaskOutModel > doParse (
			String respBody, Void assistant )
	{
		final List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );
		final Matcher titleMatcher = titlePattern.matcher ( respBody );
		final Matcher authorMatcher = authorPattern.matcher ( respBody );
		final Matcher issueTimeMatcher = issueTimePattern.matcher ( respBody );
		final Matcher replyMatcher = replyPattern.matcher ( respBody );

		int start = 0;
		boolean find;
		do
		{
			final TopicTaskOutModel topic = new TopicTaskOutModel ( );
			find = titleMatcher.find ( start );
			if ( find )
			{
				final String topicId = titleMatcher.group ( 1 );
				final String title = titleMatcher.group ( 6 );
				topic.setTopicId ( topicId );
				topic.setTitle ( title );
				int endPoint = titleMatcher.end ( titleMatcher.groupCount ( ) );
				start = endPoint;// 第一次

				if ( authorMatcher.find ( start ) )
				{
					final String authorId = authorMatcher.group ( 1 );
					final String authorName = authorMatcher.group ( 3 );
					topic.setAuthorId ( authorId );
					topic.setAuthor ( authorName );
					endPoint = Math.max ( endPoint, authorMatcher.end ( authorMatcher.groupCount ( ) ) );
				}

				if ( issueTimeMatcher.find ( start ) )
				{
					String issueTime = issueTimeMatcher.group ( 1 );
					int length = "2017-10-19 18:36:57".length ( );
					if ( issueTime.length ( ) < length )
					{
						issueTime += ":00";
					}
					topic.setIssueTime ( issueTime );
					endPoint = Math.max ( endPoint, issueTimeMatcher.end ( issueTimeMatcher.groupCount ( ) ) );
				}

				if ( replyMatcher.find ( start ) )
				{
					final String replyNum = replyMatcher.group ( 4 );
					final String readNum = replyMatcher.group ( 5 );
					topic.setReplyNum ( Integer.parseInt ( replyNum ) );
					topic.setReadNum ( Integer.parseInt ( readNum ) );
					endPoint = Math.max ( endPoint, replyMatcher.end ( replyMatcher.groupCount ( ) ) );
				}
				start = endPoint;// 第二次
				topics.add ( topic );
			}
		}
		while ( find );
		return topics;
	}

}
