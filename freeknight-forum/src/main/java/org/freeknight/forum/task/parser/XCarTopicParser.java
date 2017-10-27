
package org.freeknight.forum.task.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.task.model.TopicTaskOutModel;
import org.freeknight.framework.math.Digit;

public class XCarTopicParser
		implements Parser< String, Void, List< TopicTaskOutModel > >
{

	final Pattern	TITLE						=
																		Pattern
																				.compile ( "<a class=\"titlink\" target=\"_blank\" href=\"/bbs/viewthread.php\\?tid=([0-9]+)\"(\\s*)(.*)>(.*)</a>" );

	int						topicIdGroup		= 1;

	int						titleGroup			= 4;

	final Pattern	AUTHOR					=
																		Pattern
																				.compile ( "<a href=\"http://my.xcar.com.cn/space.php\\?uid=([0-9]+)\" target=\"_blank\" class=\"linkblack\">(.*)</a>(\\s*)<span class=\"tdate\">(.*)</span>" );

	int						authorIdGroup		= 1;

	int						authorNameGroup	= 2;

	int						issueTimeGroup	= 4;

	final Pattern	COUNT						=
																		Pattern
																				.compile ( "<dd class=\"cli_dd\"><span class=\"fontblue\">([0-9]+)</span><span class=\"tcount\">([0-9]+)</span></dd>" );

	int						replyNumGroup		= 1;

	int						readNumGroup		= 2;

	@Override
	public List< TopicTaskOutModel > doParse (
			final String respBody, final Void n )
	{
		final List< TopicTaskOutModel > topics = new ArrayList< TopicTaskOutModel > ( );
		final Matcher titleMatcher = TITLE.matcher ( respBody );
		Matcher authorMatcher = AUTHOR.matcher ( respBody );
		Matcher countMatcher = COUNT.matcher ( respBody );
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
			else
			{
				// 存在匿名用户, 此时无法解析, 不为占比率极低的情况做处理. 采用粗暴方式操作.
				topic.setAuthorId ( "0" );
				topic.setAuthor ( "匿名" );
				topic.setIssueTime ( "2007-03-24" );
			}

			if ( countMatcher.find ( start ) )
			{
				String replyNum = countMatcher.group ( replyNumGroup );
				String readNum = countMatcher.group ( readNumGroup );
				topic.setReplyNum ( Digit.parseInt ( replyNum, 0 ) );
				topic.setReadNum ( Digit.parseInt ( readNum, 0 ) );
			}

			topics.add ( topic );
		}
		while ( exist );

		return topics;
	}
}