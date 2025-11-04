package com.WeBuild.BackWeBuild.repository;

import com.WeBuild.BackWeBuild.model.Company;
import com.WeBuild.BackWeBuild.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompany(Company company);
}