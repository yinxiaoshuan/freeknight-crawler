
package org.freeknight.forum.task;

import java.util.concurrent.Callable;

public interface Task< V >
		extends Callable< V >, Runnable
{

	V invoke ( );
}
