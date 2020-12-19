package com.LH.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class KhoaHocService {
	@Autowired
	private KhoaHocRepository khoaHocRepository;
	
	public Page<KhoaHoc> listAll(int pageNumber,String sortField,String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1,5,sort);
		return khoaHocRepository.findAll(pageable);
	}
	
	public List<KhoaHoc> listKhoaHocTrangChu(){
		return khoaHocRepository.findAll();
	}
	
	public KhoaHoc SaveKhoaHoc(KhoaHoc khoaHoc) {
		return khoaHocRepository.save(khoaHoc);
	}
	
	public KhoaHoc getKhoahoc(String id) {
		return khoaHocRepository.findById(id).get();
	}
	
	public void delete(String id) {
		khoaHocRepository.deleteById(id);
	}
}
