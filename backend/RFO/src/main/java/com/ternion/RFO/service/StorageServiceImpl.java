package com.ternion.RFO.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService{
	private final Path storagePath;
	
	@Autowired
	public StorageServiceImpl() throws IOException {

		Path storagePath = Paths.get("").resolve("src").resolve("main")
				.resolve("resources").resolve("static").resolve("media");
		
		if (!Files.exists(storagePath)) {
			Files.createDirectories(storagePath);
		}
		
		this.storagePath = storagePath;
	}

	@Override
	public String create(MultipartFile file, String fileType, String folderName) {

		String filePath = null;
		String ft = "";

		try {
			String fileName = Instant.now().getEpochSecond() + "_"
					+ StringUtils.cleanPath(file.getOriginalFilename());
			
			switch (fileType) {
				case "image/jpg" :
				case "image/jpeg" :
					ft = "jpg";
					filePath = getFilePath(folderName, ft, fileName);
					break;
				case "image/png" :
				default :
					ft = "png";
					filePath = getFilePath(folderName, ft, fileName);
					break;
			}
			
			Files.copy(
				file.getInputStream(), 
				this.storagePath.resolve(folderName).resolve(ft).resolve(fileName),
				StandardCopyOption.REPLACE_EXISTING
			);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath;
	}
	
	public String getFilePath(String folderName, String fileType, String fileName) throws IOException {
		String result = "";
		
		
		String path = this.storagePath.toAbsolutePath().toString() + "/" + folderName + "/" + fileType;
		
		File dir = new File(path);
	    if (!dir.exists()) {
	    	dir.mkdirs(); 
	    }
		
		result = "/media/" + folderName + "/" + fileType + "/" + fileName;
		
		return result;
	}

	@Override
	public byte[] load(String fileName) {
		byte[] retBytes = null;

		try {
			Path filePath = this.storagePath.resolve(fileName);
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() && resource.isReadable()) { 
				retBytes = StreamUtils
						.copyToByteArray(resource.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retBytes;
	}

	@Override
	public boolean delete(String filePath) {
		filePath = filePath.replace("/media/jpg/", "");
		filePath = filePath.replace("/media/png/", "");
		filePath = filePath.replace("/media/mp4/", "");

		if (filePath != null && filePath != "") {
			try {
				Files.delete(this.storagePath.resolve(filePath));
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return false;
	}

	@Override
	public String update(MultipartFile file, String fileType, String filePath, String folderName) {

		String retfilePath = null;
		
		try {
			String arr[] = filePath.split("/");
			filePath = filePath.replace("/media/" + folderName + "/" + arr[3] + "/", "");
			
			if (filePath != null && filePath != "") {
				try {
					Files.delete(this.storagePath.resolve(folderName).resolve(arr[3]).resolve(filePath));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			String ft = "";
			String fileName = Instant.now().getEpochSecond() + "_"
					+ StringUtils.cleanPath(file.getOriginalFilename());
			
			switch (fileType) {
				case "image/jpg" :
				case "image/jpeg" :
					ft = "jpg";
					retfilePath = getFilePath(folderName, ft, fileName);
					break;
				case "image/png" :
				default :
					ft = "png";
					retfilePath = getFilePath(folderName, ft, fileName);
					break;
			}
			
			Files.copy(
				file.getInputStream(), 
				this.storagePath.resolve(folderName).resolve(ft).resolve(fileName),
				StandardCopyOption.REPLACE_EXISTING
			);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retfilePath;
	}

	@Override
	public void clearAll() {
		try {
			Files.walk(this.storagePath).sorted().forEach(file -> {
				try {
					if (!Files.isDirectory(file)) {
						Files.deleteIfExists(file);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean check(String filePath) {

		filePath = filePath.replace("/media/jpg/", "");
		filePath = filePath.replace("/media/png/", "");
		filePath = filePath.replace("/media/mp4/", "");

		if (filePath != null && filePath != "") {
			Path filePathPath = this.storagePath.resolve(filePath);
			return Files.exists(filePathPath)
					&& !Files.isDirectory(filePathPath)
					&& Files.isReadable(filePathPath);
		}

		return false;
	}
}