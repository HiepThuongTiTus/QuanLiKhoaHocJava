package com.LH.springboot;

import java.util.List;

public interface NguoiDungService {
	
	List<NguoiDung> getAllNguoiDung();
	
	NguoiDung saveNguoiDung(NguoiDung nguoidung);

	NguoiDung findbyid(String manguoihoc);
	
	List<NguoiDungFile> findfilesbynguoihocid(String manguoidung);
	
}
