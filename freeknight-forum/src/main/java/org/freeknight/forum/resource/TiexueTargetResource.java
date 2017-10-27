
package org.freeknight.forum.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.freeknight.forum.cause.IllegalTargetResourceException;

public class TiexueTargetResource
		implements TargetResource
{

	Pattern	PAGE_PATTERN	= Pattern.compile ( "(.*)-([0-9]+).html" );

	@Override
	public String getTarget (
			String fsrcId, final String src, final int pageNum )
	{
		final Matcher matcher = PAGE_PATTERN.matcher ( src );
		final boolean exist = matcher.find ( 0 );
		if ( !exist )
		{
			throw new IllegalTargetResourceException ( "target: " + src + ", regex: {" + PAGE_PATTERN.pattern ( ) + "}" );
		}

		final int start = matcher.start ( 2 );
		final int end = matcher.end ( 2 );

		final StringBuilder toTarget = new StringBuilder ( src.substring ( 0, start ) );
		toTarget.append ( pageNum );
		toTarget.append ( src.substring ( end ) );
		return toTarget.toString ( );
	}
}
