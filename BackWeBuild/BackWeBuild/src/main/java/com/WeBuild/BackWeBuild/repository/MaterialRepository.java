package com.WeBuild.BackWeBuild.repository;

import com.WeBuild.BackWeBuild.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByIsMandatory(Boolean isMandatory);
}