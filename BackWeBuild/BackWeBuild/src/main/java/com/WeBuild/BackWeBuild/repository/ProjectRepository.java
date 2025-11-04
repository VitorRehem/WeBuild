package com.WeBuild.BackWeBuild.repository;

import com.WeBuild.BackWeBuild.model.Project;
import com.WeBuild.BackWeBuild.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByClient(User client);
}