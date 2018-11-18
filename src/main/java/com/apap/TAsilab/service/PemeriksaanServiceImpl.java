package com.apap.TAsilab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.TAsilab.model.JenisPemeriksaanModel;
import com.apap.TAsilab.model.LabSuppliesModel;
import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.repository.JenisPemeriksaanDB;
import com.apap.TAsilab.repository.PemeriksaanDB;


@Service
@Transactional
public class PemeriksaanServiceImpl implements PemeriksaanService{
	
	@Autowired
	private PemeriksaanDB pemeriksaanDb;
	
	@Autowired
	private JenisPemeriksaanDB jenisPemeriksaanDb;
	
	@Override
	public PemeriksaanModel findPemeriksaanById(long id) {
		// TODO Auto-generated method stub
		return pemeriksaanDb.findById(id).get();
	}

	@Override
	public List<PemeriksaanModel> findAll() {
		// TODO Auto-generated method stub
		return pemeriksaanDb.findAll();
	}

	@Override
	public void updatePemeriksaan(PemeriksaanModel pemeriksaan) {
		JenisPemeriksaanModel jp = jenisPemeriksaanDb.findById(pemeriksaan.getId());
		for (LabSuppliesModel a: jp.getListSupplies()){
			a.setJumlah(a.getJumlah()-1);
		}
		pemeriksaanDb.save(pemeriksaan);
	}

}
