package by.htp.ex.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserDataValidationImpl implements UserDataValidation{

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
	
	@Override
	public boolean checkAUthData(String login, String password) {
		Matcher loginMatcher = LOGIN_PATTERN.matcher(login);
		Matcher passwordMatcher=PASSWORD_PATTERN.matcher(password);
		return loginMatcher.matches()&passwordMatcher.matches();			
	}	
		
	@Override
	public boolean checkRegData(String name, String surname, String birthday, String login, String password, String email) {
		Matcher nameMatcher = NAME_PATTERN.matcher(name);
		Matcher surnameMatcher = SURNAME_PATTERN.matcher(surname);
		Matcher birthdayMatcher = BIRTHDAY_PATTERN.matcher(birthday);
		Matcher loginMatcher = LOGIN_PATTERN.matcher(login);
		Matcher passwordMatcher=PASSWORD_PATTERN.matcher(password);
		Matcher emailMatcher=EMAIL_PATTERN.matcher(email);
		return nameMatcher.matches()&surnameMatcher.matches()& loginMatcher.matches()&passwordMatcher.matches()&emailMatcher.matches()& birthdayMatcher.matches();
	}
		
}
