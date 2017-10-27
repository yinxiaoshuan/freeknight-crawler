
package org.freeknight.forum.task.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.framework.math.Digit;

/**
 *  总页码解析器.
 * 
 * @author yrj
 *
 */
public class ChinaunixTotalPageParser
		implements Parser< String, Void, Integer >
{

	private Pattern	pattern					= Pattern
																			.compile ( "<span title=\"共(\\s*)([0-9]+)(\\s*)页\">(\\s*)/(\\s*)([0-9]+)(\\s*)页</span>" );

	int							totalPageGroup	= 2;

	@Override
	public Integer doParse (
			String respBody, Void assistant )
	{
		int maxPage = 1;
		Matcher matcher = pattern.matcher ( respBody );
		if ( !matcher.find ( ) )
		{
			return maxPage;
		}

		String page = matcher.group ( totalPageGroup ).trim ( );
		maxPage = Digit.parseInt ( page, 1 );
		return maxPage;
	}

}
