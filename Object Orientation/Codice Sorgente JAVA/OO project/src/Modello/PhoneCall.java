package Modello;

import java.sql.Timestamp;

// TODO: Auto-generated Javadoc
/**
 * Rappresenta una chiamata effettuata, ricevuta o persa da un utente.
 */
public class PhoneCall {

	/** Tipo della chiamata. Può essere effettuata (SENT), ricevuta (ENTERED) o persa (MISSED). */
	String tipo;
	
	/** stringa contenente il numero di telefono relativo alla chiamata. */
	String number;
	
	/** Nome e cognome del contatto o dei contatti associati al numero di telefono.
	 *  Vale sconosciuto (UNKNOWN) se il numero non `e presente in rubrica. */
	String nomeContatto;
	
	/** Stringa contenente data e ora della chiamata. */
	String data_ora;
	
	/** Durata in secondi della chiamata. Vale 0 se la chiamata è persa. */
	String durata;
	
	/**
	 * Instantiates a new phone call.
	 *
	 * @param type the type
	 * @param dateH the date H
	 * @param phoneNumber the phone number
	 * @param duration the duration
	 * @param contactName the contact name
	 */
	public PhoneCall(String type, String dateH, String phoneNumber, String duration, String contactName){
		tipo = type;
		number = phoneNumber;
		durata = duration;
		nomeContatto = contactName;
		data_ora = dateH;
	}
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Gets the durata.
	 *
	 * @return the durata
	 */
	public String getDurata() {
		return durata;
	}
	
	/**
	 * Gets the nome contatto.
	 *
	 * @return the nome contatto
	 */
	public String getNomeContatto() {
		return nomeContatto;
	}
	
	/**
	 * Gets the data ora.
	 *
	 * @return the data ora
	 */
	public String getDataOra() {
		return data_ora;
	}
}