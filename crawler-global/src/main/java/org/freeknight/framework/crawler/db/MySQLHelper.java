
package org.freeknight.framework.crawler.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.freeknight.framework.crawler.db.entity.AlbumEntity;
import org.freeknight.framework.crawler.db.entity.ForumEntity;
import org.freeknight.framework.crawler.db.entity.SingerEntity;
import org.freeknight.framework.crawler.db.entity.TopicEntity;

public final class MySQLHelper
{

	public static final String	url				= "jdbc:mysql://localhost/crawier?useUnicode=true&characterEncoding=utf-8";

	public static final String	name			= "com.mysql.jdbc.Driver";

	public static final String	user			= "yrj";

	public static final String	password	= "123456";

	public static void insert (
			final List< SingerEntity > singers )
	{
		Connection conn = null;
		PreparedStatement pstmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );

			final String sql = "insert into t_singer (s_name, alias, site, ss_id, sm_id, portrait) values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement ( sql );
			for ( final SingerEntity singer : singers )
			{
				pstmt.setString ( 1, singer.getS_name ( ) );
				pstmt.setString ( 2, singer.getAlias ( ) );
				pstmt.setInt ( 3, singer.getSite ( ) );
				pstmt.setString ( 4, singer.getSs_id ( ) );
				pstmt.setString ( 5, singer.getSm_id ( ) );
				pstmt.setString ( 6, singer.getPortrait ( ) );
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
					// TODO Auto-generated catch block
					e.printStackTrace ( );
				}
			}
		}
	}

	private MySQLHelper ( ) {
		throw new AssertionError ( "Uninstantiable class." );
	}

	public static void insertAlbum (
			final List< AlbumEntity > albums )
	{
		Connection conn = null;
		PreparedStatement pstmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );

			final String sql =
					"insert into t_album (album_id, album_mid, album_name, site, s_id, genre, language, lssue_comp, type, lssue_time) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement ( sql );
			for ( final AlbumEntity album : albums )
			{
				pstmt.setString ( 1, album.getAlbum_id ( ) );
				pstmt.setString ( 2, album.getAlbum_mid ( ) );
				pstmt.setString ( 3, album.getAlbum_name ( ) );
				pstmt.setInt ( 4, album.getSite ( ) );
				pstmt.setString ( 5, album.getS_id ( ) );
				pstmt.setString ( 6, album.getGenre ( ) );
				pstmt.setString ( 7, album.getLanguage ( ) );
				pstmt.setString ( 8, album.getLssue_comp ( ) );
				pstmt.setString ( 9, album.getType ( ) );
				pstmt.setDate ( 10, album.getLssue_time ( ) );
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
	}

	public static void insertForums (
			final List< ForumEntity > forums )
	{
		Connection conn = null;
		PreparedStatement pstmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );

			final String sql = "insert into forum (fsrcid, fname, prev, url, site) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement ( sql );
			for ( final ForumEntity forum : forums )
			{
				pstmt.setString ( 1, forum.getFsrcid ( ) );
				pstmt.setString ( 2, forum.getFname ( ) );
				pstmt.setString ( 3, forum.getPrev ( ) );
				pstmt.setString ( 4, forum.getUrl ( ) );
				pstmt.setString ( 5, forum.getSite ( ) );
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
	}

	public static List< ForumEntity > queryForumsBySite (
			final String site )
	{
		final List< ForumEntity > forums = new ArrayList< ForumEntity > ( );
		Connection conn = null;
		Statement stmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );
			stmt = conn.createStatement ( );

			final ResultSet rs = stmt.executeQuery ( "select fid, fsrcid, fname,url from forum where site = '" + site + "'" );
			while ( rs.next ( ) )
			{
				final ForumEntity forum = new ForumEntity ( );
				forum.setFid ( rs.getLong ( 1 ) );
				forum.setFsrcid ( rs.getString ( 2 ) );
				forum.setFname ( rs.getString ( 3 ) );
				forum.setUrl ( rs.getString ( 4 ) );
				forums.add ( forum );
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
		return forums;
	}

	public static void insertTopics (
			final List< TopicEntity > topics ) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );

			final String sql =
					"insert into topic_autohome (forum_id, tsid, title, author_id, author_name, reply_num, read_num, issue_time) values (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement ( sql );
			for ( final TopicEntity topic : topics )
			{
				pstmt.setLong ( 1, topic.getForumId ( ) );
				pstmt.setString ( 2, topic.getTopicId ( ) );
				pstmt.setString ( 3, topic.getTitle ( ) );
				pstmt.setString ( 4, topic.getAuthorId ( ) );
				pstmt.setString ( 5, topic.getAuthorName ( ) );
				pstmt.setInt ( 6, topic.getReplyNum ( ) );
				pstmt.setInt ( 7, topic.getReadNum ( ) );
				pstmt.setTimestamp ( 8, topic.getIssueTime ( ) );
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
			throw e;
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

	/**
	 * 分页查询歌手信息.
	 * 
	 * @param site
	 * @param offset
	 * @param limit
	 * @return
	 */
	public static List< SingerEntity > querySingerBySite (
			int site, int offset, int limit )
	{
		final List< SingerEntity > singers = new ArrayList< SingerEntity > ( );
		Connection conn = null;
		Statement stmt;
		try
		{
			Class.forName ( name );
			conn = DriverManager.getConnection ( url, user, password );
			stmt = conn.createStatement ( );

			final ResultSet rs =
					stmt.executeQuery ( "select s_id,s_name,ss_id, sm_id, portrait from crawier.t_singer where site = '"
							+ site
							+ "' limit "
							+ offset
							+ ", "
							+ limit );
			while ( rs.next ( ) )
			{
				final SingerEntity singer = new SingerEntity ( );
				singer.setSingerId ( rs.getInt ( "s_id" ) );
				singer.setS_name ( rs.getString ( "s_name" ) );
				singer.setSs_id ( rs.getString ( "ss_id" ) );
				singer.setSm_id ( rs.getString ( "sm_id" ) );
				singer.setPortrait ( rs.getString ( "portrait" ) );
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
