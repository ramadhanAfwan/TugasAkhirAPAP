package com.apap.TAsilab.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.TAsilab.model.JenisPemeriksaanModel;
import com.apap.TAsilab.model.LabSuppliesModel;
import com.apap.TAsilab.model.PemeriksaanModel;
import com.apap.TAsilab.repository.JenisPemeriksaanDB;
import com.apap.TAsilab.repository.PemeriksaanDB;
import com.apap.TAsilab.rest.PasienDetail;
import com.apap.TAsilab.rest.StatusPasien;


@Service
@Transactional
public class PemeriksaanServiceImpl implements PemeriksaanService{
	
	@Autowired
	private PemeriksaanDB pemeriksaanDb;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JenisPemeriksaanDB jenisPemeriksaanDb;
	
	@Override
	public PasienDetail getPasien(int idPasien) throws ParseException {
		PasienDetail pasien = new PasienDetail();
		JSONParser parser = new JSONParser();
		String response = restTemplate.getForObject("http://si-appointment.herokuapp.com/api/getPasien/"+idPasien, String.class);
        System.out.println(response);
        JSONObject json = (JSONObject) parser.parse(response);
        JSONObject result = (JSONObject) json.get("result");
        String nama = (String) result.get("nama");
        long id_pasien = (long) result.get("id");
        pasien.setId(id_pasien);
        pasien.setNama(nama);
        
        return pasien;
	}
	
	@Override
	public Map<Integer, PasienDetail> getPatient() throws ParseException {
		Map<Integer, PasienDetail> mapPasien = new HashMap<Integer, PasienDetail>();
		List<PemeriksaanModel> listPemeriksaan = pemeriksaanDb.findAll();
		for (PemeriksaanModel pemeriksaan : listPemeriksaan){
			PasienDetail pasien = this.getPasien((int)pemeriksaan.getIdPasien());
			mapPasien.put((int) pemeriksaan.getJenisPemeriksaan().getId(), pasien);
		}
		return mapPasien;
	}
	
//	@Override
//	public String getRest(String url) throws ParseException{
//		String response = restTemplate.getForObject(url, String.class);
//        return response;
//	}
//	
//	@Override
//	public PasienDetail parsePasien(String data) throws ParseException {
//		JSONParser parser = new JSONParser();
//		JSONObject json = (JSONObject) parser.parse(data);
//		JSONObject pasienJson = (JSONObject) json.get("result");
//		System.out.println(pasienJson);
//		JSONObject statusPasien = (JSONObject) pasienJson.get("statusPasien");
//		StatusPasien status = new StatusPasien();
//		status.setId((int)statusPasien.get("id"));
//		status.setJenis((String) statusPasien.get("jenis"));
//		PasienDetail pasien = new PasienDetail();
//		pasien.setId((int)pasienJson.get("id"));
//		pasien.setNama((String) pasienJson.get("nama"));
//		pasien.setStatusPasien(status);
//		System.out.println(pasien.getNama());
//		return pasien;
//	}
	
	@Override
	public PemeriksaanModel findPemeriksaanById(long id) {
		// TODO Auto-generated method stub
		return pemeriksaanDb.findById(id).get();
	}

	@Override
	public List<PemeriksaanModel> findAll() {
		// TODO Auto-generated method stub
		return pemeriksaanDb.findAll();
	}

	@Override
	public void updatePemeriksaan(PemeriksaanModel pemeriksaan) {
		JenisPemeriksaanModel jp = jenisPemeriksaanDb.findById(pemeriksaan.getId());
		for (LabSuppliesModel a: jp.getListSupplies()){
			a.setJumlah(a.getJumlah()-1);
		}
		pemeriksaanDb.save(pemeriksaan);
	}


}
