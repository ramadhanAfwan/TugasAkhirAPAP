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

}
