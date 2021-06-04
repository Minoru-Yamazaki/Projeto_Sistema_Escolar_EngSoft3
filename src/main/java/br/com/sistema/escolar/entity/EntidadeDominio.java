package br.com.sistema.escolar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntidadeDominio {

	private Integer id = null;
	private String nome;
	
	public EntidadeDominio(String nome) {
		this.nome = nome;
	}	
}
