package com.apap.TAsilab.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KamarDetail {
	private int idPasien;
	private int requestPasien;
	private int assignKamar;
	
	
	public int getIdPasien() {
		return idPasien;
	}
	public void setIdPasien(int idPasien) {
		this.idPasien = idPasien;
	}
	public int getRequestPasien() {
		return requestPasien;
	}
	public void setRequestPasien(int requestPasien) {
		this.requestPasien = requestPasien;
	}
	public int getAssignKamar() {
		return assignKamar;
	}
	public void setAssignKamar(int assignKamar) {
		this.assignKamar = assignKamar;
	}
	
	
}
