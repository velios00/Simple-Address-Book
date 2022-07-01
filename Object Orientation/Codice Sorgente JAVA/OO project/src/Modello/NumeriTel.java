package Modello;

public class NumeriTel {

	String numero;
	String tipo;
	boolean reindirizzamento;
	
	public NumeriTel(String type, String number, String linkedNumber){
		numero = number;
		tipo = type;
		reindirizzamento = (linkedNumber.equals("t") || linkedNumber.equals("true"));
	}
	
	public String getNumber() {
		return numero;
	}	
	
	public String getType() {
		return tipo;
	}
}
