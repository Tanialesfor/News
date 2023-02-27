package by.htp.ex.util.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class NewsValidator {

	private List<String> errors;

	private NewsValidator(NewsValidationBuilder b) {
		this.errors = b.errors;
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
		NewsValidator other = (NewsValidator) obj;
		return Objects.equals(errors, other.errors);
	}
	public static class NewsValidationBuilder implements ValidationBuilder <NewsValidator> {
		private List<String> errors = new ArrayList<String>();

		private static final String TITLE_PATTERN_REG = "^[\\S\\s]{5,45}$";
		private static final String BRIEF_PATTERN_REG = "^[\\S\\s]{5,100}$";
		private static final String CONTENT_PATTERN_REG = "^[\\S\\s]{10,}$";
		private static final String NEWSDATE_PATTERN_REG = "((19|20)\\d\\d)\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])";
		
		private static final Pattern TITLE_PATTERN = Pattern.compile(TITLE_PATTERN_REG);
		private static final Pattern BRIEF_PATTERN = Pattern.compile(BRIEF_PATTERN_REG);
		private static final Pattern CONTENT_PATTERN = Pattern.compile(CONTENT_PATTERN_REG);
		private static final Pattern NEWSDATE_PATTERN = Pattern.compile(NEWSDATE_PATTERN_REG);

		private static final String TITLE_INVALID = "title invalid";
		private static final String BRIEF_INVALID = "brief invalid";
		private static final String CONTENT_INVALID = "content invalid";
		private static final String NEWSDATE_INVALID = "newsDate invalid";
		
		public NewsValidationBuilder checkTitle(String title) {
			Matcher titleMatcher = TITLE_PATTERN.matcher(title);
			if (!titleMatcher.matches()) {
				errors.add(TITLE_INVALID);
			}
			return this;
		}

		public NewsValidationBuilder checkBrief(String brief) {
			Matcher briefMatcher = BRIEF_PATTERN.matcher(brief);
			if (!briefMatcher.matches()) {
				errors.add(BRIEF_INVALID);
			}
			return this;
		}

		public NewsValidationBuilder checkContent(String content) {
			Matcher contentMatcher = CONTENT_PATTERN.matcher(content);
			if (!contentMatcher.matches()) {
				errors.add(CONTENT_INVALID);
			}
			return this;
		}
		
		public NewsValidationBuilder checkNewsDate(String newsDate) {
			Matcher newsDateMatcher = NEWSDATE_PATTERN.matcher(newsDate);
			if (!newsDateMatcher.matches()) {
				errors.add(NEWSDATE_INVALID);
			}
			return this;
		}
		
		public NewsValidationBuilder checkAllNewsData(String title, String brief, String content, String newsDate) {
			return this.checkTitle(title).checkBrief(brief).checkContent(content).checkNewsDate(newsDate);			
		}

		@Override
		public NewsValidator isValid() {
			return new NewsValidator(this);
		}
	}

}
