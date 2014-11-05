package com.example.googlemaps;

public class Restaurante {

	private String nome;
	private String cidade;
	private Double latitude;
	private Double longitude;
	
	public Restaurante(String nome, String cidade, Double latitude, Double longitude) {
		this.nome = nome;
		this.cidade = cidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getNome() {
		return nome;
	}

	public String getCidade() {
		return cidade;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}	
	
	
}
