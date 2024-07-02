package br.com.arsel.progressive.download.api.application.service;

import br.com.arsel.progressive.download.api.infraestructure.entity.MockData;
import br.com.arsel.progressive.download.api.infraestructure.repository.MockDataRepository;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class ProgressiveDownloadService {

    private final MockDataRepository mockDataRepository;

    public ProgressiveDownloadService(MockDataRepository mockDataRepository) {
        this.mockDataRepository = mockDataRepository;
    }

    @SneakyThrows
    public void processData(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

        writer.println("ID,Primeiro Nome,Último Nome,Email,Gênero,Endereço IP");

        int pageNumber = 0;
        int pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<MockData> dados = mockDataRepository.findAll(pageable);
        while (dados.hasContent()) {
            for (MockData mockData : dados) {
                writer.println(String.format("%s,%s,%s,%s,%s,%s",
                        mockData.getId(),
                        mockData.getFirstName(),
                        mockData.getLastName(),
                        mockData.getEmail(),
                        mockData.getGender(),
                        mockData.getIpAddress()
                ));
            }
            pageable = PageRequest.of(++pageNumber, pageSize);
            dados = mockDataRepository.findAll(pageable);
            writer.flush();
        }
        writer.close();
    }

}