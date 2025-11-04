package com.WeBuild.BackWeBuild.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token; // Token de sessão (simulado neste exemplo)
    private String role;
    private String message;
}