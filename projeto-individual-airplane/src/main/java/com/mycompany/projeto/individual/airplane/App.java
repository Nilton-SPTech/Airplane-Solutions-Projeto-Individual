package com.mycompany.projeto.individual.airplane;

import com.github.britooo.looca.api.core.Looca;
import com.mycompany.projeto.individual.airplane.ConexaoBancoLocal;
import com.mycompany.projeto.individual.airplane.model.Totem;

import rowMapper.TotemRowMapper;

import java.util.*;

import org.springframework.jdbc.core.JdbcTemplate;

public class App {
    public static void main(String[] args) {
        ConexaoBancoLocal conexaoBanco = new ConexaoBancoLocal();
        JdbcTemplate jdbc =  conexaoBanco.getConnection(); 
        Scanner leitor01 = new Scanner(System.in);
        Scanner leitor02 = new Scanner(System.in);

        Looca looca = new Looca();
        
        Runnable deletarTabela = () -> {
            jdbc.execute("DROP TABLE IF EXISTS Totem;");
        };
        deletarTabela.run();
        
        Runnable criarTabela = () -> {
            jdbc.execute("create table Totem(idTotem int AUTO_INCREMENT PRIMARY KEY, token char(4), arquitetura varchar(45), sistemaOperacional VARCHAR(45), processador VARCHAR(100));");
        };
        criarTabela.run();
        
        System.out.println("Olá seja bem vindo ao sistema AIRPLANE SOLUTIONS");
        
        Integer resposta = 0;
        do{
            System.out.println("1 - Cadastrar um novo totem"
                            + "\n2 - Listar todos os Totens cadastrados"
                            + "\n3 - Ver componentes da sua máquina atual"
                            + "\n0 - Sair ");
            
            resposta = leitor01.nextInt();
            
            switch (resposta) {
                case 1:
                    System.out.println("Digite o Token:");
                    String token = leitor02.nextLine();
                    
                    System.out.println("Inserindo um novo totem no banco");
                    jdbc.update("INSERT INTO Totem(arquitetura, sistemaOperacional, processador, token) VALUES (?, ?, ?, ?)",
                            looca.getSistema().getArquitetura(), 
                            looca.getSistema().getSistemaOperacional(), 
                            looca.getProcessador().getNome(),
                            token);
                    
                    System.out.println("Totem Inserido com sucesso");
                    break;
                case 2: 
                    List<Totem> totens = jdbc.query("SELECT * FROM Totem;", new TotemRowMapper());
                    totens.forEach(System.out::println);
                    break; 
                case 3: 
                    System.out.println(String.format(
                            "Processador: %s, Arquitetura: %s, Sistema Operacional: %s\n", 
                            looca.getProcessador().getNome(), looca.getSistema().getArquitetura(), looca.getSistema().getSistemaOperacional()
                    ));
                    break; 
                case 0:
                    System.out.println("Até mais1!");
            }
        }
        while(resposta != 0);
        
    }
 
}
