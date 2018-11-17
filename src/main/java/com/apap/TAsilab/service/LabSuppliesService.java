package com.apap.TAsilab.service;

import java.util.List;

import com.apap.TAsilab.model.LabSuppliesModel;

public interface LabSuppliesService {
	List<LabSuppliesModel> getAllReagen();
	LabSuppliesModel getSuppliesDetailById(long id);
}
