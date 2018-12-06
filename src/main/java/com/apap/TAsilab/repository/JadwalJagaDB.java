package com.apap.TAsilab.repository;
import com.apap.TAsilab.model.JadwalJagaModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JadwalJagaDB extends JpaRepository<JadwalJagaModel, Long>{

	List<JadwalJagaModel> findByTanggal(String tanggal);
	JadwalJagaModel findById(long id);
}
