package com.apap.TAsilab.service;

import java.util.List;
import java.util.Optional;

import com.apap.TAsilab.model.KebutuhanReagenModel;

public interface KebutuhanReagenService {
	void add(KebutuhanReagenModel kebutuhanReagen);
	List<KebutuhanReagenModel> findAll();
	Optional<KebutuhanReagenModel> findReagenById(Long idReagen);
}
