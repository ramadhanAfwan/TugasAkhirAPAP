package com.apap.TAsilab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apap.TAsilab.model.KebutuhanReagenModel;
import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.repository.KebutuhanReagenDB;
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
	KebutuhanReagenDB kebutuhanReagenDb;
	
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
        response.setResult(kebutuhanReagenDb.findAll());
        return response;
    }
}
