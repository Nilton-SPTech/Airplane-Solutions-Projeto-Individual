package com.mycompany.projeto.individual.airplane;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


public class ConexaoBancoLocal {
    private final JdbcTemplate connection;
    
    public  ConexaoBancoLocal() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/projetoIndividual?autoReconnect=true&useSSL=false");
        dataSource.setUsername("admin");
        dataSource.setPassword("1234");
        
        this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        System.out.println("Acessando o banco de dados!");
        return connection;
    }
}
  