package br.com.senai.model;

public class ClienteModel {

	//Atributo
	private String nome;

	
	//Construtor
	public ClienteModel() {
	}
	
	public ClienteModel(String nome) {
		super();
		this.nome = nome;
	}
	
	//Metodos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
