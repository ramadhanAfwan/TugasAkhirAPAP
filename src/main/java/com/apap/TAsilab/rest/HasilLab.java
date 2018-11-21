package com.apap.TAsilab.rest;

import java.sql.Date;

public class HasilLab {
	private String jenis;
	private String hasil;
	private Date tanggalPengajuan;
	public String getJenis() {
		return jenis;
	}
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	public String getHasil() {
		return hasil;
	}
	public void setHasil(String hasil) {
		this.hasil = hasil;
	}
	public Date getTanggalPengajuan() {
		return tanggalPengajuan;
	}
	public void setTanggalPengajuan(Date tanggalPengajuan) {
		this.tanggalPengajuan = tanggalPengajuan;
	}
	public PasienDetail getPasien() {
		return pasien;
	}
	public void setPasien(PasienDetail pasien) {
		this.pasien = pasien;
	}
	private PasienDetail pasien;
}
