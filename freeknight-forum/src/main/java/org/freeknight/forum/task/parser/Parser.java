
package org.freeknight.forum.task.parser;

/**
 * 解析器.
 * 
 * @author yrj
 *
 */
public interface Parser< E, V, T >
{

	/**
	 * 解析工作.
	 * 
	 * @param src
	 * @param assistant
	 *          助手.
	 * @return
	 */
	T doParse (
			E src, V assistant );
}
