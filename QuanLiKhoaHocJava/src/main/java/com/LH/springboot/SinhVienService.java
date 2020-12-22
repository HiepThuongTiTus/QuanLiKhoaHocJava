package com.LH.springboot;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface SinhVienService {
	List<SinhVien> findAll();
	boolean saveDataFromUploadfile(MultipartFile file) throws Exception;
}
