package com.apap.TAsilab.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.apap.TAsilab.model.JadwalJagaModel;

@Service
public interface JadwalJagaService {

	void addJadwalJaga(JadwalJagaModel jadwalJaga);

	List<JadwalJagaModel> getJadwalJagaByTangal(String tanggal);

	JadwalJagaModel getJadwalJagaById(long id);

	void ubahJadwalJaga(long id, JadwalJagaModel newJadwalJaga);
	
	
}
