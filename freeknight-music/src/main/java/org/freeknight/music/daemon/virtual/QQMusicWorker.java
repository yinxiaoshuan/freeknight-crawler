
package org.freeknight.music.daemon.virtual;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.freeknight.framework.crawler.db.MusicMapper;
import org.freeknight.framework.crawler.db.entity.SingerEntity;
import org.freeknight.music.cause.ShutdownException;
import org.freeknight.music.daemon.AbstractVirtualWorker;
import org.freeknight.music.daemon.DaemonWorkerEngine;
import org.freeknight.music.task.MusicTask;
import org.freeknight.music.task.QQMusicTask;
import org.freeknight.music.task.model.MusicTaskInModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QQMusicWorker
		extends AbstractVirtualWorker
{

	Logger											logger			= LoggerFactory.getLogger ( QQMusicWorker.class );

	MusicMapper									musicMapper	= new MusicMapper ( );

	BlockingQueue< MusicTask >	tasks				= new LinkedBlockingQueue< MusicTask > ( );

	@Override
	public String getName ( )
	{
		return "QQ-Music";
	}

	@Override
	public void init ( )
	{
		List< SingerEntity > singers = musicMapper.querySingerBySite ( DaemonWorkerEngine.QQ.getSite ( ) );
		if ( singers == null )
		{
			logger.warn ( Thread.currentThread ( ).getName ( )
					+ ", in '"
					+ DaemonWorkerEngine.QQ.getSite ( )
					+ "' site, no exist singers." );
			return;
		}

		for ( SingerEntity singer : singers )
		{
			MusicTaskInModel taskInModel = new MusicTaskInModel ( singer.getSingerId ( ), singer.getSm_id ( ), singer.getS_name ( ) );
			MusicTask task = new QQMusicTask ( taskInModel );

			tasks.add ( task );
		}

		taskExecutor = new ThreadPoolExecutor ( 8, 8, 30, TimeUnit.SECONDS, new LinkedBlockingQueue< Runnable > ( ) );
		logger.info ( Thread.currentThread ( ).getName ( ) + ", initialize finished, tasks totalNum: " + tasks.size ( ) );
	}

	@Override
	protected void doProcess ( )
	{
		MusicTask task = tasks.poll ( );
		if ( task == null )
		{
			throw new ShutdownException ( DaemonWorkerEngine.QQ.getKey ( ) + ", task finished." );
		}

		taskExecutor.execute ( task );
	}

}
