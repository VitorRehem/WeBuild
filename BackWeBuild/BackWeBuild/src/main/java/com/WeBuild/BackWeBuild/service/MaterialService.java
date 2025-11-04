package com.WeBuild.BackWeBuild.service;

import com.WeBuild.BackWeBuild.model.Material;
import com.WeBuild.BackWeBuild.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public List<Material> getMandatoryMaterials() {
        return materialRepository.findByIsMandatory(true);
    }

    public List<Material> getOptionalMaterials() {
        return materialRepository.findByIsMandatory(false);
    }
}