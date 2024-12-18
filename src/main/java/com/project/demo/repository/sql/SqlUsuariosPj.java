package com.project.demo.repository.sql;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Data
@Component
public class SqlUsuariosPj {

    public static String crateUserRepository = """
            INSERT INTO ALISSON.EMPRESAS
            (RAZAO_SOCIAL, NOME_FANTASIA, CNPJ, EMAIL , SEGMENTO , SENHA)
            VALUES
            (:RAZAO_SOCIAL, :NOME_FANTASIA, :CNPJ, :EMAIL, :SEGMENTO, :SENHA)
            """;

    public static String updateUserPj = """
            UPDATE ALISSON.EMPRESAS
            SET NOME_FANTASIA = :NOME_FANTASIA, EMAIL = :EMAIL, SENHA = :SENHA
            WHERE CNPJ = :CNPJ
            """;

    public static String buscarUsuario = "SELECT * FROM ALISSON.EMPRESAS WHERE CNPJ = :CNPJ";
}