
package org.freeknight.forum.daemon;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;
import org.freeknight.framework.crawler.db.MySQLHelper;
import org.freeknight.framework.crawler.db.entity.ForumEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象的后台工作者基类, 为子类工作者提供统一的线程任务调度.
 * 
 * @author yrj
 *
 */
abstract public class AbstractVirtualWorker
		implements DaemonWorker
{

	private class VirtualThread
			extends Thread
	{
		public VirtualThread (
				final String name ) {
			setName ( name );
			setDaemon ( false );
		}

		@Override
		public void run ( )
		{
			try
			{
				init ( );

				for ( ;; )
				{
					TopicTask task = tasks.poll ( );
					if ( task == null )
					{
						break;
					}

					taskExecutor.execute ( task );
				}
			}
			finally
			{
				shutdown ( );
			}
		}
	}

	Logger															logger	= LoggerFactory.getLogger ( getClass ( ) );

	private Thread											daemon;

	protected ThreadPoolExecutor				taskExecutor;

	private BlockingQueue< TopicTask >	tasks		= new LinkedBlockingQueue< TopicTask > ( );

	abstract protected void doProcess (
			TopicTask task );

	@Override
	public void init ( )
	{
		List< ForumEntity > forums = MySQLHelper.queryForumsBySite ( getName ( ) );
		if ( forums == null || forums.isEmpty ( ) )
		{
			logger.warn ( "forum: " + getName ( ) + ", no exists todo forums. Initialize a head of finished." );
			return;
		}

		for ( ForumEntity forum : forums )
		{
			TopicTotalPageTaskInModel totalPageTaskInModel = new TopicTotalPageTaskInModel ( forum.getFname ( ), forum.getUrl ( ) );
			int totalPage = doPreTask ( totalPageTaskInModel );

			logger.info ( "forum: " + forum.getFname ( ) + ", target: " + forum.getUrl ( ) + ", totalpage: " + totalPage );

			final TopicTaskInModel taskInModel =
					new TopicTaskInModel ( totalPageTaskInModel.getForumName ( ), totalPageTaskInModel.getTarget ( ), totalPage );
			final TopicTask task = assemblyTopicTask ( taskInModel );
			tasks.add ( task );
		}
	}

	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		return 1;
	}

	abstract protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel );

	@Override
	public void start (
			String forum )
	{
		if ( daemon == null )
		{
			daemon = new VirtualThread ( getName ( ) );
		}

		daemon.start ( );
	}

	@Override
	public void shutdown ( )
	{
		if ( taskExecutor != null )
		{
			taskExecutor.shutdown ( );
		}
		daemon.interrupt ( );
	}

}
