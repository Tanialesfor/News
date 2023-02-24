package by.htp.ex.util.validation;

public interface UserDataValidation {
       
	   boolean checkAUthData(String login, String password);
       boolean checkRegData(String name, String surname, String birthday, String login, String password, String email);
}
