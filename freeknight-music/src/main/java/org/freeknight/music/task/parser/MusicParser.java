
package org.freeknight.music.task.parser;

import java.util.List;

import org.freeknight.music.task.model.MusicTaskOutModel;

public interface MusicParser
{

	List< MusicTaskOutModel > parse (
			String respBody );
}
