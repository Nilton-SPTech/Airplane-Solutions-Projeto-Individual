package com.mycompany.projeto.individual.airplane.models;

import org.springframework.jdbc.core.JdbcTemplate;

public class TablesCreate {
    private final Connection conexao = new Connection(false);
    private final JdbcTemplate con = conexao.getConnection();
    
    public void creatTables(){
        con.execute("CREATE TABLE IF NOT EXISTS componente(\n" +
                        "idComponente INT PRIMARY KEY AUTO_INCREMENT, \n" +
                        "descricao VARCHAR(45)\n" +
                    ");");
        
        
        con.execute("CREATE TABLE IF NOT EXISTS totem(\n" +
                        "idTotem INT PRIMARY KEY AUTO_INCREMENT, \n" +
                        "token CHAR(4), \n" +
                        "arquitetura CHAR(4),\n" +
                        "sistemaOperacional VARCHAR(100),\n" +
                        "processador VARCHAR(50)\n" +
                    ");");
        
        con.execute("CREATE TABLE IF NOT EXISTS componenteTotem(\n" +
                        "idComponenteTotem INT PRIMARY KEY AUTO_INCREMENT, \n" +
                        "descricao VARCHAR(45), \n" +
                        "total BIGINT, \n" +
                        "frequencia BIGINT,\n" +
                        "fkComponente INT, \n" +
                        "fkTotem INT, \n" +
                        "enderecoIPv4Rede VARCHAR(25), \n" +
                        "enderecoIPv4Totem VARCHAR(25)	\n" +
                    ");");
        
        con.execute("CREATE TABLE IF NOT EXISTS registroComponente(\n" +
                        "idRegistroComponente INT PRIMARY KEY AUTO_INCREMENT, \n" +
                        "valorUso BIGINT, \n" +
                        "bytesEnviados BIGINT,\n" +
                        "bytesRecebidos BIGINT, \n" +
                        "clock DECIMAL(4,2), \n" +
                        "tempoAtividade VARCHAR(50), \n" +
                        "dataInicializacao DATETIME, \n" +
                        "dataHoraCaptura DATETIME, \n" +
                        "tempoTransferencia BIGINT, \n" +
                        "fkComponenteTotem INT\n" +
                    ");");
        
        con.update("INSERT INTO componente(descricao) VALUES ('Memoria RAM'),('Disco'),('CPU'),('Rede');");
        
        
    }
}
