package com.apap.TAsilab.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String viewAllPemeriksaan(Model model) throws ParseException {
		List<PemeriksaanModel> listPemeriksaan = pemeriksaanService.findAll();
		Map<Integer, PasienDetail> mapPasien = pemeriksaanService.getPatient();
		
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
	public String ubahStatus(@PathVariable(value= "id") int idPemeriksaan, Model model) throws ParseException {
		PemeriksaanModel pemeriksaan = pemeriksaanService.findPemeriksaanById(idPemeriksaan);
		Map<Integer, PasienDetail> mapPasien = pemeriksaanService.getPatient();

//		String[] status = {"Menunggu Persetujuan","Diproses","Selesai"};
//		List<String> listStatus = Arrays.asList(status);
		
		// kondisi perubahan status dari proses menjadi selesai
		if(pemeriksaan.getStatus()==1) {
			// tambahin kondisi buat nampilin input hasil
//			model.addAttribute("status", pemeriksaan.getStatus());
			model.addAttribute("old", pemeriksaan);
			model.addAttribute("mapPasien", mapPasien);
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
//		model.addAttribute("listStatus", listStatus);
		model.addAttribute("mapPasien", mapPasien);
		model.addAttribute("old", pemeriksaan);
		return "ubah-status";
	}

	
	@RequestMapping(value = "/lab/pemeriksaan", method = RequestMethod.POST)
	public String ubahStatusSubmit(@ModelAttribute PemeriksaanModel pemeriksaan, Model model) throws ParseException {
		pemeriksaanService.updatePemeriksaan(pemeriksaan);
		model.addAttribute("msg", "Status Pemeriksaan berhasil diubah");
		return "success-page";
	}
}
