package com.apap.TAsilab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.TAsilab.model.KebutuhanReagenModel;
import com.apap.TAsilab.repository.KebutuhanReagenDB;

@Service
@Transactional
public class KebutuhanReagenServiceImpl implements KebutuhanReagenService {
	@Autowired
	private KebutuhanReagenDB kebutuhanReagenDB;

	@Override
	public void add(KebutuhanReagenModel kebutuhanReagen) {
		kebutuhanReagenDB.save(kebutuhanReagen);
		
	}
}
