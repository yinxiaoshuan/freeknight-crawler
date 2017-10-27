
package org.freeknight.forum.task.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.framework.math.Digit;

public class B8264TopicParser
		implements Parser< String, Void, List< TopicTaskOutModel > >
{

	Pattern	titlePattern	=
														Pattern
																.compile ( "<a href=\"http://bbs.8264.com/thread-([0-9]+)-1-1.html\"(\\s*)(.*)(\\s*)target=\"_blank\" class=\"f_16\">(.*)</a>" );

	int			topicIdGroup	= 1;

	int			titleGroup		= 5;

	Pattern	authorPattern	= Pattern.compile ( "<a href=\"http://u.8264.com/space-uid-([0-9]+).html\" rel=\"nofollow\">(.*)</a>" );

	int			authorIdGroup	= 1;

	int			authorGroup		= 2;

	Pattern	issuePattern	= Pattern.compile ( "<em class=\"d_block\"(\\s*)(.*)(\\s*)>(.*)</em>" );

	int			issueGroup		= 4;

	Pattern	replyPattern	= Pattern.compile ( "<span class=\"d_block\"><a class=\"blue\">([0-9]+)</a></span>" );

	int			replyGroup		= 1;

	Pattern	readPattern		= Pattern.compile ( "<em class=\"d_block\">([0-9]+)</em>" );

	int			readGroup			= 1;

	@Override
	public List< TopicTaskOutModel > doParse (
			String respBody, Void assistant )
	{
		Matcher titleMatcher = titlePattern.matcher ( respBody );
		Matcher authorMatcher = authorPattern.matcher ( respBody );
		Matcher issueMatcher = issuePattern.matcher ( respBody );
		Matcher replyMatcher = replyPattern.matcher ( respBody );
		Matcher readMatcher = readPattern.matcher ( respBody );
		List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );

		int start = 0;
		do
		{
			if ( !titleMatcher.find ( start ) )
			{
				break;
			}

			TopicTaskOutModel topic = new TopicTaskOutModel ( );

			topic.setTopicId ( titleMatcher.group ( topicIdGroup ) );
			topic.setTitle ( titleMatcher.group ( titleGroup ) );
			start = Math.max ( start, titleMatcher.end ( titleMatcher.groupCount ( ) ) );

			if ( authorMatcher.find ( start ) )
			{
				topic.setAuthorId ( authorMatcher.group ( authorIdGroup ) );
				topic.setAuthor ( authorMatcher.group ( authorGroup ) );
				start = Math.max ( start, authorMatcher.end ( authorMatcher.groupCount ( ) ) );
			}

			if ( issueMatcher.find ( start ) )
			{
				topic.setIssueTime ( issueMatcher.group ( issueGroup ) );
				start = Math.max ( start, issueMatcher.end ( issueMatcher.groupCount ( ) ) );
			}

			if ( replyMatcher.find ( start ) )
			{
				topic.setReplyNum ( Digit.parseInt ( replyMatcher.group ( replyGroup ), 0 ) );
				start = Math.max ( start, replyMatcher.end ( replyMatcher.groupCount ( ) ) );
			}

			if ( readMatcher.find ( start ) )
			{
				topic.setReadNum ( Digit.parseInt ( readMatcher.group ( readGroup ), 0 ) );
				start = Math.max ( start, readMatcher.end ( readMatcher.groupCount ( ) ) );
			}
			topics.add ( topic );
		}
		while ( true );

		return topics;
	}

}
