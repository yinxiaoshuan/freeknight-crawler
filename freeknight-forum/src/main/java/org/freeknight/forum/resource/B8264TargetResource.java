
package org.freeknight.forum.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B8264TargetResource
		implements TargetResource
{

	Pattern	pattern			= Pattern.compile ( "([0-9]+).html" );

	String	forumRegex	= "${fsrcId}";

	String	pageRegex		= "${pageNum}";

	String	formatter		= "http://bbs.8264.com/forum-" + forumRegex + "-" + pageRegex + ".html";

	@Override
	public String getTarget (
			String fsrcId, String url, int pageNum )
	{
		if ( pageNum == 1 )
		{
			return url;
		}
		final Matcher matcher = pattern.matcher ( url );
		if ( !matcher.find ( ) )
		{// 特殊处理.
			return formatter.replace ( forumRegex, fsrcId ).replace ( pageRegex, String.valueOf ( pageNum ) );
		}

		final int start = matcher.start ( 1 );
		final int end = matcher.end ( 1 );

		final String prefix = url.substring ( 0, start );
		final String posfix = url.substring ( end );
		return prefix + pageNum + posfix;
	}

}
