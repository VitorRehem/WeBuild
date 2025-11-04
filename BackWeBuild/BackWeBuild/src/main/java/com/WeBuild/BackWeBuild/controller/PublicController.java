package com.WeBuild.BackWeBuild.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    // Funcionalidade: Tela para apresentação da empresa/site
    @GetMapping("/presentation")
    public ResponseEntity<Map<String, Object>> getPresentationData() {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "WeBuild: Construção com Previsibilidade");
        data.put("description", "Intermediador entre clientes e empresas para orçamentação e execução de obras.");
        data.put("paletaDeCores", Map.of(
            "primaria", "Preto",
            "secundaria", "Branco",
            "apoio", List.of("Cinza", "Azul")
        ));
        return ResponseEntity.ok(data);
    }

    // Funcionalidade: Rodapé
    @GetMapping("/footer")
    public ResponseEntity<Map<String, Object>> getFooterData() {
        Map<String, Object> footer = new HashMap<>();
        footer.put("faleConosco", "contato@webuild.com.br");
        footer.put("empresasParceiras", List.of("Distribuidora Alpha", "Empreiteira Beta"));
        footer.put("redesSociais", List.of("Instagram", "Facebook", "LinkedIn"));
        return ResponseEntity.ok(footer);
    }
}