package com.LH.springboot;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SinhVienServiceImpl implements SinhVienService {
	@Autowired
	private SinhVienRepository sinhvienrepository;

	@Override
	public List<SinhVien> findAll() {

		return (List<SinhVien>) sinhvienrepository.findAll();
	}

	@Override
	public boolean saveDataFromUploadfile(MultipartFile file) throws Exception {
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			isFlag = readDataFromExcel(file);
		}
		return isFlag;
	}

	public boolean readDataFromExcel(MultipartFile file) {
	Workbook workbook = getWorkBook(file);
	Sheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rows = sheet.iterator();
	rows.next();
	while(rows.hasNext())
	{
		DataFormatter dataFormatter = new DataFormatter();
		Row row = rows.next();
		SinhVien sinhvien = new SinhVien();
		if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String stt = NumberToTextConverter.toText(row.getCell(0).getNumericCellValue());
			sinhvien.setStt(stt);
		} else if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
			sinhvien.setStt(row.getCell(0).getStringCellValue());
		}
		if (row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String msv = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
			sinhvien.setMasinhvien(msv);
		} else if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
			sinhvien.setMasinhvien(row.getCell(1).getStringCellValue());
		}
		if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
			sinhvien.setHovaten(row.getCell(2).getStringCellValue());
		}

		if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String ngaysinh = NumberToTextConverter.toText(row.getCell(3).getNumericCellValue());
			sinhvien.setNgaysinh(ngaysinh);
		}

		if (row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING) {
			sinhvien.setLopsinhhoat(row.getCell(4).getStringCellValue());
		}
		if (row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING) {
			sinhvien.setGhichu(row.getCell(5).getStringCellValue());
		}
		sinhvienrepository.save(sinhvien);
	}
	return true;
	
	}

	private Workbook getWorkBook(MultipartFile file) {
	Workbook workbook = null;
	String extension = FilenameUtils.getExtension(file.getOriginalFilename());
	try {
		if(extension.equalsIgnoreCase("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
		}else if (extension.equalsIgnoreCase("xls")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return workbook;
}
}
