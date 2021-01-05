package com.LH.springboot;

import java.io.File;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NguoiDungFileServiceImpl implements NguoiDungFileService {
	@Autowired
	ServletContext context;

	@Override
	public File getFilePath(String modifiedFileName, String path) {
		boolean exits = new File(context.getRealPath("/"+path+"/")).exists();
		if(!exits) {
			new File(context.getRealPath("/"+path+"/")).mkdir();
		}
		String modifiedFilePath = context.getRealPath("/"+path+"/"+File.separator+modifiedFileName);
		File file = new File(modifiedFilePath);
		return file;
	}

}
