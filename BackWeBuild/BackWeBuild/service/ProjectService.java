package com.WeBuild.BackWeBuild.service;

import com.WeBuild.BackWeBuild.model.Project;
import com.WeBuild.BackWeBuild.model.User;
import com.WeBuild.BackWeBuild.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProjectAndEstimate(Project project) {
        // --- Lógica de Negócio Central (Simulação de Orçamento) ---
        // A lógica real deve ser refinada buscando custos reais dos materiais no banco de dados.

        Double builtArea = project.getBuiltArea();

        // 1. Cálculo de Custos Mínimos Obrigatórios (Simulação: R$500/m² de material + R$300/m² de mão de obra)
        Double custoMaterialMinimo = builtArea * 500.0;
        Double custoMaoObra = builtArea * 300.0;

        // 2. Cálculo de Custos Opcionais (Simulação: R$200/m² para acabamentos)
        Double custoMaterialOpcional = builtArea * 200.0;

        // 3. Estimativa total
        Double custoTotal = custoMaterialMinimo + custoMaoObra + custoMaterialOpcional;

        // 4. Previsão de Valorização (Simulação: 20% do custo total)
        Double valorizacao = custoTotal * 0.20;

        project.setEstimatedCost(custoTotal);
        project.setEstimatedAppreciation(valorizacao);
        // ------------------------------------

        return projectRepository.save(project);
    }

    public List<Project> getProjectsByUser(User client) {
        return projectRepository.findByClient(client);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}