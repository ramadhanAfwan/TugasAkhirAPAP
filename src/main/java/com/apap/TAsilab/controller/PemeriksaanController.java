package com.apap.TAsilab.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.TAsilab.model.JenisPemeriksaanModel;
import com.apap.TAsilab.model.LabSuppliesModel;
import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.rest.PasienDetail;
import com.apap.TAsilab.service.JenisPemeriksaanService;
import com.apap.TAsilab.service.LabSuppliesService;
import com.apap.TAsilab.service.PemeriksaanService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class PemeriksaanController {

	@Autowired
	PemeriksaanService pemeriksaanService;
	
	@Autowired
	JenisPemeriksaanService jenisPemeriksaanService;
	
	@Autowired
	LabSuppliesService labService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@RequestMapping(value = "/lab/pemeriksaan/permintaan", method = RequestMethod.GET)
	public String viewAllPemeriksaan(Model model) throws Exception {
		List<PemeriksaanModel> listPemeriksaan = pemeriksaanService.findAll();
		Map<Integer, PasienDetail> mapPasien = pemeriksaanService.getPatient();
		
				
//		List<PasienDetail> listDataPasien = new ArrayList<PasienDetail>();
		
//		for(PemeriksaanModel pem : listPemeriksaan) {
//			Long pasienId = pem.getIdPasien();
//			String path = "http://si-appointment.herokuapp.com/api/getPasien/"+pasienId;
//			String response = restTemplate.getForEntity(path, String.class).getBody();
//			ObjectMapper mapper = new ObjectMapper();
//			JsonNode node = mapper.readTree(response);
//			JsonNode result = node.get("result");
//			PasienDetail pasien = mapper.treeToValue(result, PasienDetail.class);
//			listDataPasien.add(pasien);
//		}
		
		if(listPemeriksaan.size()==0) {
			model.addAttribute("header", "Tidak ada permintaan pemeriksaan");
		}
		else {
			model.addAttribute("listPemeriksaan", listPemeriksaan);
			model.addAttribute("mapPasien", mapPasien);
			model.addAttribute("header", "Daftar Permintaan Pemeriksaan");
			
		}
		model.addAttribute("title", "Daftar Pemeriksaan");
		return "lihat-daftar-pemeriksaan";
	}
	

	@RequestMapping(value = "/lab/pemeriksaan/{id}", method = RequestMethod.GET)
	public String ubahStatus(@PathVariable(value= "id") Long idPemeriksaan, Model model) {
		PemeriksaanModel pemeriksaan = pemeriksaanService.findPemeriksaanById(idPemeriksaan);

		// kondisi perubahan status dari proses menjadi selesai
		if(pemeriksaan.getStatus()==1) {
			// tambahin kondisi buat nampilin input hasil
			model.addAttribute("status", pemeriksaan.getStatus());
			model.addAttribute("old", pemeriksaan);
		}
		else {
			JenisPemeriksaanModel jenisPemeriksaan = jenisPemeriksaanService.findById(idPemeriksaan);
			List<LabSuppliesModel> lab = jenisPemeriksaan.getListSupplies();
			boolean stokAda = labService.cekLabSupplies(lab);
			if(stokAda==false) {
				model.addAttribute("msg", "Stok lab habis, Status tidak dapat dirubah");
				return "success-page";
			}
		}
		model.addAttribute("old", pemeriksaan);
		return "ubah-status";
	}
	/*
	 * 
	 * Revisi buat afwan
	 * Gua benerin supaya fitur gua bisa jalan
	 */
	@RequestMapping(value = "/lab/pemeriksaan", method = RequestMethod.POST)
	public String ubahStatusSubmit(@ModelAttribute PemeriksaanModel pemeriksaan, Model model) {
		// Kita bikin simple aja napah
		// Gua bikin implementasi di service pemeriksaan
		// Jangan lupa dipelajarin
		/*System.out.println(pemeriksaan.getId());
		System.out.println(pemeriksaan.getTanggalPemeriksaan());
		System.out.println(pemeriksaan.getTanggalPengajuan());
		System.out.println(pemeriksaan.getIdPasien());
		System.out.println(pemeriksaan.getStatus());
		System.out.println(pemeriksaan.getJadwalJaga().getId());
		System.out.println(pemeriksaan.getJenisPemeriksaan().getId());
		System.out.println(pemeriksaan.getHasil());*/
		pemeriksaanService.updatePemeriksaan(pemeriksaan);
		model.addAttribute("msg", "Status Pemeriksaan berhasil diubah");
		return "success-page";
	}
}
