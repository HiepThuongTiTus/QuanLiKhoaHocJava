package com.vanlinh.springboot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiHocRepository extends JpaRepository<BaiHoc, String> {
	
	@Query("select b from BaiHoc b where b.danhmuckhoahoc.makhoahoc = :makhoahoc")
	public List<BaiHoc> listBaiHocTheoMaKhoaHoc(@Param("makhoahoc") String makhoahoc);
}
