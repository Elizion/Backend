package com.core.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

	public boolean sendEmailXlsx(String asunto, String mensaje, MultipartFile file);

}
