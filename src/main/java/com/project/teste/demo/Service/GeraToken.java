package com.project.teste.demo.Service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Data
public class GeraToken {

    private String token;
    private LocalDate dataToken;

    // Construtor
    public GeraToken(){
        this.token = geraTokenString();
        this.dataToken = LocalDate.now().plusDays(1);
    }

    // Gera o token
    public String geraTokenString(){
        return UUID.randomUUID().toString();
    }

    // Verifica a validade do Token
    public boolean ehTokenValido(LocalDate dadosTokenUsuario){
        return LocalDate.now().isAfter(dadosTokenUsuario);
    }

}
