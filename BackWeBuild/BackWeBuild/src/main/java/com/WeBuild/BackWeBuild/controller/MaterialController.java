package com.WeBuild.BackWeBuild.controller;

import com.WeBuild.BackWeBuild.model.Material;
import com.WeBuild.BackWeBuild.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    // Funcionalidade: Opções de materiais (Montagem de PC da Kabum - lista de materiais)
    @GetMapping("/options")
    public ResponseEntity<List<Material>> getAllMaterials() {
        // Lista todos os materiais (Obrigatórios e Opcionais) para o cliente selecionar
        return ResponseEntity.ok(materialService.getAllMaterials());
    }
    
    // Rota de administração (acesso apenas para COMPANY)
    @PostMapping("/admin")
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        return ResponseEntity.ok(materialService.saveMaterial(material));
    }

    @GetMapping("/admin/mandatory")
    public ResponseEntity<List<Material>> getMandatoryMaterials() {
        return ResponseEntity.ok(materialService.getMandatoryMaterials());
    }
}