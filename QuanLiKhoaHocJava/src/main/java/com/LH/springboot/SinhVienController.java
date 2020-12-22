package com.LH.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {
	@Autowired
	private SinhVienServiceImpl sinhvienserviceimpl;
	
	@GetMapping()
	public String home(Model model) {
		model.addAttribute("sinhvien",new SinhVien());
		List<SinhVien> sinhViens = sinhvienserviceimpl.findAll();
		model.addAttribute("sinhviens", sinhViens);
		return "sinhvien";	
	}
	
	@PostMapping("/fileupload")
	public String uploadFile(@ModelAttribute SinhVien sinhvien,RedirectAttributes redirectAttributes) throws Exception{
		boolean isFlag = sinhvienserviceimpl.saveDataFromUploadfile(sinhvien.getFile());
		if(isFlag) {
			redirectAttributes.addFlashAttribute("thanhcong","upload file thanh cong");
		}else {
			redirectAttributes.addFlashAttribute("thatbai", "upload file that bai, moi ban tai file lai");
		}
		return "redirect:/sinhvien";
	}
}
