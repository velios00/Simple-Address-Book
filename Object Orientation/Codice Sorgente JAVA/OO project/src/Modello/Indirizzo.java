package Modello;

public class Indirizzo {

	String strada;
	String CAP;
	String citta;
	String provincia;
	String nazione;
	
	Indirizzo(String street, String zipCode, String city, String province, String country){
		strada = street;
		CAP = zipCode;
		citta = city;
		provincia = province;
		nazione = country;
	}
}
