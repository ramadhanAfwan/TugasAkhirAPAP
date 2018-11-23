package com.apap.TAsilab.service;

import java.util.List;
import java.util.Optional;

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
	public List<KebutuhanReagenModel> findAll() {
		// TODO Auto-generated method stub
		return kebutuhanReagenDB.findAll();
	}

	@Override
	public Optional<KebutuhanReagenModel> findReagenById(Long idReagen) {
		// TODO Auto-generated method stub
		return kebutuhanReagenDB.findById(idReagen);
	}
}
