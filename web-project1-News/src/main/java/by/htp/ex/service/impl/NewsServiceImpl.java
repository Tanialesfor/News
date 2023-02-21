package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.NewsDataValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class NewsServiceImpl implements INewsService {

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	private final NewsDataValidation newsDataValidation = ValidationProvider.getInstance().getNewsDataValidation();

	@Override
	public void add(News news) throws ServiceException {
		String title = news.getTitle();
		String brief = news.getBriefNews();
		String content = news.getContent();
		String date = news.getNewsDate();

		if (!newsDataValidation.checkNewsDate(date)) {
			throw new ServiceException("newsDate invalid");
		} else {
			if (!newsDataValidation.checkInputNewsData(title, brief, content)) {
				throw new ServiceException("fields don't enter");
			} else {
				try {
					newsDAO.addNews(news);
				} catch (NewsDAOException e) {
					throw new ServiceException(e);
				}
			}
		}
	}

	@Override
	public void update(News news) throws ServiceException {
		String title = news.getTitle();
		String brief = news.getBriefNews();
		String content = news.getContent();
		String date = news.getNewsDate();

		if (!newsDataValidation.checkNewsDate(date)) {
			throw new ServiceException("newsDate invalid");
		} else {
			if (!newsDataValidation.checkInputNewsData(title, brief, content)) {
				throw new ServiceException("fields don't enter");
			} else {
				try {
					newsDAO.updateNews(news);
				} catch (NewsDAOException e) {
					throw new ServiceException(e);
				}
			}
		}
	}

	@Override
	public void delete(String[] idNews) throws ServiceException {
		try {
			newsDAO.deleteNewses(idNews);

		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		try {
			return newsDAO.getLatestsList(5);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(News news) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}
}
