package com.apap.TAsilab.controller;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.TAsilab.model.JenisPemeriksaanModel;
import com.apap.TAsilab.model.LabSuppliesModel;
import com.apap.TAsilab.model.PemeriksaanModel;
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
	
	
	@RequestMapping(value = "/lab/pemeriksaan/permintaan", method = RequestMethod.GET)
	public String viewAllPemeriksaan(Model model) {
		
		List<PemeriksaanModel> listPemeriksaan = pemeriksaanService.findAll();
		
		model.addAttribute("title", "Daftar Pemeriksaan");
		model.addAttribute("listPemeriksaan", listPemeriksaan);
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
