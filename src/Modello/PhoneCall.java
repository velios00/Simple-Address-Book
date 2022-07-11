package Modello;

import java.sql.Timestamp;

public class PhoneCall {

	String tipo;
	String number;
	String nomeContatto;
	String data_ora;
	String durata;
	
	public PhoneCall(String type, String dateH, String phoneNumber, String duration, String contactName){
		tipo = type;
		number = phoneNumber;
		durata = duration;
		nomeContatto = contactName;
		data_ora = dateH;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getDurata() {
		return durata;
	}
	
	public String getNomeContatto() {
		return nomeContatto;
	}
	
	public String getDataOra() {
		return data_ora;
	}
}