package com.mysoft.alpha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class AlphaConfig {
	@Value("${alpha.upload.file.folder:D:/upload/file}")
	private String uploadFolder;
	@Value("${alpha.upload.file.url:http://localhost:8443/api/file/}")
	private String fileUrl;
	
	public String getUploadFolder() {
		return uploadFolder;
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}	

}
