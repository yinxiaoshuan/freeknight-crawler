
package org.freeknight.forum.task.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.framework.math.Digit;

public class ChinaunixTopicParser
		implements Parser< String, Void, List< TopicTaskOutModel > >
{

	Pattern	titleP				= Pattern
														.compile ( "<a href=\"thread-([0-9]+)-1-([0-9]+).html\"(\\s*)(.*)(\\s*)class=\"xst\" >(.*)</a>" );

	int			topicIdGroup	= 1;

	int			titleGroup		= 6;

	Pattern	authorP				= Pattern.compile ( "<a href=\"space-uid-([0-9]+).html\" c=\"1\">(.*)</a></cite>" );

	int			authorIdGroup	= 1;

	int			authorGroup		= 2;

	Pattern	issueP				= Pattern.compile ( "<em><span>(.*)</span></em>" );

	int			issueGroup		= 1;

	Pattern	replyP				= Pattern.compile ( "<a href=\"thread-([0-9]+)-1-([0-9]+).html\" class=\"xi2\">(.*)</a>" );

	int			replyNumGroup	= 3;

	Pattern	readP					= Pattern.compile ( "<em>([0-9]+)</em>" );

	int			readNumGroup	= 1;

	@Override
	public List< TopicTaskOutModel > doParse (
			String respBody, Void assistant )
	{
		Matcher titleMatcher = titleP.matcher ( respBody );
		Matcher authorM = authorP.matcher ( respBody );
		Matcher issueM = issueP.matcher ( respBody );
		Matcher replyM = replyP.matcher ( respBody );
		Matcher readM = readP.matcher ( respBody );

		int start = 0;
		boolean find;
		final List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );
		do
		{
			final TopicTaskOutModel topic = new TopicTaskOutModel ( );
			int endPoint = 0;
			find = titleMatcher.find ( start );
			if ( find )
			{
				topic.setTopicId ( titleMatcher.group ( topicIdGroup ) );
				topic.setTitle ( titleMatcher.group ( titleGroup ) );
				endPoint = titleMatcher.end ( );

				if ( authorM.find ( start ) )
				{
					topic.setAuthorId ( authorM.group ( authorIdGroup ) );
					String author = authorM.group ( authorGroup );
					int emIndex = author.indexOf ( "<em" );
					if ( emIndex > -1 )
					{
						author = author.substring ( 0, emIndex );
					}
					topic.setAuthor ( author );// <font color='#f6c500'>shenyue_sam</font> 这种场景也未处理.
					endPoint = Math.max ( endPoint, authorM.end ( authorM.groupCount ( ) ) );
				}
				if ( issueM.find ( start ) )
				{
					topic.setIssueTime ( issueM.group ( issueGroup ) );
					endPoint = Math.max ( endPoint, issueM.end ( issueM.groupCount ( ) ) );
				}
				if ( replyM.find ( start ) )
				{
					int replyNum = Digit.parseInt ( replyM.group ( replyNumGroup ), 0 );
					topic.setReplyNum ( replyNum );
					endPoint = Math.max ( endPoint, replyM.end ( replyM.groupCount ( ) ) );
				}
				if ( readM.find ( start ) )
				{
					int readNum = Digit.parseInt ( readM.group ( readNumGroup ), 0 );
					topic.setReadNum ( readNum );
					endPoint = Math.max ( endPoint, readM.end ( readM.groupCount ( ) ) );
				}
				start = endPoint;
				topics.add ( topic );
			}
		}
		while ( find );
		return topics;
	}

}
