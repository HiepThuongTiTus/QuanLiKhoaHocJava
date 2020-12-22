package com.LH.springboot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/khoahoc")
public class TrangChuController {
	@Autowired
	KhoaHocService khoaHocService;
	
	@Autowired
	private BaiHocService baihocservice;
	
	@RequestMapping
	public String listKhoaHocTrangChu(Model model) {
		List<KhoaHoc> listKhoaHoc = khoaHocService.listKhoaHocTrangChu();
		model.addAttribute("khoahocjava",listKhoaHoc);
		return "TrangChu";
	}
	
	/*
	 * @GetMapping("/{makhoahoc}") public String getThongTinKhoaHoc(@PathVariable
	 * String makhoahoc,Model model) { KhoaHoc khoaHoc =
	 * khoaHocService.getKhoahoc(makhoahoc);
	 * model.addAttribute("chitietkhoahoc",khoaHoc); return "ChiTietKhoaHoc"; }
	 */
	
	@GetMapping("/{makhoahoc}")
	public String getAllBaiHocTheoMaKhoaHoc(@PathVariable String makhoahoc,Model model) {
		List<BaiHoc> listAllBaiHocTheoMaKhoaHoc = baihocservice.listBaiHocTheoMaKhoaHoc(makhoahoc);
		model.addAttribute("listAllBaiHocByMaKhoaHoc", listAllBaiHocTheoMaKhoaHoc);
		return "ChiTietKhoaHoc";
	}
	
	@GetMapping("/{makhoahoc}/{mabaihoc}")
	public ModelAndView showEditKhoaHocForm2(@PathVariable String makhoahoc, @PathVariable String mabaihoc) {
	ModelAndView mav1 = new ModelAndView("bai1.html");
	BaiHoc baiHoc = baihocservice.getbaihocByKhoaHoc(makhoahoc, mabaihoc);
	mav1.addObject("chitietbaihoc1", baiHoc);
	return mav1;
}
}
