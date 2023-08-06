package com.project.teste.demo.Repository;

import com.project.teste.demo.Model.Usuario;
import jdk.dynalink.linker.support.SimpleLinkRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
@Slf4j
@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    private final JavaMailSender javaMailSender;

    public UsuarioRepository(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmail( String para, String titulo, String link ){
        log.info("Enviando email");
        var mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject(titulo);
        mensagem.setText("Para redefinir a sua senha clique no link: " + link);
        javaMailSender.send(mensagem);
        log.info("Email enviado");

    }

    public int crateUserRepository(Usuario entityUser) {
      String sql = "INSERT INTO ALISSON_DB.USUARIO(ID, NOME, SENHA) VALUES (:id,:nome,:senha)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", entityUser.getId())
                .addValue("nome", entityUser.getNome())
                .addValue("senha", entityUser.getSenha());
                return namedJdbcTemplate.update(sql, params);
    }

    public List<Usuario> listaUsuarioRepository() {
        String sql = "SELECT ID, NOME FROM ALISSON_DB.USUARIO";
        List<Usuario> usuarios = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Usuario.class));
        return usuarios;
    }

    public int atualizaUsuario(Usuario usuario) {
        int retorno = 0;
        String sql =
              " UPDATE \n" +
              " USUARIO \n" +
              " SET \n" +
              " NOME = :nome \n" +
              " WHERE ID = :id \n";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nome", usuario.getNome(), Types.VARCHAR)
                .addValue("id", usuario.getId(), Types.VARCHAR);
        retorno = namedJdbcTemplate.update(sql, params);

        return retorno;
    }


}