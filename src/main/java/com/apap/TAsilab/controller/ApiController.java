package com.apap.TAsilab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.model.JadwalJagaModel;
import com.apap.TAsilab.model.JenisPemeriksaanModel;
import com.apap.TAsilab.repository.JadwalJagaDB;
import com.apap.TAsilab.repository.JenisPemeriksaanDB;
import com.apap.TAsilab.repository.PemeriksaanDB;
import com.apap.TAsilab.rest.BaseResponse;

/**
 * 
 * Khusus untuk api fitur 8 dan ---
 * Implementasi api controller
 * Base url: 
 */
@RestController
public class ApiController {
	@Autowired
	PemeriksaanDB pemeriksaanDb;
	
	@Autowired
	JenisPemeriksaanDB jenisPeriksaDb;
	
	@Autowired
	JadwalJagaDB jadwalDb;
	/*
	 * API Untuk
	 * Menerima request pemeriksaan
	 */
	@PostMapping(value="/lab/pemeriksaan/permintaan")
	public BaseResponse<PemeriksaanModel> addPermintaanPemeriksaan(@RequestBody PemeriksaanModel pemeriksaanLab, BindingResult bindingResult){
		BaseResponse<PemeriksaanModel> response = new BaseResponse<PemeriksaanModel>();
		if (bindingResult.hasErrors()) {
			response.setStatus(500);
			response.setMessage("Error Data");
		}
		else {
			pemeriksaanLab.setStatus(0);
			pemeriksaanLab.setHasil("Belum Ada Hasil");
			pemeriksaanDb.save(pemeriksaanLab);
			response.setStatus(200);
			response.setMessage("Success");
			response.setResult(pemeriksaanLab);
		}
		return response;
	}
	
	
	/*
	 * API untuk
	 * Memberikan daftar jenis pemeriksaan yang disediakan laboratorium
	 */
	@GetMapping(value="/getAllJenisPemeriksaan")
	public BaseResponse<List<JenisPemeriksaanModel>> viewJenisPemeriksaan(){
		BaseResponse<List<JenisPemeriksaanModel>> response = new BaseResponse<List<JenisPemeriksaanModel>>();
		response.setStatus(200);
		response.setMessage("Success");
		response.setResult(jenisPeriksaDb.findAll());
		return response;
	}
	
	
	/*
	 * API untuk
	 * Memberikan jadwal lab yang dapat dipilih oleh sistem rawat jalan
	 */
	@GetMapping(value="/getAllJadwal")
	public BaseResponse<List<JadwalJagaModel>> viewJadwalJaga(){
		BaseResponse<List<JadwalJagaModel>> response = new BaseResponse<List<JadwalJagaModel>>();
		response.setStatus(200);
		response.setMessage("Success");
		response.setResult(jadwalDb.findAll());
		return response;
	}
}
