package com.LH.springboot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinhVienRepository extends CrudRepository<SinhVien, String> {

}
