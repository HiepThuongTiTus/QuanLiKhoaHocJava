package com.LH.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDetailsRepository extends JpaRepository<DocumentDetails, String> {

}
