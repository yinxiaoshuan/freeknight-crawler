
package org.freeknight.forum.task.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.framework.math.Digit;

public class AutohomeTotalPageParser
		implements Parser< String, Void, Integer >
{
	// 该过滤条件存在bug, 当总页码小于5时无法匹配, 导致maxPage < 5 的情况总是为1.
	Pattern	maxPagePattern	= Pattern.compile ( "<span class=\"fs\" title=\"共(\\s*)(.*)(\\s*)页\">(.*)页</span>" );

	int			maxPageGroup		= 2;

	@Override
	public Integer doParse (
			final String respBody, final Void n )
	{
		int maxPage = 1;
		final Matcher matcher = maxPagePattern.matcher ( respBody );
		final boolean exist = matcher.find ( 0 );
		if ( !exist )
		{
			return maxPage;
		}

		final String page = matcher.group ( maxPageGroup );
		return Digit.parseInt ( page.trim ( ), 1 );
	}
}
