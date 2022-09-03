package Modello;

// TODO: Auto-generated Javadoc
/**
 * Descrive un eventuale account di messaging collegato alla e-mail del contatto
 */
public class MessagingAccount {
	
	/** Nome del fornitore dell’app di messaging. */
	String supplierName;
	
	/** Nome identificativo dell’account del contatto. */
	String nickName;
	
	/** Frase di benvenuto o stato personale dell’account. */
	String stato;
	
	/**
	 * Instantiates a new messaging account.
	 *
	 * @param supplier the supplier
	 * @param nickname the nickname
	 * @param status the status
	 */
	public MessagingAccount(String supplier, String nickname, String status){
		supplierName = supplier;
		nickName = nickname;
		stato = status;
	}
	
	public String toString() {
		return supplierName+": "+nickName+ " - " + stato;
	}
	
}
