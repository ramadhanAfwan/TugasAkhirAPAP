package com.apap.TAsilab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lab_supplies")
public class LabSuppliesModel implements Serializable {
	// Abaikan attribute ini
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
    @Size(max = 255)
    @Column(name = "jenis", nullable = false)
    private String jenis;
	
	@NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;
	
	@NotNull
    @Column(name = "jumlah", nullable = false)
    private int jumlah;
	
    @Size(max = 255)
    @Column(name = "deskripsi", nullable = true)
    private String deskripsi;
    
    @OneToMany(mappedBy = "reagen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    
    private List<KebutuhanReagenModel> listKebutuhanReagen;
    
    @ManyToMany(mappedBy = "listSupplies")
    private List<JenisPemeriksaanModel> listJenisPemeriksaan = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public List<KebutuhanReagenModel> getListKebutuhanReagen() {
		return listKebutuhanReagen;
	}

	public void setListKebutuhanReagen(List<KebutuhanReagenModel> listKebutuhanReagen) {
		this.listKebutuhanReagen = listKebutuhanReagen;
	}

	public List<JenisPemeriksaanModel> getListJenisPemeriksaan() {
		return listJenisPemeriksaan;
	}

	public void setListJenisPemeriksaan(List<JenisPemeriksaanModel> listJenisPemeriksaan) {
		this.listJenisPemeriksaan = listJenisPemeriksaan;
	}
}
