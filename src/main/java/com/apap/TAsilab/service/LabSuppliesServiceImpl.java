package com.apap.TAsilab.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.TAsilab.model.LabSuppliesModel;
import com.apap.TAsilab.repository.LabSuppliesDB;

@Service
@Transactional
public class LabSuppliesServiceImpl implements LabSuppliesService {
	@Autowired
	LabSuppliesDB labSuppliesDb;

	@Override
	public List<LabSuppliesModel> getAllReagen() {
		List<LabSuppliesModel> allSupplies = labSuppliesDb.findAll();
		
		List<LabSuppliesModel> listReagen = new ArrayList<LabSuppliesModel>();
		for (LabSuppliesModel supplies :allSupplies) {
			if(supplies.getJenis().equalsIgnoreCase("reagen")) {
				listReagen.add(supplies);
			}
		}
		
		return listReagen;
	}

	@Override
	public LabSuppliesModel getSuppliesDetailById(long id) {
		return labSuppliesDb.findById(id).get();
	}
	
	@Override
	public List<LabSuppliesModel> getAllSupplies() {
		return labSuppliesDb.findAll();
	}
}