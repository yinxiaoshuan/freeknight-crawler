
package org.freeknight.music.task.resource;

public final class QQMusicPageNumResource
{

	private final static String	TARGET_URL	= "https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg";

	private final static int		MAX_NUM			= 30;

	public static final String getTargetResource (
			final String smid, final int pageNum )
	{
		String target = TARGET_URL + "?format=json&platform=yqq&order=listen&songstatus=1";
		target += "&singermid=" + smid;
		target += "&begin=" + ( ( pageNum - 1 ) * MAX_NUM );
		target += "&num=" + MAX_NUM;
		return target;
	}

	private QQMusicPageNumResource ( ) {
		throw new AssertionError ( "Uninstantiable class." );
	}
}
