package Modello;

import java.sql.Timestamp;

public class PhoneCall {

	String tipo;
	String number;
	String nomeContatto;
	Timestamp data_ora;
	String durata;
	
	PhoneCall(String type, Timestamp dateH, String phoneNumber, String duration, String contactName){
		tipo = type;
		number = phoneNumber;
		durata = duration;
		nomeContatto = contactName;
		data_ora = dateH;
	}
}
