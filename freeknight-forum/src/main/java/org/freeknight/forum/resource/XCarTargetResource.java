
package org.freeknight.forum.resource;

public class XCarTargetResource
		implements TargetResource
{

	@Override
	public String getTarget (
			String fsrcId, String target, int pageNum )
	{
		// http://www.xcar.com.cn/bbs/forumdisplay.php?fid=931&page=2
		return target + "&page=" + pageNum;
	}

}
