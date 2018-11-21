package com.apap.TAsilab.service;


import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.rest.PasienDetail;


public interface PemeriksaanService {
	PemeriksaanModel findPemeriksaanById(long id);
	List<PemeriksaanModel> findAll();
	void updatePemeriksaan(PemeriksaanModel pemeriksaan);
	PasienDetail getPasien(long idPasien) throws ParseException;
	Map<Long, PasienDetail> getPasienPemeriksaan() throws ParseException;
	
	
}
