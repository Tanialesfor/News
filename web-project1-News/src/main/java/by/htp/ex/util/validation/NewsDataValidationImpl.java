package by.htp.ex.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDataValidationImpl implements NewsDataValidation {

	private static final String NEWSDATE_PATTERN_REG = "((19|20)\\d\\d)\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])";
	private static final Pattern NEWSDATE_PATTERN = Pattern.compile(NEWSDATE_PATTERN_REG);
	
	@Override
	public boolean checkNewsDate(String newsDate) {
		Matcher newsDateMatcher = NEWSDATE_PATTERN.matcher(newsDate);
		return newsDateMatcher.matches();
	}

	@Override
	public boolean checkInputNewsData (String title, String briefNews, String content) {
		if (title!="" & briefNews!="" & content!="") {
			return true;
		}
		return false;
	}
}
