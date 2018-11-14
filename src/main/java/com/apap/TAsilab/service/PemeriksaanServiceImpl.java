package com.apap.TAsilab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.repository.PemeriksaanDB;


@Service
@Transactional
public class pemeriksaanServiceImpl implements PemeriksaanService{
	
	@Autowired
	private PemeriksaanDB pemeriksaanDb;
	@Override
	public PemeriksaanModel findPemeriksaanById(long id) {
		// TODO Auto-generated method stub
		return pemeriksaanDb.findById(id).get();
	}

}
