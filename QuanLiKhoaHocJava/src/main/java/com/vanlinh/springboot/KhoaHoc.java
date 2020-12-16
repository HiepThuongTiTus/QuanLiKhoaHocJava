package com.vanlinh.springboot;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Nationalized;


@Entity
@Table(name="khoahoc")
public class KhoaHoc {
	@Id
	@Column(name="makhoahoc")
	@Nationalized
	private String makhoahoc;
	
	@Column(name="tenkhoahoc")
	@Nationalized
	private String tenkhoahoc;
	 
	@Column(name="anhkhoahoc")
	@Nationalized
	private String anhkhoahoc;
	 
	@Column(name="mota")
	@Nationalized
	private String mota;
	
	@OneToMany
	@JoinColumn(name="makhoahoc")
	private Set<BaiHoc> danhsachbaihoc;
	
	
	public KhoaHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMakhoahoc() {
		return makhoahoc;
	}

	public void setMakhoahoc(String makhoahoc) {
		this.makhoahoc = makhoahoc;
	}

	public String getTenkhoahoc() {
		return tenkhoahoc;
	}

	public void setTenkhoahoc(String tenkhoahoc) {
		this.tenkhoahoc = tenkhoahoc;
	}

	public String getAnhkhoahoc() {
		return anhkhoahoc;
	}

	public void setAnhkhoahoc(String anhkhoahoc) {
		this.anhkhoahoc = anhkhoahoc;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Set<BaiHoc> getDanhsachbaihoc() {
		return danhsachbaihoc;
	}

	public void setDanhsachbaihoc(Set<BaiHoc> danhsachbaihoc) {
		this.danhsachbaihoc = danhsachbaihoc;
	}

	@Transient
	public String getLogoImagePath() {
		if(anhkhoahoc==null || makhoahoc==null) {
			return null;
		}
		return "/logo-khoahoc/"+makhoahoc+"/"+anhkhoahoc;
	}
	
}
