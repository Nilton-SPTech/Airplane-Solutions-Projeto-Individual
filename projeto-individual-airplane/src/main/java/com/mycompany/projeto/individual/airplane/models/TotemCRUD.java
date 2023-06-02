package com.mycompany.projeto.individual.airplane.models;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.mycompany.projeto.individual.airplane.controllers.Totem;
import com.mycompany.projeto.individual.airplane.controllers.ComponenteTotem;

import java.util.ArrayList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

import javax.swing.*;  

public class TotemCRUD {
        
    private final Looca looca = new Looca();
    
    private final Connection conexao = new Connection(true);
    private final JdbcTemplate con = conexao.getConnection();
    
    private final Connection conexaoLocal = new Connection(false);
    private final JdbcTemplate conLocal = conexaoLocal.getConnection();
    
    public Totem getTotemByToken(String token) {
        List<Totem> validacaoTotem = con.query("SELECT * FROM totem WHERE token = ?"
                , new BeanPropertyRowMapper(Totem.class), token);
        System.out.println(validacaoTotem);
        
        Totem totem = validacaoTotem.get(0);
        
        //VALIDANDO SE FOI INSERIDO PELO USUÁRIO OS DADOS DO TOTEM
        if(totem.getProcessador() == null){
            updateTotemInformation(totem.getIdTotem());
            
            validacaoTotem = con.query("SELECT * FROM totem WHERE token = ?"
                , new BeanPropertyRowMapper(Totem.class), token);
        
            totem = validacaoTotem.get(0);
        }
        
        insertTotemLocal(totem);
        
        //VALIDANDO SE OS COMPONENTES DO TOTEM JÁ FORAM CADASTRADOS
        validationComponenteTotem(totem.getIdTotem(), totem.getFkCompanhia());
        
        //PASSANDO A LISTA DE COMPONENTES PARA O OBJETO TOTEM
        List<ComponenteTotem> componentes = con.query("SELECT * FROM ComponenteTotem WHERE fkTotem = ?",
                new BeanPropertyRowMapper(ComponenteTotem.class), totem.getIdTotem());
        
        totem.setComponentes(componentes);
        return totem;
    }
    
    public void updateBoolCaptura(String token, Integer bool){
        con.update("UPDATE totem SET boolCaptura = ? WHERE token = ?", bool, token);
    }
    
    public Map<String, Object> selectBoolCaptura(String token){
        Map<String, Object> bool = con.queryForMap("SELECT boolCaptura FROM totem WHERE token = ?", token);
        return bool;
    }
    
    public void updateTotemInformation(Integer idTotem){
        
        String arquitetura = looca.getSistema().getArquitetura() + "bits";
        String sistemaOperacional = looca.getSistema().getSistemaOperacional();
        String processador = looca.getProcessador().getNome();
        String fabricante = looca.getProcessador().getFabricante();
        
        con.update("UPDATE Totem SET fabricante = ?, arquitetura = ?, sistemaOperacional = ?, processador = ? WHERE idTotem = ?",
                    fabricante, arquitetura, sistemaOperacional, processador, idTotem);
        
        
        JOptionPane.showMessageDialog(null, "Notamos que seu totem não está com algumas informações cadastradas. \n"
                                        + "Faremos isso para você de forma automática.");
    }
    
    public void validationComponenteTotem(Integer idTotem, Integer idCompanhia){
        insertComponenteRam(idTotem, idCompanhia);
        insertComponenteDisco(idTotem, idCompanhia);
        insertComponenteCpu(idTotem, idCompanhia);
        insertComponenteRede(idTotem, idCompanhia);
    }
    
    public void insertComponenteRam(Integer idTotem, Integer idCompanhia){
        Integer idComponente = 1;
        
        // INSERINDO NA NUVEM
        if(idCompanhia != 0){
            List<ComponenteTotem> componente = con.query("SELECT * FROM ComponenteTotem WHERE fkComponente = ? AND fkTotem = ?", 
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                long total = looca.getMemoria().getTotal();

                con.update("INSERT INTO ComponenteTotem(fkTotem, fkComponente, total, fkCompanhia) VALUES (?,?,?,?)",
                            idTotem, idComponente, total, idCompanhia);
            }
        }
        else{
            //INSERINDO LOCAL
            List<ComponenteTotem> componente = conLocal.query("SELECT * FROM componenteTotem WHERE fkComponente = ? AND fkTotem = ?", 
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                long total = looca.getMemoria().getTotal();

                conLocal.update("INSERT INTO componenteTotem(fkTotem, fkComponente, total) VALUES (?,?,?)",
                            idTotem, idComponente, total);
            }
        }
        
    }
    
    public void insertComponenteDisco(Integer idTotem, Integer idCompanhia){
        Integer idComponente = 2;
        if(idCompanhia != 0){
            List<ComponenteTotem> componente = con.query("SELECT * FROM ComponenteTotem WHERE fkComponente = ? AND fkTotem = ?", 
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                long total = looca.getGrupoDeDiscos().getTamanhoTotal();

                con.update("INSERT INTO ComponenteTotem(fkTotem, fkComponente, total, fkCompanhia) VALUES (?,?,?,?)",
                            idTotem, idComponente, total, idCompanhia);
            }
        }
        else{
            List<ComponenteTotem> componente = conLocal.query("SELECT * FROM componenteTotem WHERE fkComponente = ? AND fkTotem = ?", 
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                long total = looca.getGrupoDeDiscos().getTamanhoTotal();

                conLocal.update("INSERT INTO componenteTotem(fkTotem, fkComponente, total) VALUES (?,?,?)",
                            idTotem, idComponente, total);
            }
        }
    }
    
