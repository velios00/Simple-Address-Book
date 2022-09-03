package Modello;

// TODO: Auto-generated Javadoc
/**
 * Rappresenta un indirizzo fisico appartenente al contatto
 */
public class Indirizzo {

	/** stringa contenente nome della strada e numero civico dell’indirizzo. CAP (string): codice */
	String strada;
	
	/** codice postale formato da 5 caratteri (CAP) dell’indirizzo. */
	String CAP;
	
	/** nome della città all’interno dell’indirizzo. */
	String citta;
	
	/** identificativo di 2 caratteri della provincia relativa alla citt`a. */
	String provincia;
	
	/** indica se `e l’indirizzo principale del contatto. */
	String nazione;
	
	/** The main. */
	public boolean main;
	
	/**
	 * Instantiates a new indirizzo.
	 *
	 * @param street the street
	 * @param zipCode the zip code
	 * @param city the city
	 * @param province the province
	 * @param country the country
	 * @param principale the principale
	 */
	public Indirizzo(String street, String zipCode, String city, String province, String country, String principale){
		strada = street;
		CAP = zipCode;
		citta = city;
		provincia = province;
		nazione = country;
		main = (principale.equals("t")  || principale.equals("true"));
	}
	
	/**
	 * Gets the string.
	 *
	 * @return the string
	 */
	public String getString() {
		return new String("<html>"+strada+"<br/>"+CAP+", "+citta+" ("+provincia+")"+"<br/>"+nazione+"</html>");
	}
}
