
package org.freeknight.forum.task.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.framework.math.Digit;

public class SouthcnTopicParser
		implements Parser< String, Void, List< TopicTaskOutModel > >
{

	Pattern	titleP				=
														Pattern
																.compile ( "<a href=\"thread-([0-9]+)-1-([0-9]+).html\" onclick=\"atarget\\(this\\)\" class=\"xst\" >(.*)</a>" );

	int			topicIdGroup	= 1;

	int			titleGroup		= 3;

	Pattern	authorP				= Pattern.compile ( "<a href=\"space-uid-([0-9]+).html\" c=\"1\">(.*)</a>" );

	int			authorIdGroup	= 1;

	int			authorGroup		= 2;

	Pattern	issueP				= Pattern.compile ( "<em><span>(.*)</span></em>" );

	Pattern	replyP				=
														Pattern
																.compile ( "<td class=\"num\"><a href=\"thread-([0-9]+)-1-([0-9]+).html\" class=\"xi2\">([0-9]+)</a><em>([0-9]+)</em></td>" );

	int			replyNumGroup	= 3;

	int			readNumGroup	= 4;

	@Override
	public List< TopicTaskOutModel > doParse (
			String respBody, Void assistant )
	{
		final List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );

		final Matcher titleM = titleP.matcher ( respBody );
		final Matcher authorM = authorP.matcher ( respBody );
		final Matcher issueM = issueP.matcher ( respBody );
		final Matcher replyM = replyP.matcher ( respBody );
		int start = 0;
		boolean find;
		do
		{
			final TopicTaskOutModel topic = new TopicTaskOutModel ( );
			find = titleM.find ( start );
			if ( find )
			{
				int endPoint = 0;
				final String topicId = titleM.group ( topicIdGroup );
				final String title = titleM.group ( titleGroup );
				topic.setTopicId ( topicId );
				topic.setTitle ( title );
				endPoint = titleM.end ( );

				// int authorEnd = 0;
				if ( authorM.find ( start ) )
				{
					final String authorId = authorM.group ( authorIdGroup );
					final String authorName = authorM.group ( authorGroup );
					topic.setAuthorId ( authorId );
					topic.setAuthor ( authorName );
					endPoint = Math.max ( endPoint, authorM.end ( ) );
					// authorEnd = authorMatcher.end ( );
				}
				else
				{
					topic.setAuthorId ( "guest" );
					topic.setAuthor ( "匿名" );
				}

				if ( issueM.find ( start ) )
				{
					/* final String issueTime = */issueM.group ( 1 );
					// final Pattern issuePattern = Pattern.compile ( "<span title=\"(*)\"" );
					// final Matcher matcher = issuePattern.matcher ( issueTime );
					// if ( matcher.find ( ) )
					// {
					// issueTime = matcher.group ( 1 );
					// }

					topic.setIssueTime ( "2017-10-10 12:27" );
					endPoint = Math.max ( endPoint, issueM.end ( ) );
				}

				if ( replyM.find ( start ) )
				{
					final String replyNum = replyM.group ( replyNumGroup );
					final String readNum = replyM.group ( readNumGroup );
					topic.setReplyNum ( Digit.parseInt ( replyNum, 0 ) );
					topic.setReadNum ( Digit.parseInt ( readNum, 0 ) );
				}

				start = endPoint;
				topics.add ( topic );
			}
		}
		while ( find );
		return topics;
	}

}
