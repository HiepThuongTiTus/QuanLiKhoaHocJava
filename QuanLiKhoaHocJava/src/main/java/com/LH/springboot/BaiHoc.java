package com.LH.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="baihoc")
public class BaiHoc {
	
	@Id
	@Column(name="mabaihoc")
	@Nationalized
	private String mabaihoc;
	
	@Column(name="stt")
	private int stt;
	
	@Column(name="tenbaihoc")
	@Nationalized
	private String tenbaihoc;
	
	@OneToOne
	@JoinColumn(name="makhoahoc")
	private KhoaHoc danhmuckhoahoc;
	
	@Column(columnDefinition = "nvarchar(max)")
	private String noidung;

	public String getMabaihoc() {
		return mabaihoc;
	}

	public void setMabaihoc(String mabaihoc) {
		this.mabaihoc = mabaihoc;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getTenbaihoc() {
		return tenbaihoc;
	}

	public void setTenbaihoc(String tenbaihoc) {
		this.tenbaihoc = tenbaihoc;
	}

	public KhoaHoc getDanhmuckhoahoc() {
		return danhmuckhoahoc;
	}

	public void setDanhmuckhoahoc(KhoaHoc danhmuckhoahoc) {
		this.danhmuckhoahoc = danhmuckhoahoc;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	
	
	
}
