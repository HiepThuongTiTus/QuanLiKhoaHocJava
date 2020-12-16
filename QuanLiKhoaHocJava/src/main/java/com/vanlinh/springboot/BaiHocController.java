package com.vanlinh.springboot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/quanlibaihoc")
public class BaiHocController {
	
	@Autowired
	private BaiHocService baihocservice;
	
	@GetMapping
	public String viewHomePage(Model model) {
		return listByPage(model, 1,"tenbaihoc","asc");
	}
	
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage ,@Param("sortField") String sortField,
			@Param("sortDir")String sortDir) {
		Page<BaiHoc> page = baihocservice.listAll(currentPage,sortField,sortDir);
		long totalItems =  page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<BaiHoc> listBaiHoc = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listBaiHocs", listBaiHoc);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equals("asc") ? "desc":"asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		return "QuanLiBaiHoc";
	}
	
	@GetMapping("/new")
	public String showNewKhoaHocForm(Model model) {
		BaiHoc khoaHoc = new BaiHoc();
		model.addAttribute("baihoc", khoaHoc);
		return "new_baihoc";
	}
	
	@PostMapping(value="/save")
	public String saveKhoaHoc(@ModelAttribute("baihoc") BaiHoc baiHoc){
		BaiHoc course = baihocservice.saveBaiHoc(baiHoc);
		return "redirect:/admin/quanlibaihoc";
	}
	
	@GetMapping("/edit/{mabaihoc}")
	public ModelAndView showEditKhoaHocForm(@PathVariable String mabaihoc) {
		ModelAndView mav = new ModelAndView("edit_baihoc");
		BaiHoc baiHoc = baihocservice.getBaiHoc(mabaihoc);
		mav.addObject("baihoc", baiHoc);
		return mav;
	}
	
	@GetMapping("/delete/{mabaihoc}")
	public String deleteKhoaHoc(@PathVariable String mabaihoc) {
		 baihocservice.deleteBaiHoc(mabaihoc);
		 return "redirect:/admin/quanlibaihoc";
	}
	
}
