package com.LH.springboot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/quanlikhoahoc")
public class KhoaHocController {
	@Autowired
	private KhoaHocService khoaHocService;
	
	@GetMapping
	public String viewHomePage(Model model) {
		return listByPage(model, 1,"tenkhoahoc","asc");
	}
	
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage ,@Param("sortField") String sortField,
			@Param("sortDir")String sortDir) {
		Page<KhoaHoc> page = khoaHocService.listAll(currentPage,sortField,sortDir);
		long totalItems =  page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<KhoaHoc> listKhoaHoc = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listKhoaHocs", listKhoaHoc);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equals("asc") ? "desc":"asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		return "QuanLiKhoaHoc";
	}
	
	@GetMapping("/new")
	public String showNewKhoaHocForm(Model model) {
		KhoaHoc khoaHoc = new KhoaHoc();
		model.addAttribute("khoahoc", khoaHoc);
		return "new_khoahoc";
	}
	
	@PostMapping(value="/save")
	public String saveKhoaHoc(@ModelAttribute("khoahoc") KhoaHoc khoaHoc,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		khoaHoc.setAnhkhoahoc(fileName);
		KhoaHoc course = khoaHocService.SaveKhoaHoc(khoaHoc);
		String uploadDir = "./logo-khoahoc/" + course.getMakhoahoc();
		Path uploadPath = Paths.get(uploadDir);
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()){
		Path filePath = uploadPath.resolve(fileName);
		System.out.println(filePath.toFile().getAbsolutePath());
		Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException e) {
			throw new IOException("Khong the upload file"+fileName);
		}
		return "redirect:/admin/quanlikhoahoc";
	}
	
	@GetMapping("/edit/{makhoahoc}")
	public ModelAndView showEditKhoaHocForm(@PathVariable String makhoahoc) {
		ModelAndView mav = new ModelAndView("edit_khoahoc");
		KhoaHoc khoaHoc = khoaHocService.getKhoahoc(makhoahoc);
		mav.addObject("khoahoc", khoaHoc);
		return mav;
	}
	
	@GetMapping("/delete/{makhoahoc}")
	public String deleteKhoaHoc(@PathVariable String makhoahoc) {
		 khoaHocService.delete(makhoahoc);
		 return "redirect:/admin/quanlikhoahoc";
	}
	
}
