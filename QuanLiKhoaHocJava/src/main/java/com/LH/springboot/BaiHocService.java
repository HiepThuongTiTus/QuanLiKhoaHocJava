package com.LH.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BaiHocService{
	
	@Autowired
	private BaiHocRepository baihocrepository;
	
	public Page<BaiHoc> listAll(int pageNumber,String sortField,String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1,5,sort);
		return baihocrepository.findAll(pageable);
	}
	
	List<BaiHoc> listBaiHocTheoMaKhoaHoc(String makhoahoc){
		return baihocrepository.listBaiHocTheoMaKhoaHoc(makhoahoc);
	}
	
	List<BaiHoc> listBaiHocs(){
		return baihocrepository.findAll();
	}
	
	BaiHoc getBaiHoc(String mabaihoc) {
		return baihocrepository.findById(mabaihoc).get();
	}
	
	BaiHoc saveBaiHoc(BaiHoc baiHoc) {
		return baihocrepository.save(baiHoc);
	}
	
	void deleteBaiHoc(String mabaihoc) {
		baihocrepository.deleteById(mabaihoc);
	}
	public BaiHoc getbaihocByKhoaHoc(String makhoahoc, String mabaihoc) {
		return baihocrepository.getBaihocByMaKhoaHoc(makhoahoc, mabaihoc);
	}
}
