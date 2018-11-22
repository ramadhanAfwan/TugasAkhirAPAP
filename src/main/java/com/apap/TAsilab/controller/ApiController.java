package com.apap.TAsilab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.TAsilab.model.JadwalJagaModel;
import com.apap.TAsilab.model.JenisPemeriksaanModel;
import com.apap.TAsilab.model.KebutuhanReagenModel;
import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.repository.JadwalJagaDB;
import com.apap.TAsilab.repository.JenisPemeriksaanDB;
import com.apap.TAsilab.repository.KebutuhanReagenDB;
import com.apap.TAsilab.repository.PemeriksaanDB;
import com.apap.TAsilab.rest.BaseResponse;
import com.apap.TAsilab.rest.HasilLab;
import com.apap.TAsilab.rest.PasienDetail;

/**
 * 
 * Khusus untuk api fitur 8 dan
 * Implementasi api controller
 * Base url: 
 */
@RestController
public class ApiController {
	@Autowired
	PemeriksaanDB pemeriksaanDb;
	
	@Autowired
	KebutuhanReagenDB kebutuhanReagenDb;
	
	@Autowired
	JenisPemeriksaanDB jenisPeriksaDb;
	
	@Autowired
	JadwalJagaDB jadwalDb;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
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
	

	@GetMapping(value = "/lab/kebutuhan/perencanaan")
    public BaseResponse<List<KebutuhanReagenModel>> getAllKebutuhanReagen() {
        BaseResponse<List<KebutuhanReagenModel>> response = new BaseResponse<List<KebutuhanReagenModel>>();
        response.setStatus(200);
        response.setMessage("success");
        List<KebutuhanReagenModel> listKebutuhanReagen = kebutuhanReagenDb.findAll();
        
        // assign attribute yang tidak dibutuhkan dan akan merefer model lainnya menjadi null agar tidak terjadi error
        for(KebutuhanReagenModel kebutuhanReagen :listKebutuhanReagen) {
        	kebutuhanReagen.getReagen().setListJenisPemeriksaan(null);
        	kebutuhanReagen.getReagen().setListKebutuhanReagen(null);
        }
        response.setResult(listKebutuhanReagen);
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
		List<JenisPemeriksaanModel> model = jenisPeriksaDb.findAll();
		for (JenisPemeriksaanModel a:model) {
			a.setListSupplies(null);
			a.setListPemeriksaan(null);
		}
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
		List<JadwalJagaModel> model = jadwalDb.findAll();
		for (JadwalJagaModel a : model) {
//			a.setListPemeriksaan(null);
		}
		response.setResult(jadwalDb.findAll());
		return response;
	}
	/*
	 * Send API to SI - Appointment
	 * Fitur 10
	 */
	@PostMapping(value="/kirim/hasil-lab")
	public HasilLab addLabResult(@RequestParam (value="id") Long id) {
		PemeriksaanModel pemeriksaan = pemeriksaanDb.findById(id).get();
		HasilLab hasil = new HasilLab();
		PasienDetail pasien = new PasienDetail();
		pasien.setId(pemeriksaan.getIdPasien());
		hasil.setJenis(pemeriksaan.getJenisPemeriksaan().getNama());
		hasil.setHasil(pemeriksaan.getHasil().substring(1));
		hasil.setTanggalPengajuan(pemeriksaan.getTanggalPengajuan());
		hasil.setPasien(pasien);
		try {
			restTemplate.postForObject("http://si-appointment.herokuapp.com/api/addLabResult", hasil, ResponseEntity.class);
			return hasil;
		}
		catch(Exception e) {
			pemeriksaanDb.delete(pemeriksaan);
			return hasil;
		}
	}
}
