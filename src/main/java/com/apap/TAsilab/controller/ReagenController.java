package com.apap.TAsilab.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.TAsilab.model.KebutuhanReagenModel;
import com.apap.TAsilab.service.KebutuhanReagenService;
import com.apap.TAsilab.service.LabSuppliesService;

@Controller
@RequestMapping("/lab/kebutuhan")
public class ReagenController {

	@Autowired
	KebutuhanReagenService kebutuhanReagenService;
	
	@Autowired
	LabSuppliesService labSuppliesService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String addUserSubmit(@ModelAttribute KebutuhanReagenModel reagen, Model model) {
		
		
		return "lihat-kebutuhan-reagen";
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.GET)
	private String addPerencanaanReagen(Model model) {
		model.addAttribute("listReagen", labSuppliesService.getAllReagen());
		model.addAttribute("kebutuhanReagen", new KebutuhanReagenModel());
		return "add-perencanaan-reagen";
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	private String submitPerencanaanReagen(@ModelAttribute KebutuhanReagenModel kebutuhanReagen, Model model) {
		
		long millis=System.currentTimeMillis();
		Date todayDate = new Date(millis);
		kebutuhanReagen.setTanggalUpdate(todayDate);
		
		kebutuhanReagen.setStatus(0);
		
		kebutuhanReagen.setReagen(labSuppliesService.getSuppliesDetailById(kebutuhanReagen.getReagen().getId()));
		
		kebutuhanReagenService.add(kebutuhanReagen);;
		return "add-perencanaan-reagen";
	}
}
