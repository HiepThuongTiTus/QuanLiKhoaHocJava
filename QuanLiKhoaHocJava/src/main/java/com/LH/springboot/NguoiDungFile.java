package com.LH.springboot;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nguoidungfile")
public class NguoiDungFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private	String filename;
    
    private String modifiedfilename;
    
    private String fileextension;
    
    @ManyToOne
    @JoinColumn(name = "manguoidung")
    private NguoiDung nguoidung;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getModifiedfilename() {
		return modifiedfilename;
	}

	public void setModifiedfilename(String modifiedfilename) {
		this.modifiedfilename = modifiedfilename;
	}

	public String getFileextension() {
		return fileextension;
	}

	public void setFileextension(String fileextension) {
		this.fileextension = fileextension;
	}

	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
