package com.LH.springboot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Nationalized;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "sinhvien")
public class SinhVien {
	
	private String stt;

	@Id
	private String masinhvien;
	
	@Nationalized
	private String hovaten;
	
	/*
	 * @Temporal(TemporalType.DATE)
	 * 
	 * @DateTimeFormat(pattern="yyyy-MM-dd")
	 */
	private String ngaysinh;
	
	
	private String lopsinhhoat;
	
	@Nationalized
	private String ghichu;
	
	@Transient
	private MultipartFile file;

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getMasinhvien() {
		return masinhvien;
	}

	public void setMasinhvien(String masinhvien) {
		this.masinhvien = masinhvien;
	}

	public String getHovaten() {
		return hovaten;
	}

	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getLopsinhhoat() {
		return lopsinhhoat;
	}

	public void setLopsinhhoat(String lopsinhhoat) {
		this.lopsinhhoat = lopsinhhoat;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
