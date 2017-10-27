
package org.freeknight.music.daemon;

import org.freeknight.music.daemon.virtual.QQMusicWorker;

public enum DaemonWorkerEngine
{

	QQ ( "qq", 1 )
	{
		@Override
		public DaemonWorker initDaemonWorker ( )
		{
			return new QQMusicWorker ( );
		}
	},

	BAIDU ( "baidu", 2 )
	{
		@Override
		public DaemonWorker initDaemonWorker ( )
		{
			return null;
		}
	};

	private String	key;

	private int			site;

	private DaemonWorkerEngine (
			String key,
			int site ) {
		this.key = key;
		this.site = site;
	}

	public String getKey ( )
	{
		return key;
	}

	public int getSite ( )
	{
		return site;
	}

	public static DaemonWorkerEngine getDaemonWorker (
			String site )
	{
		for ( DaemonWorkerEngine engine : DaemonWorkerEngine.values ( ) )
		{
			if ( engine.getKey ( ).equalsIgnoreCase ( site ) )
			{
				return engine;
			}
		}
		throw new IllegalArgumentException ( "Can't found site: " + site );
	}

	abstract public DaemonWorker initDaemonWorker ( );
}
