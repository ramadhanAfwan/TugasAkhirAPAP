package com.apap.TAsilab.service;


import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.rest.PasienDetail;
import com.apap.TAsilab.rest.PemeriksaanDetail;


public interface PemeriksaanService {
	PemeriksaanModel findPemeriksaanById(long id);
	List<PemeriksaanModel> findAll();
	void updatePemeriksaan(PemeriksaanModel pemeriksaan) throws java.text.ParseException;
	PasienDetail getPasien(int idPasien) throws ParseException;
	Map<Long, PasienDetail> getPatient() throws ParseException;
//	PemeriksaanDetail getPemeriksaan() throws ParseException;
	
	
}
