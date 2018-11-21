package com.apap.TAsilab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.TAsilab.model.LabSuppliesModel;
import com.apap.TAsilab.repository.LabSuppliesDB;

@Service
@Transactional
public class LabSuppliesServiceImpl implements LabSuppliesService{
	@Autowired

	private LabSuppliesDB LabSuppliesDb;
	
	@Override
	public List<LabSuppliesModel> findLabSuppliesByJenis(String jenis) {
		// TODO Auto-generated method stub
		return LabSuppliesDb.findByJenis(jenis);
	}

	@Override
	public List<LabSuppliesModel> getListSupplies() {
		// TODO Auto-generated method stub
		return LabSuppliesDb.findAll();
	}

	@Override
	public void addSupplies(LabSuppliesModel labSup) {
		// TODO Auto-generated method stub
		LabSuppliesDb.save(labSup);
	}

	@Override
	public LabSuppliesModel findById(long id) {
		// TODO Auto-generated method stub
		return LabSuppliesDb.getOne(id);
	}

	@Override
	public void updateLabSupplies(LabSuppliesModel labSupplies) {
		// TODO Auto-generated method stub
		LabSuppliesDb.save(labSupplies);
	}

	@Override
	public boolean cekLabSupplies(List<LabSuppliesModel> list) {
		for(LabSuppliesModel a: list) {
			// Kondisi jika lab supplies
			//untuk jenis pemeriksaan tertentu sudah mendekati habis == 1
			// munculkan message error pada template lihat-daftar-pemeriksaan
			if(a.getJumlah()==1) {
				return false;
			}
		}
		return true;
	}

}
