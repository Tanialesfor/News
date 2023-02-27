package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.UserValidator;
import by.htp.ex.util.validation.UserValidator.UserValidationBuilder;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService{

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
   
	@Override
	public String signIn(String login, String password) throws ServiceException {
				
		UserValidationBuilder userValidationBuilder = new UserValidator.UserValidationBuilder();
		UserValidator validator = userValidationBuilder.checkAUthData(login, password).isValid();

		if (!validator.getErrors().isEmpty()) {
			throw new ServiceException(validator.getErrors().toString());
		}
			try {
				if(userDAO.logination(login, password)) {
					return userDAO.getRole(login, password);
				}else {
					return "guest";
				}
			} catch(DaoException e) {
				throw new ServiceException("error from method signIn Service", e);
			}
	}

	@Override
	public boolean registration(NewUserInfo user) throws ServiceException {
		String name=user.getUserName();
		String surname=user.getUserSurname();
		String birthday=user.getBirthday();
		String email=user.getEmail();
		String login=user.getLogin();
		String password=user.getPassword();
		
		UserValidationBuilder userValidationBuilder = new UserValidator.UserValidationBuilder();
		UserValidator validator = userValidationBuilder.checkARegData(name, surname, birthday, login, password, email).isValid();

		if (!validator.getErrors().isEmpty()) {
			throw new ServiceException(validator.getErrors().toString());
		}
		
		    try {
				if (userDAO.registration(user)) {
					return true;
				} else {
					return false;
				}
			} catch (DaoException e) {
				throw new ServiceException("error from method registration Service", e);
			}
	}

	@Override
	public boolean isPermission(String login, String password) throws ServiceException {
		
		UserValidationBuilder userValidationBuilder = new UserValidator.UserValidationBuilder();
		UserValidator validator = userValidationBuilder.checkAUthData(login, password).isValid();

		if (!validator.getErrors().isEmpty()) {
			throw new ServiceException(validator.getErrors().toString());
		}
		
		else {
			try {
				if (userDAO.isPermission(login, password)==true) {
					return true;
				}
			} catch (DaoException e) {
				throw new ServiceException("error from method isPermission Service", e);
			}
			return false;
		}
	}
}
