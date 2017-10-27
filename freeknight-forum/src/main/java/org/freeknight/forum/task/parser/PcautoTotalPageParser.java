
package org.freeknight.forum.task.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.framework.math.Digit;

public class PcautoTotalPageParser
		implements Parser< String, Void, Integer >
{

	Pattern	pattern					=
															Pattern
																	.compile ( "<a href=\"http://bbs.pcauto.com.cn/forum-([0-9]+)-([0-9]+).html\"(\\s*)>(.*)([0-9]+)</a>" );

	int			totalPageGroup	= 2;

	@Override
	public Integer doParse (
			String respBody, Void assistant )
	{
		final Matcher matcher = pattern.matcher ( respBody );
		int maxPage = 1;
		do
		{
			int start = 0;
			boolean find = matcher.find ( start );
			if ( !find )
			{
				break;
			}
			start = matcher.end ( matcher.groupCount ( ) );

			String page = matcher.group ( totalPageGroup );
			maxPage = Math.max ( Digit.parseInt ( page, 0 ), maxPage );
		}
		while ( true );
		return maxPage;
	}

}
