package com.apap.TAsilab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.apap.TAsilab.model.JadwalJagaModel;
import com.apap.TAsilab.rest.Setting;
import com.apap.TAsilab.rest.StaffDetail;
import com.apap.TAsilab.service.JadwalJagaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class JadwalJagaController {
	@Autowired
	private JadwalJagaService jadwalJagaService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@RequestMapping(value = "/lab/jadwal-jaga/tambah", method = RequestMethod.GET)
	public String addJadwalJaga(Model model) throws Exception{
		model.addAttribute("jadwalJaga", new JadwalJagaModel());
		List<StaffDetail> listStaff = this.getAllStaff();
//		for(StaffDetail staff: listStaff) {
//			System.out.println(staff.getNama());
//		}
		model.addAttribute("listStaff", listStaff);
		return "addJadwalJaga";
	}
	
	@RequestMapping(value = "/lab/jadwal-jaga/tambah", method = RequestMethod.POST)
	public String addJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga, Model model){
//		System.out.println("masuk");
//		jadwalJaga.setWaktuMulai("00:00:00");
//		jadwalJaga.setWaktuSelesai("00:00:00");
		jadwalJagaService.addJadwalJaga(jadwalJaga);
		model.addAttribute("msg", "jadwal berhasil ditambah");
		return "success-page";
	}
	
	private List<StaffDetail> getAllStaff() throws Exception{
		String path = Setting.allStaffUrl;
		List<StaffDetail> listDataStaff = new ArrayList<StaffDetail>();
		/*
		List<StaffDetail> listStaff = mapper.readValue(path, new TypeReference<List<StaffDetail>>() {});
		return listStaff;*/		
		String responsenya = restTemplate.getForEntity(path, String.class).getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(responsenya);
		JsonNode result = node.get("result");
		for(int i=0; i<result.size(); i++) {
			StaffDetail staffs = mapper.treeToValue(result.get(i), StaffDetail.class);
			listDataStaff.add(staffs);
		}		
		return listDataStaff;
		
	}
	
	@RequestMapping(value = "/lab/jadwal-jaga/{tanggal}", method = RequestMethod.GET)
	public String lihatJadwalJaga(Model model) throws Exception{
		return "lihat-jadwal-jaga";
	}
	
	@RequestMapping(value = "/lab/jadwal-jaga/ubah/{id}", method = RequestMethod.GET)
	public String ubahJadwalJaga(Model model) throws Exception{
		return "ubah-jadwal-jaga";
	}

}
