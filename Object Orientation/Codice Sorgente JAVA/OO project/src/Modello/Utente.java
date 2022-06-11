package Modello;

public class Utente {
	
	String email;
	public String nickname;
	public String password;
	String note;
	
	
	public Utente(String newEmail, String newPassword, String newNickname){
		
		email = newEmail;
		password = newPassword;
		nickname = newNickname;
		note = "";
	}
}
