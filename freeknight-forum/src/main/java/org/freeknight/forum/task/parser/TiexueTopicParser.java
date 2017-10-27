
package org.freeknight.forum.task.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.framework.math.Digit;

public class TiexueTopicParser
		implements Parser< String, Void, List< TopicTaskOutModel > >
{

	Pattern	titleP				=
														Pattern
																.compile ( "<p class=\"titls\"><a href=\"http://bbs.tiexue.net/post_(.*)_1.html\" target=\"_blank\" title=\"(.*)发帖时间:(.*)\" class=\"listTitle\" >(.*)</a></p>" );

	int			topicIdGroup	= 1;

	int			titleGroup		= 4;

	int			issueGroup		= 3;

	Pattern	authorP				=
														Pattern
																.compile ( "<div class=\"cel_03\"><a href=\"http://i.tiexue.net/([0-9]+)\" target=\"_blank\">(.*)</a></div>" );

	int			authorIdGroup	= 1;

	int			authorGroup		= 2;

	Pattern	replyNumP			= Pattern.compile ( "<div class=\"cel_04\"><p>(.*)/(.*)</p></div>" );

	Pattern	subReplyNumP	= Pattern.compile ( "<font color=\"(.*)\">([0-9]+)</font>" );

	int			replyNumGroup	= 2;

	int			readNumGroup	= 1;

	@Override
	public List< TopicTaskOutModel > doParse (
			String respBody, Void assistant )
	{
		final List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );

		Matcher titleM = titleP.matcher ( respBody );
		Matcher authorM = authorP.matcher ( respBody );
		Matcher replyNumM = replyNumP.matcher ( respBody );

		int start = 0;
		boolean find;
		do
		{
			final TopicTaskOutModel topic = new TopicTaskOutModel ( );
			find = titleM.find ( start );
			if ( find )
			{
				String topicId = titleM.group ( topicIdGroup );
				String title = titleM.group ( titleGroup );
				String issue = titleM.group ( issueGroup );
				topic.setTopicId ( topicId );
				topic.setTitle ( title );

				String issueTime = convert ( issue );
				topic.setIssueTime ( issueTime );

				int endPoint = titleM.end ( );

				if ( authorM.find ( start ) )
				{
					String authorId = authorM.group ( authorIdGroup );
					String author = authorM.group ( authorGroup );
					topic.setAuthorId ( authorId );
					topic.setAuthor ( author );
					endPoint = authorM.end ( authorM.groupCount ( ) );
				}

				if ( replyNumM.find ( start ) )
				{
					String readNum = replyNumM.group ( readNumGroup );
					String replyNum = replyNumM.group ( replyNumGroup );
					topic.setReadNum ( split ( readNum ) );
					topic.setReplyNum ( split ( replyNum ) );
					endPoint = replyNumM.end ( replyNumM.groupCount ( ) );
				}

				start = endPoint;
				topics.add ( topic );
			}
		}
		while ( find );
		return topics;
	}

	private String convert (
			String issue )
	{
		DateFormat df = new SimpleDateFormat ( "yyyy/MM/dd HH:mm:ss" );
		String issueTime = null;
		try
		{
			Date _issue = df.parse ( issue );

			df = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
			issueTime = df.format ( _issue );
		}
		catch ( ParseException e )
		{
			// Ignore it.
		}
		return issueTime;
	}

	private int split (
			String style )
	{
		Matcher matcher = subReplyNumP.matcher ( style );
		String num = style;
		if ( matcher.find ( ) )
		{
			num = matcher.group ( 2 );
		}
		return Digit.parseInt ( num, 0 );
	}
}
