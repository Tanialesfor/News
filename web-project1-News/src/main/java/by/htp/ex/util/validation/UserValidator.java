package by.htp.ex.util.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserValidator {

	private List<String> errors;
	
	private UserValidator(UserValidationBuilder ub) {
		this.errors = ub.errors;
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(errors);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserValidator other = (UserValidator) obj;
		return Objects.equals(errors, other.errors);
	}

	public static class UserValidationBuilder implements ValidationBuilder <UserValidator>{
		
		private List<String> errors = new ArrayList<String>();
		
		private static final String NAME_PATTERN_REG = "^[a-zA-Z-]{1,10}$";	
		private static final String SURNAME_PATTERN_REG = "^[a-zA-Z-]{1,15}$";
		private static final String BIRTHDAY_PATTERN_REG = "((19|20)\\d\\d)\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])";
		private static final String LOGIN_PATTERN_REG = "^[a-zA-Z0-9-]{1,10}$";
		private static final String PASSWORD_PATTERN_REG = "^[a-zA-Z0-9-]{1,10}$";
		private static final String EMAIL_PATTERN_REG = "^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
		
		private static final Pattern NAME_PATTERN = Pattern.compile(NAME_PATTERN_REG);
		private static final Pattern SURNAME_PATTERN = Pattern.compile(SURNAME_PATTERN_REG);
		private static final Pattern BIRTHDAY_PATTERN = Pattern.compile(BIRTHDAY_PATTERN_REG);
		private static final Pattern LOGIN_PATTERN = Pattern.compile(LOGIN_PATTERN_REG);
		private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_PATTERN_REG);
		private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_PATTERN_REG);
		
		private static final String NAME_INVALID = "name invalid";
		private static final String SURNAME_INVALID = "surname invalid";
		private static final String BIRTHDAY_INVALID = "birthday invalid";
		private static final String LOGIN_INVALID = "login invalid";
		private static final String PASSWORD_INVALID = "password invalid";
		private static final String EMAIL_INVALID = "email invalid";
		
		public UserValidationBuilder checkName(String name) {
			Matcher nameMatcher = NAME_PATTERN.matcher(name);
			if (!nameMatcher.matches()) {
				errors.add(NAME_INVALID);
			}
			return this;
		}
		
		public UserValidationBuilder checkSurname(String surname) {
			Matcher surnameMatcher = SURNAME_PATTERN.matcher(surname);
			if (!surnameMatcher.matches()) {
				errors.add(SURNAME_INVALID);
			}
			return this;
		}
		
		public UserValidationBuilder checkBirthday(String birthday) {
			Matcher birthdayMatcher = BIRTHDAY_PATTERN.matcher(birthday);
			if (!birthdayMatcher.matches()) {
				errors.add(BIRTHDAY_INVALID);
			}
			return this;
		}
		
		public UserValidationBuilder checkLogin(String login) {
			Matcher loginMatcher = LOGIN_PATTERN.matcher(login);
			if (!loginMatcher.matches()) {
				errors.add(LOGIN_INVALID);
			}
			return this;
		}
		
		public UserValidationBuilder checkPassword(String password) {
			Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
			if(!passwordMatcher.matches()) {
				errors.add(PASSWORD_INVALID);
			}
			return this;
		}
		
		public UserValidationBuilder checkEmail(String email) {
			Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
			if(!emailMatcher.matches()) {
				errors.add(EMAIL_INVALID);
			}
			return this;
		}
	
		public UserValidationBuilder checkAUthData(String login, String password) {
			return this.checkLogin(login).checkPassword(password);			
		}	
		
		public UserValidationBuilder checkARegData(String name, String surname, String birthday, String login, String password, String email) {
			return this.checkName(surname).checkSurname(surname).checkBirthday(birthday).checkLogin(login).checkPassword(password).checkEmail(email);			
		}
		
		@Override
		public UserValidator isValid() {
			return new UserValidator(this);
		}
		
	}

}
