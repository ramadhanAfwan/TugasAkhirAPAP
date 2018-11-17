package com.apap.TAsilab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.TAsilab.model.JenisPemeriksaanModel;
import com.apap.TAsilab.repository.JenisPemeriksaanDB;

@Service
@Transactional
public class JenisPemeriksaanServiceImpl implements JenisPemeriksaanService {

	@Autowired
	JenisPemeriksaanDB jenisPemeriksaanDb;
	
	@Override
	public JenisPemeriksaanModel findById(long id) {
		// TODO Auto-generated method stub
		return jenisPemeriksaanDb.findById(id);
	}

	
	
}
