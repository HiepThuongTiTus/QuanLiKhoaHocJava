package com.LH.springboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Table;
import org.springframework.web.multipart.MultipartFile;

@Entity
@javax.persistence.Table(name = "nguoidung")
public class NguoiDung {
	
	@Id
	private String manguoidung;
	
	@Nationalized
	private String hoten;
	
	private String lopsinhhoat;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	@Transient
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	
	@Transient
	private List<String> removeImage = new ArrayList<String>();

	public String getManguoidung() {
		return manguoidung;
	}

	public void setManguoidung(String manguoidung) {
		this.manguoidung = manguoidung;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getLopsinhhoat() {
		return lopsinhhoat;
	}

	public void setLopsinhhoat(String lopsinhhoat) {
		this.lopsinhhoat = lopsinhhoat;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public List<String> getRemoveImage() {
		return removeImage;
	}

	public void setRemoveImage(List<String> removeImage) {
		this.removeImage = removeImage;
	}
	
}
