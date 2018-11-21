package com.apap.TAsilab.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasienDetail {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nama")
	private String nama;
	
	@JsonProperty("tanggalRujukan")
	private Date tanggalRujukan;
	
	@JsonProperty("poliRujukan")
	private String poliRujukan;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
}
