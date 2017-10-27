
package org.freeknight.forum.daemon;

/**
 * 后台工作者.
 * 
 * @author yrj
 *
 */
public interface DaemonWorker
{

	String getName ( );

	void init ( );

	void start (
			String forum );

	void shutdown ( );

	public enum DaemonWorkerEngine
	{
		XCAR ( "xcar" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new XCarTopicWorker ( );
			}

		},
		AUTO_HOME ( "autohome" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new AutohomeTopicWorker ( );
			}

		},
		TOPSAGE ( "topsage" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new TopsageTopicWorker ( );
			}

		},
		$8264 ( "8264" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new B8264TopicWorker ( );
			}

		},
		BBS55 ( "55bbs" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new B55TopicWorker ( );
			}

		},
		CHINAUNIX ( "chinaunix" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new ChinaunixTopicWorker ( );
			}

		},
		PCAUTO ( "pcauto" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new PcautoTopicWorker ( );
			}

		},
		SOUTHCN ( "southcn" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new SouthcnTopicWorker ( );
			}

		},
		TIEXUE ( "tiexue" )
		{

			@Override
			public DaemonWorker initDaemonWorker ( )
			{
				return new TiexueTopicWorker ( );
			}

		};

		private String	forumName;

		public String getForumName ( )
		{
			return forumName;
		}

		private DaemonWorkerEngine (
				String forumName ) {
			this.forumName = forumName;
		}

		abstract public DaemonWorker initDaemonWorker ( );

		public static DaemonWorkerEngine getDaemonWorker (
				String forum )
		{
			for ( DaemonWorkerEngine ff : DaemonWorkerEngine.values ( ) )
			{
				if ( ff.getForumName ( ).equalsIgnoreCase ( forum ) )
				{
					return ff;
				}
			}

			throw new IllegalArgumentException ( "Can't found Forum: " + forum );
		}
	}
}
