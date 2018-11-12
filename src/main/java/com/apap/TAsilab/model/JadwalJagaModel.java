package com.apap.TAsilab.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jadwal_jaga")
public class JadwalJagaModel implements Serializable {
	// Abaikan attribute ini
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@NotNull
    @Column(name = "tanggal", nullable = false)
    private Date tanggal;
	
	@NotNull
    @Column(name = "waktu_mulai", nullable = false)
    private Time waktuMulai;
	
	@NotNull
    @Column(name = "waktu_selesai", nullable = false)
    private Time waktuSelesai;
	
	// id staff akan digunakan untuk mengambil object staff dengan API di SI_Appointment
	@NotNull
    @Column(name = "id_staff", nullable = false)
    private long idStaff;
	
	@OneToMany(mappedBy = "jadwalJaga", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PemeriksaanModel> listPemeriksaan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public Time getWaktuMulai() {
		return waktuMulai;
	}

	public void setWaktuMulai(Time waktuMulai) {
		this.waktuMulai = waktuMulai;
	}

	public Time getWaktuSelesai() {
		return waktuSelesai;
	}

	public void setWaktuSelesai(Time waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}

	public long getIdStaff() {
		return idStaff;
	}

	public void setIdStaff(long idStaff) {
		this.idStaff = idStaff;
	}

	public List<PemeriksaanModel> getListPemeriksaan() {
		return listPemeriksaan;
	}

	public void setListPemeriksaan(List<PemeriksaanModel> listPemeriksaan) {
		this.listPemeriksaan = listPemeriksaan;
	}
}
