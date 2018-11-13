package com.apap.TAsilab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

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
