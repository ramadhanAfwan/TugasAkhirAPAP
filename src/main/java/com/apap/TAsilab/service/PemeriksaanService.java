package com.apap.TAsilab.service;


import java.util.List;

import com.apap.TAsilab.model.PemeriksaanModel;


public interface PemeriksaanService {
	PemeriksaanModel findPemeriksaanById(int id);
	List<PemeriksaanModel> findAll();
	void updatePemeriksaan(PemeriksaanModel pemeriksaan);
	
	
}
