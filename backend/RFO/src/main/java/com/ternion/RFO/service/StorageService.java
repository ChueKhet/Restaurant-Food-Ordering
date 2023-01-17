package com.ternion.RFO.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	public String create(MultipartFile file, String fileType, String folderName);

	public byte[] load(String filePath);

	public boolean delete(String filePath);

	public String update(MultipartFile file, String fileType, String filePath, String folderName);

	public void clearAll();

	public boolean check(String filePath);

}
