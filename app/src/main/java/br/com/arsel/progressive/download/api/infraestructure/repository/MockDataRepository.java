package br.com.arsel.progressive.download.api.infraestructure.repository;

import br.com.arsel.progressive.download.api.infraestructure.entity.MockData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MockDataRepository extends JpaRepository<MockData, Long> {

    @Query("SELECT m FROM MockData m")
    Page<MockData> findAllMocks(Pageable pageable);

}