
package org.freeknight.music.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.freeknight.music.mysql.entity.MusicEntity;
import org.freeknight.music.mysql.entity.SingerEntity;

public final class MySQL
{

	public static final String	url				= "jdbc:mysql://localhost/crawier?useUnicode=true&characterEncoding=utf-8";

	public static final String	name			= "com.mysql.jdbc.Driver";

	public static final String	user			= "yrj";

	public static final String	password	= "123456";

	public static List< SingerEntity > querySingerBySite (
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

	public static void insertMusics (
			List< MusicEntity > musics )
	{
		Connection conn = null;
		PreparedStatement pstmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );

			final String sql =
					"insert into crawier.t_music (music_id, music_mid, music_name, album_id, album_mid, singer_id, singer_mid, site) values (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement ( sql );
			for ( final MusicEntity music : musics )
			{
				pstmt.setString ( 1, music.getMusic_id ( ) );
				pstmt.setString ( 2, music.getMusic_mid ( ) );
				pstmt.setString ( 3, music.getMusic_name ( ) );
				pstmt.setString ( 4, music.getAlbum_id ( ) );
				pstmt.setString ( 5, music.getAlbum_mid ( ) );
				pstmt.setString ( 6, music.getSinger_id ( ) );
				pstmt.setString ( 7, music.getSinger_mid ( ) );
				pstmt.setInt ( 8, music.getSite ( ) );
				pstmt.addBatch ( );
			}

			pstmt.executeBatch ( );
		}
		catch ( final ClassNotFoundException e )
		{
			e.printStackTrace ( );
		}
		catch ( final SQLException e )
		{
			e.printStackTrace ( );
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
	}
}
