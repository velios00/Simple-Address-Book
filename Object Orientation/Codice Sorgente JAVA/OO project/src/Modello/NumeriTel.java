package Modello;

// TODO: Auto-generated Javadoc
/**
 * Rappresenta un numero di telefono associato ad un contatto.
 */
public class NumeriTel {

	/** Stringa contenente il numero di telefono. */
	String numero;
	
	/** Tipo di riferimento telefonico. Pu`o essere mobile (MOBILE) oppure fisso (LANDLINE). */
	String tipo;
	
	/** indica se è un numero di reindirizzamento. */
	boolean reindirizzamento;
	
	/**
	 * Instantiates a new numeri tel.
	 *
	 * @param type the type
	 * @param number the number
	 * @param linkedNumber the linked number
	 */
	public NumeriTel(String type, String number, String linkedNumber){
		numero = number;
		tipo = type;
		reindirizzamento = (linkedNumber.equals("t") || linkedNumber.equals("true"));
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return numero;
	}	
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return tipo;
	}
	
	/**
	 * If linked.
	 *
	 * @return true, if successful
	 */
	public boolean ifLinked() {
		return reindirizzamento;
	}
}
