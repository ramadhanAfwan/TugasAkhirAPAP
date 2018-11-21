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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "jenis_pemeriksaan")
public class JenisPemeriksaanModel implements Serializable {
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
    @Column(name = "nama", nullable = false)
    private String nama;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "jenis_pemeriksaan_supplies", joinColumns = {@JoinColumn(name = "id_jenis_pemeriksaan")}, inverseJoinColumns = {@JoinColumn(name = "id_supplies")})
	private List<LabSuppliesModel> listSupplies = new ArrayList<>();
	
	@OneToMany(mappedBy = "jenisPemeriksaan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PemeriksaanModel> listPemeriksaan;

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
	
	@JsonIgnore
	public List<LabSuppliesModel> getListSupplies() {
		return listSupplies;
	}
	public void setListSupplies(List<LabSuppliesModel> listSupplies) {
		this.listSupplies = listSupplies;
	}
	@JsonIgnore
	public List<PemeriksaanModel> getListPemeriksaan() {
		return listPemeriksaan;
	}

	public void setListPemeriksaan(List<PemeriksaanModel> listPemeriksaan) {
		this.listPemeriksaan = listPemeriksaan;
	}
}
