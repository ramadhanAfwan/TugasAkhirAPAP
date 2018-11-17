package com.apap.TAsilab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.TAsilab.model.LabSuppliesModel;
import com.apap.TAsilab.service.LabSuppliesService;

@Controller
public class LabSuppliesController {
	@Autowired
	private LabSuppliesService labSupService;

	@RequestMapping(value = "/lab/stok/tambah", method = RequestMethod.GET)
	public String tambahStok(Model model) {
		LabSuppliesModel labSup = new LabSuppliesModel();
		model.addAttribute("labSupplies", labSup);
		return "addPersediaan";
	}
	@RequestMapping(value = "/lab/stok/tambah", method = RequestMethod.POST)
	public String tambahStokSubmit(@ModelAttribute LabSuppliesModel LabSupplies, Model model) {
		labSupService.addSupplies(LabSupplies);
		model.addAttribute("msg", "Data berhasil ditambahkan");
		return "success-page";
	}
	@RequestMapping("/lab/stok")
	public String lihatStok(Model model) {
		model.addAttribute("allSupplies", labSupService.getListSupplies());
		return "allPersediaan";
	}
}
