
package org.freeknight.forum.task.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.framework.math.Digit;

public class XCarTotalPageParser
		implements Parser< String, Void, Integer >
{

	private final static Pattern	PATTERN	=
																						Pattern
																								.compile ( "<a href=\"forumdisplay.php\\?fid=([0-9]+)&amp;page=([0-9]+)\" class=\"page\" rel=\"nofollow\">([0-9]+)</a>" );

	@Override
	public Integer doParse (
			final String respBody, final Void v )
	{
		int start = 0;
		int maxpage = 1;
		boolean finded = false;
		final Matcher matcher = PATTERN.matcher ( respBody );
		do
		{
			finded = matcher.find ( start );
			if ( !finded )
			{
				break;
			}
			final String page = matcher.group ( 3 );
			start = matcher.end ( matcher.groupCount ( ) );
			final int pagenum = Digit.parseInt ( page, 1 );
			maxpage = Math.max ( pagenum, maxpage );
		}
		while ( finded );

		return maxpage;
	}

}
