
package org.freeknight.forum.task.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.framework.math.Digit;

public class TopsageTotalPageParser
		implements Parser< String, Void, Integer >
{

	Pattern	maxPagePattern	= Pattern.compile ( "<span title=\"共(\\s*)([0-9]+)(\\s*)页\"> /(\\s*)([0-9]+)(\\s*)页</span>" );

	int			maxPageGroup		= 5;

	@Override
	public Integer doParse (
			String respBody, Void n )
	{
		final Matcher matcher = maxPagePattern.matcher ( respBody );
		final boolean exist = matcher.find ( 0 );
		if ( !exist )
		{
			return 1;
		}

		final String maxPage = matcher.group ( maxPageGroup );
		final int maxpage = Digit.parseInt ( maxPage.trim ( ), 1 );
		return maxpage;
	}

}
