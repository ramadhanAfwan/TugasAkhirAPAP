package com.apap.TAsilab.service;

import java.util.List;
import com.apap.TAsilab.model.LabSuppliesModel;

public interface LabSuppliesService {
	List<LabSuppliesModel> findLabSuppliesByJenis(String jenis);
	List<LabSuppliesModel> getListSupplies();
	void addSupplies(LabSuppliesModel labSup);
}
