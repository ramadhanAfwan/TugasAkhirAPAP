package com.apap.TAsilab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		
		return "lihat-kebutuhan-reagen";
	}
	

}
