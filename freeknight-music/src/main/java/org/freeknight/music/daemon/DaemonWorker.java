
package org.freeknight.music.daemon;

/**
 * 后台工作者.
 * 
 * @author yrj
 *
 */
public interface DaemonWorker
{

	/**
	 * 后台工作者名称.
	 * 
	 * @return
	 */
	String getName ( );

	/**
	 * 初始化工作.
	 */
	void init ( );

	/**
	 * 启动工作者线程.
	 */
	void start ( );

	/**
	 * 资源关闭.
	 */
	void shutdown ( );
}
