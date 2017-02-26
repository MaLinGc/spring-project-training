package com.ml.demo.repository;

import com.ml.demo.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, String> {
    Certificate findByUsername(String username);
}
