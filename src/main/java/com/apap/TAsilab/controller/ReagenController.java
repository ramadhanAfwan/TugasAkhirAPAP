package com.apap.TAsilab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.TAsilab.service.KebutuhanReagenService;

@Controller
@RequestMapping("/lab/kebutuhan")
public class ReagenController {
	@Autowired
	KebutuhanReagenService kebutuhanReagenService;
}
