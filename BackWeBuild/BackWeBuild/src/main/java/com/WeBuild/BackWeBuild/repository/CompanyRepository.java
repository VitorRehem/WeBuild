package com.WeBuild.BackWeBuild.repository;

import com.WeBuild.BackWeBuild.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}