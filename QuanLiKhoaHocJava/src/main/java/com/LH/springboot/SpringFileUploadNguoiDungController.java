package com.LH.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SpringFileUploadNguoiDungController {
	
	@Autowired
	private NguoiDungServiceImpl nguoidungserviceimpl;
	
	@Autowired
	private NguoiDungFileServiceImpl nguoidungfileserviceimpl;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String listNguoiDung(Model model){
		List<NguoiDung> listNguoidung = nguoidungserviceimpl.getAllNguoiDung();
		model.addAttribute("nguoidungs", listNguoidung);
		return "QuanLiSinhVien";
	}
	
	@RequestMapping(value="/upbaitap/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("nguoidung") NguoiDung nguoidung, RedirectAttributes redirectAttributes,Model model) {
		
		 NguoiDung dbnguoidung = nguoidungserviceimpl.saveNguoiDung(nguoidung);
		 if(dbnguoidung != null) {
			 redirectAttributes.addFlashAttribute("thanhcong","luu nguoi hoc thanh cong");
			 return "redirect:/file";
		 }else {
			model.addAttribute("thatbai", "luu nguoi hoc that bai, moi thu lai");
			return "QuanLiSinhVien";
		}
		
	}
	
	@RequestMapping(value="/editsv/{manguoidung}", method = RequestMethod.POST)
	public String editnguodung(@PathVariable String manguoidung,Model model) {
		NguoiDung nguoiDung = nguoidungserviceimpl.findbyid(manguoidung);
		List<NguoiDungFile> nguoidungfiles = nguoidungserviceimpl.findfilesbynguoihocid(manguoidung);
		
		model.addAttribute("nguoidungfiles", nguoidungfiles);
		model.addAttribute("nguoidung", nguoiDung);
		return "QuanLiSinhVien";
	}
}
