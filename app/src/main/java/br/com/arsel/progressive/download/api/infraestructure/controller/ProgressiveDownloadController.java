package br.com.arsel.progressive.download.api.infraestructure.controller;

import br.com.arsel.progressive.download.api.application.service.ProgressiveDownloadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/progressive")
public class ProgressiveDownloadController {

	private final ProgressiveDownloadService progressiveDownloadService;

	public ProgressiveDownloadController(ProgressiveDownloadService progressiveDownloadService) {
		this.progressiveDownloadService = progressiveDownloadService;
	}

	@GetMapping("/download/all")
	public ResponseEntity<StreamingResponseBody> download() {
		StreamingResponseBody responseBody = progressiveDownloadService::processData;
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"generate-file.csv\"")
				.body(responseBody);
	}

}