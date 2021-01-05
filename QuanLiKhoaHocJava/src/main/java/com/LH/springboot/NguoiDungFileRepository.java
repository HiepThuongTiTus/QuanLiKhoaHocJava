package com.LH.springboot;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungFileRepository extends CrudRepository<NguoiDungFile, Long> {

	@Query("select f from NguoiDungFile as f where f.nguoidung.manguoidung = ?1")
	List<NguoiDungFile> findfilebynguoihocid(String manguoidung);
}
