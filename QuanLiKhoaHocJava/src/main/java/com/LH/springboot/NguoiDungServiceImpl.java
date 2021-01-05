package com.LH.springboot;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class NguoiDungServiceImpl implements NguoiDungService {
	
	@Autowired
	private NguoiDungRepository nguoidungrepository;
	
	@Autowired
	private NguoiDungFileService nguoidungfileservice;
	
	@Autowired
	private NguoiDungFileRepository nguoidungfilerepository;
	
	@Autowired
	private ServletContext context;

	@Override
	public List<NguoiDung> getAllNguoiDung() {
		
		return (List<NguoiDung>) nguoidungrepository.findAll();
	}

	@Override
	public NguoiDung saveNguoiDung(NguoiDung nguoidung) {
		
		nguoidung.setCreatedDate(new Date());
		NguoiDung dbnguoidung = nguoidungrepository.save(nguoidung);
		if(dbnguoidung != null && nguoidung.getFiles()!=null && nguoidung.getFiles().size()>0) {
			for(MultipartFile file : nguoidung.getFiles()) {
				String fileName = file.getOriginalFilename();
				String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+
				FilenameUtils.getExtension(fileName);
				File storeFile = nguoidungfileservice.getFilePath(modifiedFileName, "images");
				if(storeFile!=null) {
					try {
						FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				NguoiDungFile files = new NguoiDungFile();
				files.setFileextension(FilenameUtils.getExtension(fileName));
				files.setFilename(fileName);
				files.setModifiedfilename(modifiedFileName);
				files.setNguoidung(dbnguoidung);
				nguoidungfilerepository.save(files);
			}
		}
		return dbnguoidung;
	}

	@Override
	public NguoiDung findbyid(String manguoidung) {
		Optional<NguoiDung> nguoidung = nguoidungrepository.findById(manguoidung);
		if(nguoidung.isPresent()) {
			return nguoidung.get();
		}
		return null;
	}

	@Override
	public List<NguoiDungFile> findfilesbynguoihocid(String manguoidung) {
		return nguoidungfilerepository.findfilebynguoihocid(manguoidung);
	}
	
}
