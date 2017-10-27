
package org.freeknight.forum.daemon;

import org.freeknight.forum.task.TiexueTopicTask;
import org.freeknight.forum.task.TopicTask;
import org.freeknight.forum.task.model.TopicTaskInModel;
import org.freeknight.forum.task.model.TopicTotalPageTaskInModel;

public class TiexueTopicWorker
		extends AbstractVirtualWorker
{

	public TiexueTopicWorker ( ) {
	}

	@Override
	public String getName ( )
	{
		return DaemonWorkerEngine.TIEXUE.getForumName ( );
	}

	@Override
	protected TopicTask assemblyTopicTask (
			TopicTaskInModel taskInModel )
	{
		return new TiexueTopicTask ( taskInModel );
	}

	@Override
	protected int doPreTask (
			TopicTotalPageTaskInModel totalPageTaskInModel )
	{
		return 50;
	}

	@Override
	protected void doProcess (
			TopicTask task )
	{
	}

}
