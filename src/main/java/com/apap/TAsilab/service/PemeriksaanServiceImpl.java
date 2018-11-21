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
import com.apap.TAsilab.rest.Setting;


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

	@Override
	public PasienDetail getPasien(long idPasien) throws ParseException {
		PasienDetail pasien = new PasienDetail();
		
		JSONParser parser = new JSONParser();
		String path = Setting.appointmentUrl+"{"+idPasien+"}";
		String jsonParse = (String) restTemplate.getForObject(path, Object.class);
		JSONObject json = (JSONObject) parser.parse(jsonParse);
		JSONObject result = (JSONObject) parser.parse((String) json.get("result"));
		String namaPasien = (String) result.get("nama");
		Long id = (Long) result.get("id");
		
		pasien.setId(id);
		pasien.setNama(namaPasien);
    	return pasien;
	}

	@Override
	public Map<Long, PasienDetail> getPasienPemeriksaan() throws ParseException {
		List<PemeriksaanModel> pemeriksaan = pemeriksaanDb.findAll();
		Map<Long, PasienDetail> pasienPemeriksaan = new HashMap<Long,PasienDetail>();
		for(PemeriksaanModel pem : pemeriksaan) {
			PasienDetail pasien = this.getPasien(pem.getIdPasien());
			pasienPemeriksaan.put(pem.getIdPasien(),pasien);
		}
		return pasienPemeriksaan;
	}
}
