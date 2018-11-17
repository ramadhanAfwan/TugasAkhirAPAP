package com.apap.TAsilab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.service.PemeriksaanService;



@Controller
public class PageController {
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/lab/stok/tambah")
	public String tambahStok() {
		return "addPersediaan";
	}
	@RequestMapping("/lab/stok")
	public String lihatStok() {
		return "allPersediaan";
	}
	
}
