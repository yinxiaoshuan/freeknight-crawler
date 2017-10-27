
package org.freeknight.forum.task.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.framework.math.Digit;

public class SouthcnTotalPageParser
		implements Parser< String, Void, Integer >
{

	@Override
	public Integer doParse (
			String respBody, Void assistant )
	{
		final Pattern pattern = Pattern.compile ( "<span title=\"共(\\s*)([0-9]+)(\\s*)页\"> /(\\s*)([0-9]+)(\\s*)页</span>" );
		final Matcher matcher = pattern.matcher ( respBody );
		if ( !matcher.find ( ) )
		{
			return 1;
		}

		final String totalPage = matcher.group ( 2 );
		int total = Digit.parseInt ( totalPage, 1 );
		return total;
	}

}
