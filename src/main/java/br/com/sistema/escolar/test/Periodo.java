package br.com.sistema.escolar.test;

public enum Periodo {

	MANHA("Manhã"), TARDE("Tarde"), NOTE("Noite");
	
	private final String periodo;
	
	private Periodo(String periodo){
		this.periodo = periodo;
	}
	
	public String getPeriodo() {
		return this.periodo;
	}
}
