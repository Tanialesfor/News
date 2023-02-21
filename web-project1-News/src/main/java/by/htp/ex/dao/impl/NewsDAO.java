package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

public class NewsDAO implements INewsDAO {

	private static final String SELECT_LATEST_NEWS = "SELECT * FROM news.news ORDER BY news.news.date_creation DESC LIMIT ?";
	private static final String SELECT_ALL_NEWS = "SELECT * FROM news.news";
	private static final String INSERT_NEWS = "INSERT INTO news.news(title, date_creation, brief, content, users_id, status_news_id) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SELECT_NEWS_FOR_ID = "SELECT * FROM news.news WHERE id = ?";
	private static final String UPDATE_NEWS = "UPDATE news.news SET title = ?, date_creation = ?, brief = ?, content = ?, users_id = ?, status_news_id = ? WHERE id = ?";
	private static final String DELETE_NEWS = "DELETE FROM news.news WHERE id = ?";

	private final ConnectionPool pool = ConnectionPool.getConnectionPool();

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {

		List<News> list = new ArrayList<News>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(SELECT_LATEST_NEWS);
			ps.setInt(1, count);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief"),
						rs.getString("content"), rs.getDate("date_creation").toString()));
			}

			return list;

		} catch (SQLException e) {
			throw new NewsDAOException("error select latests news in count", e);
		}

		catch (ConnectionPoolException e) {
			throw new NewsDAOException("problem with connection pool", e);
		
		} finally {
			pool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> list = new ArrayList<News>();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = pool.takeConnection();
			st = con.createStatement();

			rs = st.executeQuery(SELECT_ALL_NEWS);

			while (rs.next()) {
				list.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief"),
						rs.getString("content"), rs.getDate("date_creation").toString()));
			}

			return list;

		} catch (SQLException e) {
			throw new NewsDAOException("error select latests news", e);
		}

		catch (ConnectionPoolException e) {
			throw new NewsDAOException("problem with connection pool", e);
		
		} finally {
			pool.closeConnection(con, st, rs);
		}
	}
	

	@Override
	public int addNews(News news) throws NewsDAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(INSERT_NEWS);

			ps.setString(1, news.getTitle());
			ps.setDate(2, Date.valueOf(LocalDate.parse(news.getNewsDate())));
			ps.setString(3, news.getBriefNews());
  		    ps.setString(4, news.getContent());
			ps.setInt(5, 1);
			ps.setInt(6, 3);

			int rowsInsert=ps.executeUpdate();
			
			if (rowsInsert!=1) {
				throw new NewsDAOException("news don't added");	
			}
			return 0;

		} catch (SQLException e) {
			throw new NewsDAOException("error insert table news in method addNews", e);
		}

		catch (ConnectionPoolException e) {
			throw new NewsDAOException("problem with connection pool", e);
		
		} finally {
			pool.closeConnection(con, ps);
		}
	}

	@Override
	public News fetchById(int id) throws NewsDAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(SELECT_NEWS_FOR_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				return new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief"),rs.getString("content"), rs.getDate("date_creation").toString());
			}

		} catch (SQLException e) {
			throw new NewsDAOException("error select latests news", e);
		}

		catch (ConnectionPoolException e) {
			throw new NewsDAOException("problem with connection pool", e);
		
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		
		return null;
//		return result.stream().filter(o -> o.getIdNews().equals(id)).findFirst().orElse(null);
	}
	
	@Override
	public void updateNews(News news) throws NewsDAOException {
				
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(UPDATE_NEWS);
			ps.setString(1, news.getTitle());
			ps.setDate(2, Date.valueOf(LocalDate.parse(news.getNewsDate())));
			ps.setString(3, news.getBriefNews());
  		    ps.setString(4, news.getContent());
			ps.setInt(5, 1);
			ps.setInt(6, 3);
			ps.setInt(7, news.getIdNews());

			int rowsInsert=ps.executeUpdate();
			
			if (rowsInsert!=1) {
				throw new NewsDAOException("news don't update");	
			}
	
		} catch (SQLException e) {
			throw new NewsDAOException("error update table news in method updateNews", e);
		}

		catch (ConnectionPoolException e) {
			throw new NewsDAOException("problem with connection pool", e);
		
		} finally {
			pool.closeConnection(con, ps);
		}
		
//		int indexDelete = -1;
//		for (News element : result) {
//			if (element.getIdNews().equals(news.getIdNews())) {
//				indexDelete = result.indexOf(element);
//			}
//		}
//		if (indexDelete >= 0) {
//			result.set(indexDelete, news);
//		}

	}

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(DELETE_NEWS);
//			private static final String DELETE_NEWS = "DELETE FROM news.news WHERE id = ?";

			for (String id : idNewses) {
				int selectId = Integer.parseInt(id);
				ps.setInt(1, selectId);
				ps.addBatch();
			}

			int[] arrayRowsDelete = ps.executeBatch();

			for (int i = 0; i < arrayRowsDelete.length; i++) {
				if (arrayRowsDelete[i] != 1) {
					throw new NewsDAOException("news don't delete");
				}
			}

		} catch (SQLException e) {
			throw new NewsDAOException("error delete news in method deleteNewses", e);
		}

		catch (ConnectionPoolException e) {
			throw new NewsDAOException("problem with connection pool", e);

		} finally {
			pool.closeConnection(con, ps);
		}
	}
		//		int arrayNews[] = Arrays.stream(idNewses).mapToInt(Integer::parseInt).toArray();
//		for (int i = result.size() - 1; i > -1; i--) {
//			News newsDelete = result.get(i);
//			for (int j : arrayNews) {
//				if (newsDelete.getIdNews() == j) {
//					this.result.remove(newsDelete);
//				}
//			}
//		}

	

}
