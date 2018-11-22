package com.apap.TAsilab.service;

import java.util.List;
import java.util.Optional;

import com.apap.TAsilab.model.KebutuhanReagenModel;

public interface KebutuhanReagenService {

	List<KebutuhanReagenModel> findAll();
	Optional<KebutuhanReagenModel> findReagenById(int idReagen);

}
