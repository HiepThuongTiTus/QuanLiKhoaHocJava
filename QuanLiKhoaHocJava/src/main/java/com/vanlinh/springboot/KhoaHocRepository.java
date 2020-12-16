package com.vanlinh.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoaHocRepository extends  JpaRepository<KhoaHoc,String>{
	
}
