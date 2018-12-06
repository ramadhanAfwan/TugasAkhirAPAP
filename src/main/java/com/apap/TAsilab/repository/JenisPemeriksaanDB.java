package com.apap.TAsilab.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.TAsilab.model.JenisPemeriksaanModel;

public interface JenisPemeriksaanDB extends JpaRepository<JenisPemeriksaanModel, Integer> {
	JenisPemeriksaanModel findById(int id);
}
