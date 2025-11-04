package com.WeBuild.BackWeBuild.controller;

import com.WeBuild.BackWeBuild.model.Project;
import com.WeBuild.BackWeBuild.model.User;
import com.WeBuild.BackWeBuild.service.ProjectService;
import com.WeBuild.BackWeBuild.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    // Funcionalidade: Tela de formulário para preencher informações sobre o terreno
    // Funcionalidade: Estimativa de valores (Custos Mínimos e Opcionais) e Previsão de valorização
    @PostMapping("/estimate")
    public ResponseEntity<Project> submitProjectForEstimate(@RequestBody Project project,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        
        // O usuário logado é o cliente do projeto
        User client = (User) userService.loadUserByUsername(userDetails.getUsername());
        project.setClient(client);
        project.setSubmissionDate(LocalDateTime.now());

        Project estimatedProject = projectService.createProjectAndEstimate(project);
        return ResponseEntity.ok(estimatedProject);
    }

    // Funcionalidade: Ver projetos do cliente (tela de perfil/histórico)
    @GetMapping
    public ResponseEntity<List<Project>> getMyProjects(@AuthenticationPrincipal UserDetails userDetails) {
        User client = (User) userService.loadUserByUsername(userDetails.getUsername());
        List<Project> projects = projectService.getProjectsByUser(client);
        return ResponseEntity.ok(projects);
    }

    // Funcionalidade: Ver todos os projetos (para Empresas/Empreiteiras, para fins de cotação)
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        // Protegido por ROLE_COMPANY no SecurityConfig
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}