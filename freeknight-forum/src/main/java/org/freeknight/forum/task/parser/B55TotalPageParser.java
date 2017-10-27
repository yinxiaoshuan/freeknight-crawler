
package org.freeknight.forum.task.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.framework.math.Digit;

public class B55TotalPageParser
		implements Parser< String, Void, Integer >
{
	Pattern	totalPagePattern	= Pattern
																.compile ( "<a href=\"/forum-([0-9a-z])+-([0-9]+).html\" class=\"last\">...(\\s*)([0-9]+)</a>" );

	int			totalPageGroup		= 4;

	@Override
	public Integer doParse (
			String respBody, Void assistant )
	{
		int maxPage = 1;
		Matcher matcher = totalPagePattern.matcher ( respBody );
		if ( !matcher.find ( ) )
		{
			return maxPage;
		}

		String page = matcher.group ( totalPageGroup ).trim ( );
		maxPage = Digit.parseInt ( page, 1 );
		return maxPage;
	}
}
