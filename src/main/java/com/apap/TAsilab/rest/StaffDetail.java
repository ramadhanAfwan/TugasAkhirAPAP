package com.apap.TAsilab.rest;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class StaffDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@NotNull
    @Column(name = "nama", nullable = false)
    private String nama;
	
	@NotNull
    @Column(name = "jenis", nullable = false)
    private int jenis;

	public int getJenis() {
		return jenis;
	}

	public void setJenis(int jenis) {
		this.jenis = jenis;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}


	
	

}
