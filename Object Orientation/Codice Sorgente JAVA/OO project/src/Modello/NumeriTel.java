package Modello;

public class NumeriTel {

	String numero;
	String tipo;
	boolean reindirizzamento;
	
	NumeriTel(String type, String number, String linkedNumber){
		numero = number;
		tipo = type;
		reindirizzamento = linkedNumber.equals("true");
	}
}
