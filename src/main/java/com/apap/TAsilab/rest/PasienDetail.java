package com.apap.TAsilab.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasienDetail {
	private int id;
	private String nama;
	private Date tanggalRujukan;
	private String poliRujukan;
	private StatusPasien statusPasien;
	
	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Date getTanggalRujukan() {
		return tanggalRujukan;
	}

	public void setTanggalRujukan(Date tanggalRujukan) {
		this.tanggalRujukan = tanggalRujukan;
	}

	public String getPoliRujukan() {
		return poliRujukan;
	}

	public void setPoliRujukan(String poliRujukan) {
		this.poliRujukan = poliRujukan;
	}

	public StatusPasien getStatusPasien() {
		return statusPasien;
	}

	public void setStatusPasien(StatusPasien statusPasien) {
		this.statusPasien = statusPasien;
	}

	
	
}
