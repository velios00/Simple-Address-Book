package Modello;

public class MessagingAccount {
	
	String supplierName;
	String nickName;
	String assignedEmail;
	String stato;
	
	MessagingAccount(String supplier, String nickname, String assEmail, String status){
		supplierName = supplier;
		nickName = nickname;
		assignedEmail = assEmail;
		stato = status;
	}
}
