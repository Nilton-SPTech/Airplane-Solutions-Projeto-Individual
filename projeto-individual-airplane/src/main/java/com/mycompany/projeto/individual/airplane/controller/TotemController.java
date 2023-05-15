package com.mycompany.projeto.individual.airplane.controller;

import com.mycompany.projeto.individual.airplane.ConexaoBancoLocal;
import com.mycompany.projeto.individual.airplane.model.Totem;
import rowMapper.TotemRowMapper;

import java.util.*;

import org.springframework.jdbc.core.JdbcTemplate;

public class TotemController {
    private JdbcTemplate jdbc;
    private ConexaoBancoLocal conexaoBanco; 
    
    
    public TotemController() {
        conexaoBanco = new ConexaoBancoLocal();
        jdbc = conexaoBanco.getConnection();
    }
    
    public List listAllTotem(){
        List<Totem> lista = jdbc.query("select * from Totem", 
                new TotemRowMapper());
        return lista;
    }
  
}
