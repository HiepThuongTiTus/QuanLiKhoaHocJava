package com.LH.springboot;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
	public String saveUploadedFile(MultipartFile file);

	public Document getDocument(String fileName);

	public DocumentDetails storeFile(MultipartFile file, String userId, String orgId, String docCatagory);

	public DocumentDetails getDocumentById(String docId);
}
