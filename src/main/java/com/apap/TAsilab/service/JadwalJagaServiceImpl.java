package com.apap.TAsilab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.TAsilab.model.JadwalJagaModel;
import com.apap.TAsilab.repository.JadwalJagaDB;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService {
	
	@Autowired
	private JadwalJagaDB jadwalJagaDB;
	
	@Override
	public void addJadwalJaga(JadwalJagaModel jadwalJaga) {
		jadwalJagaDB.save(jadwalJaga);
	}

	@Override
	public List<JadwalJagaModel> getJadwalAll() {
		// TODO Auto-generated method stub
		return jadwalJagaDB.findAll();
	}

}
