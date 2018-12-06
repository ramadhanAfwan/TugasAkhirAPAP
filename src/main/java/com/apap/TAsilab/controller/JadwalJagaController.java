package com.apap.TAsilab.controller;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	private List<StaffDetail> getAllStaff() throws Exception{
		String path = Setting.allStaffUrl;
		List<StaffDetail> listDataStaff = new ArrayList<StaffDetail>();	
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
	
	//apa bedanya stafflist sama list data staff?
	private Map<Integer, String> getStaff() throws Exception, IOException, Throwable {
		List <StaffDetail> staffList  = this.getAllStaff();
		Map<Integer, String> infoStaff = new HashMap<Integer, String>();
		for(StaffDetail staff : staffList) {
			infoStaff.put(staff.getId(), staff.getNama());
		}
		return infoStaff;
	}
	
	//belum yg tambah banyak
	@RequestMapping(value = "/lab/jadwal-jaga/tambah", method = RequestMethod.GET)
	public String addJadwalJaga(Model model) throws Exception{		
	
		model.addAttribute("jadwalJaga", new JadwalJagaModel());
		List<StaffDetail> listStaff = this.getAllStaff();
		model.addAttribute("listStaff", listStaff);
		return "addJadwalJaga";
			
	}
	
	@RequestMapping(value = "/lab/jadwal-jaga/tambah", method = RequestMethod.POST)
	public String addJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga, Model model){

		//ini untuk handle agar date yang dimasukkan bukan date yang sudah berlalu
		if (jadwalJaga.getTanggal().before(new Date())) {
			model.addAttribute("msg", "date yang dimasukkan sudah berlalu");
			return "failed-date-passed";
		}
		else {
			jadwalJagaService.addJadwalJaga(jadwalJaga);
			model.addAttribute("msg", "jadwal berhasil ditambah");
			return "success-page";
		}
		
	}
	
	@RequestMapping(value = "/lab/jadwal-jaga/{tanggal}", method = RequestMethod.GET)
	public String lihatJadwalJaga(@PathVariable(value="tanggal") String tanggal, Model model) throws Throwable{
		
//		JadwalJagaModel jadwalJaga = null;
//		List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getAllJadwalJaga();
//		model.addAttribute("listJadwalJaga", listJadwalJaga);
		
		List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getJadwalJagaByTangal(tanggal);
		model.addAttribute("listJadwalJaga", listJadwalJaga);
		model.addAttribute("listStaff", this.getStaff());
		return "lihat-jadwal-jaga";
	}
	
	
	
	@RequestMapping(value = "/lab/jadwal-jaga/ubah/{id}", method = RequestMethod.GET)
	public String ubahJadwalJaga(@PathVariable(value="id") long id, Model model) throws Exception, Throwable{
		
		JadwalJagaModel oldJadwalJaga = jadwalJagaService.getJadwalJagaById(id);
		model.addAttribute("oldJadwalJaga", oldJadwalJaga);
		List<StaffDetail> listStaff = this.getAllStaff();
		model.addAttribute("listStaff", listStaff);
		return "ubah-jadwal-jaga";
		
		
	}
	
	//cek lagi seharusnya outputnya gimana
	@RequestMapping(value = "/lab/jadwal-jaga/ubah/{id}", method = RequestMethod.POST)
	public String ubahJadwalJagaSubmit(@PathVariable(value="id") long id, Model model, @ModelAttribute JadwalJagaModel newJadwalJaga){
		
		//ini untuk handle agar date yang dimasukkan bukan date yang sudah berlalu
		//kenapa yg di cek jadwal yg lama bukan jadwal yg barunya?
		if (newJadwalJaga.getTanggal().before(new Date())) {
			model.addAttribute("msg", "date yang dimasukkan sudah berlalu");
			return "failed-date-passed";
		}
		else {
			jadwalJagaService.ubahJadwalJaga(newJadwalJaga.getId(), newJadwalJaga);
			model.addAttribute("msg", "jadwal berhasil diubah");
			return "success-page";
		}	
		
		
	}	
	
	
}