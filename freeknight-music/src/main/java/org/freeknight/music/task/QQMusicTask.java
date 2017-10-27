
package org.freeknight.music.task;

import org.freeknight.music.task.model.MusicTaskInModel;
import org.freeknight.music.task.parser.QQMusicParser;

public class QQMusicTask
		extends MusicTask
{

	public QQMusicTask (
			MusicTaskInModel taskInModel ) {
		super ( taskInModel, new QQMusicParser ( ) );
	}

}