    public void insertComponenteCpu(Integer idTotem, Integer idCompanhia){
        Integer idComponente = 3;
        long frequencia = looca.getProcessador().getFrequencia();
        String descricaoCpu = looca.getProcessador().getIdentificador();
        
        if(idCompanhia != 0){
            List<ComponenteTotem> componente = con.query("SELECT * FROM ComponenteTotem WHERE fkComponente = ? AND fkTotem = ?", 
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                con.update("INSERT INTO ComponenteTotem(fkTotem, fkComponente, frequencia, descricao, fkCompanhia) VALUES (?,?,?,?,?)",
                            idTotem, idComponente, frequencia, descricaoCpu, idCompanhia);
            }
        }
        else{
            List<ComponenteTotem> componente = conLocal.query("SELECT * FROM componenteTotem WHERE fkComponente = ? AND fkTotem = ?", 
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                conLocal.update("INSERT INTO componenteTotem(fkTotem, fkComponente, frequencia, descricao) VALUES (?,?,?,?)",
                            idTotem, idComponente, frequencia, descricaoCpu);
            }
        }
    }
    
    public void insertComponenteRede(Integer idTotem, Integer idCompanhia){
        List ipv4 = new ArrayList(); 
        Integer idComponente = 4; 
        
        if(idCompanhia != 0){
            List<ComponenteTotem> componente = con.query("SELECT * FROM ComponenteTotem WHERE fkComponente = ? AND fkTotem = ?",
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                List<RedeInterface> rede = looca.getRede().getGrupoDeInterfaces().getInterfaces();

                for(RedeInterface redeInterface : rede){
                    if(redeInterface.getEnderecoIpv4().size() > 0){
                        ipv4.add(redeInterface.getEnderecoIpv4().get(0));
                    }
                }

                if(ipv4.size() >= 2){
                    con.update("INSERT INTO ComponenteTotem (fkTotem, fkComponente, enderecoIPv4Rede, enderecoIPv4Totem, fkCompanhia) VALUES (?,?,?,?,?)",
                            idTotem, idComponente, ipv4.get(0), ipv4.get(1), idCompanhia);
                }

                if(ipv4.size() == 1){
                    con.update("INSERT INTO ComponenteTotem (fkTotem, fkComponente, enderecoIPv4Totem, fkCompanhia) VALUES (?,?,?,?)",
                            idTotem, idComponente, ipv4.get(0), idCompanhia);
                }
            }
        }
        else{
            List<ComponenteTotem> componente = conLocal.query("SELECT * FROM componenteTotem WHERE fkComponente = ? AND fkTotem = ?",
                    new BeanPropertyRowMapper(ComponenteTotem.class), idComponente, idTotem);

            if(componente.isEmpty()){
                List<RedeInterface> rede = looca.getRede().getGrupoDeInterfaces().getInterfaces();

                for(RedeInterface redeInterface : rede){
                    if(redeInterface.getEnderecoIpv4().size() > 0){
                        ipv4.add(redeInterface.getEnderecoIpv4().get(0));
                    }
                }

                if(ipv4.size() >= 2){
                    conLocal.update("INSERT INTO componenteTotem (fkTotem, fkComponente, enderecoIPv4Rede, enderecoIPv4Totem) VALUES (?,?,?,?)",
                            idTotem, idComponente, ipv4.get(0), ipv4.get(1));
                }

                if(ipv4.size() == 1){
                    conLocal.update("INSERT INTO componenteTotem (fkTotem, fkComponente, enderecoIPv4Totem) VALUES (?,?,?)",
                            idTotem, idComponente, ipv4.get(0));
                }
            }
        }
    }
        
        
    // INSERINDO NO BANCO LOCAL 
    public void insertTotemLocal(Totem totem){
        //VALIDANDO SE JÁ EXISTEM UM TOTEM CADASTRADO COM O token
        Map<String, Object> mapTotem = conLocal.queryForMap("SELECT COUNT(*) totemCadastrado FROM totem WHERE token = ?", totem.getToken());
 
        Integer hasTotem = Integer.parseInt(mapTotem.get("totemCadastrado").toString());
   
        //INSERINDO OS DADOS CASO NÃO TENHA MÁQUINA
        if(hasTotem == 0){
            conLocal.update("INSERT INTO totem(token, arquitetura, sistemaOperacional, processador) VALUES(?,?,?,?);",
                    totem.getToken(), totem.getArquitetura(), totem.getSistemaOperacional(), totem.getProcessador());
        }
        
        // PEGANDO O ID DO TOTEM QUE FOI CADASTRADO
        mapTotem = conLocal.queryForMap("SELECT idTotem FROM totem WHERE token = ?", totem.getToken());

        Integer idTotem = Integer.parseInt(mapTotem.get("idTotem").toString());
        
        validationComponenteTotem(idTotem, 0);
    }
    
    public List<Integer> getListComponente(String token){
        List<Map<String, Object>> mapComponente = conLocal.queryForList("SELECT idComponenteTotem FROM componenteTotem c\n" +
                                                                "INNER JOIN totem t ON t.idTotem = c.fkTotem\n" +
                                                                "WHERE t.token = ?", token);
        
        List<Integer> listIdComponenteTotem = new ArrayList();
        
        for(Map<String, Object> componente : mapComponente){
            listIdComponenteTotem.add(Integer.parseInt(componente.get("idComponenteTotem").toString()));
        }
        return listIdComponenteTotem;
    }
}   
