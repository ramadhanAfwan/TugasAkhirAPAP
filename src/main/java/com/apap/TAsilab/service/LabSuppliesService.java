package com.apap.TAsilab.service;

import java.util.List;
import com.apap.TAsilab.model.LabSuppliesModel;

public interface LabSuppliesService {
	List<LabSuppliesModel> getAllReagen();
	LabSuppliesModel getSuppliesDetailById(int id);
	List<LabSuppliesModel> getAllSupplies();
	List<LabSuppliesModel> findLabSuppliesByJenis(String jenis);
	List<LabSuppliesModel> getListSupplies();
	void addSupplies(LabSuppliesModel labSup);
	LabSuppliesModel findById(int id);
	void updateLabSupplies(LabSuppliesModel labSupplies);
	boolean cekLabSupplies(List<LabSuppliesModel> list);
}
