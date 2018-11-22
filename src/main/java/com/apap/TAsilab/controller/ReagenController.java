package com.apap.TAsilab.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.TAsilab.model.KebutuhanReagenModel;
import com.apap.TAsilab.service.KebutuhanReagenService;

@Controller
@RequestMapping("/lab/kebutuhan")
public class ReagenController {

	@Autowired
	KebutuhanReagenService kebutuhanReagenService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String addUserSubmit(@ModelAttribute KebutuhanReagenModel reagen, Model model) {
		List<KebutuhanReagenModel> listKebutuhanReagen = kebutuhanReagenService.findAll();
		String status = "";
		if(reagen.getStatus()==0) {
			status = "aktif/belum beli";
		}
		else {
			status = "tidak aktif/telah dibeli";
		}
		model.addAttribute("title", "Kebutuhan Reagen");
		model.addAttribute("status", status);
		model.addAttribute("listKebutuhanReagen", listKebutuhanReagen);
		return "lihat-kebutuhan-reagen";
	}
	
	@RequestMapping(value = "/ubah/{id}", method = RequestMethod.GET)
	public String ubahStatus(@PathVariable(value= "id") int idReagen, Model model) {
		Optional<KebutuhanReagenModel> reagen = kebutuhanReagenService.findReagenById(idReagen);
		model.addAttribute("reagen", reagen);
		model.addAttribute("title", "Ubah Status Kebutuhan Reagen");
		return "ubah-kebutuhan-reagen";
	}

}
