
package org.freeknight.framework.crawler.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.freeknight.framework.crawler.db.entity.SingerEntity;

public class MusicMapper
{

	public static final String	url				= "jdbc:mysql://localhost/crawier?useUnicode=true&characterEncoding=utf-8";

	public static final String	name			= "com.mysql.jdbc.Driver";

	public static final String	user			= "yrj";

	public static final String	password	= "123456";

	public List< SingerEntity > querySingerBySite (
			int site )
	{
		final List< SingerEntity > singers = new ArrayList< SingerEntity > ( );
		Connection conn = null;
		Statement stmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );
			stmt = conn.createStatement ( );

			final ResultSet rs = stmt.executeQuery ( "select s_id, ss_id, sm_id, s_name from t_singer where site = " + site );
			while ( rs.next ( ) )
			{
				final SingerEntity singer = new SingerEntity ( );
				singer.setSingerId ( rs.getInt ( 1 ) );
				singer.setSs_id ( rs.getString ( 2 ) );
				singer.setSm_id ( rs.getString ( 3 ) );
				singer.setS_name ( rs.getString ( 4 ) );
				singers.add ( singer );
			}
		}
		catch ( final ClassNotFoundException e )
		{
			e.printStackTrace ( );
		}
		catch ( final SQLException e )
		{
			throw new RuntimeException ( e );
		}
		finally
		{
			if ( conn != null )
			{
				try
				{
					conn.close ( );
				}
				catch ( final SQLException e )
				{
					e.printStackTrace ( );
				}
			}
		}
		return singers;
	}

}
