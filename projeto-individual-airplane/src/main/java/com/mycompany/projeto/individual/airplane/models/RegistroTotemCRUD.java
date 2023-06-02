package com.mycompany.projeto.individual.airplane.models;

import com.github.britooo.looca.api.core.Looca;
import com.mycompany.projeto.individual.airplane.controllers.ShowCPU;
import com.mycompany.projeto.individual.airplane.controllers.ShowDisco;
import com.mycompany.projeto.individual.airplane.controllers.ShowMemoria;
import com.mycompany.projeto.individual.airplane.controllers.ShowRede;
import java.time.Instant;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Date;
import java.util.List;



import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;


public class RegistroTotemCRUD {
    
    private Looca looca = new Looca();
    
    private Connection conexao = new Connection(true);
    private JdbcTemplate con = conexao.getConnection();
    
    private Connection conexaoLocal = new Connection(false);
    private JdbcTemplate conLocal = conexaoLocal.getConnection();
    
    
    public void insertRegistroComponente(Integer fkTotem, Integer fkCompanhia, String token){
        TotemCRUD totemCrud = new TotemCRUD();
        AtomicBoolean shouldContinue = new AtomicBoolean(true);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Boolean boolCaptura = (Boolean) totemCrud.selectBoolCaptura(token).get("boolCaptura");
                
                if(boolCaptura){
                    System.out.println("Registro dos componentes capturados a cada 5 segundos");
                    insertRegistroRam(fkTotem, fkCompanhia);
                    insertRegistroDisco(fkTotem, fkCompanhia);
                    insertRegistroCpu(fkTotem, fkCompanhia);
                    insertRegistroRede(fkTotem, fkCompanhia);
                    
                    
                    insertRegistros(totemCrud.getListComponente(token));
                }
                else{
                    shouldContinue.set(false);
                }
            }
        }, 0, 5000);
        
    }
    
    // INSERINDO NO BANCO DA NUVEM
    public void insertRegistroRam(Integer fkTotem, Integer fkCompanhia){
        
        Integer fkComponente = 1;
        ShowMemoria memoria = new ShowMemoria();
        Long usoRam = memoria.showUsoRam();
        
        con.update(
            "INSERT INTO RegistroComponente(fkTotem, fkComponente, fkCompanhia, valorUso, dataHoraCaptura) VALUES (?,?,?,?,?)", 
            fkTotem, 
            fkComponente, 
            fkCompanhia, 
            usoRam, 
            new Date()
        );
    }
    
    public void insertRegistroDisco(Integer fkTotem, Integer fkCompanhia){
        Integer fkComponente = 2;
        ShowDisco disco = new ShowDisco();

        Double usoDisco = disco.showUsoDisco();
        Long tempoTransferencia = disco.showTempoTransferencia();
        Long total = disco.showTotal();

        con.update(
            "INSERT INTO RegistroComponente(fkTotem, fkComponente, fkCompanhia, valorUso, tempoTransferencia, dataHoraCaptura) VALUES (?,?,?, ?,?,?)",
            fkTotem, 
            fkComponente, 
            fkCompanhia, 
            usoDisco,
            tempoTransferencia, 
            new Date()
        );
    }
    
    public void insertRegistroCpu(Integer fkTotem, Integer fkCompanhia){
        Integer fkComponente = 3;
        ShowCPU cpu = new ShowCPU();
        
        Integer usoCpu = cpu.showUsoCpu();
        Double clockCpu = cpu.showClockCpu();
        String tempoAtividade = cpu.showTempoAtividade();
        Date inicializado = Date.from(cpu.showInicializado());
        
        con.update(
            "INSERT INTO RegistroComponente(fkTotem, fkComponente, fkCompanhia, valorUso, clock, dataHoraCaptura, tempoAtividade, dataInicializacao) VALUES (?,?,?, ?,?,?,?,?)", 
            fkTotem, 
            fkComponente, 
            fkCompanhia, 
            usoCpu, 
            clockCpu, 
            new Date(), 
            tempoAtividade,
            inicializado
        );
   }
    
    public void insertRegistroRede(Integer fkTotem, Integer fkCompanhia){
        Integer fkComponente = 4;
        ShowRede rede = new ShowRede();
        Long bytesEnviados = rede.showBytesEnviados();
        Long bytesRecebidos = rede.showBytesRecebidos();

        con.update(
            "INSERT INTO RegistroComponente(fkTotem, fkComponente, fkCompanhia, bytesEnviados, bytesRecebidos, dataHoraCaptura) VALUES (?,?,?, ?,?,?)",
            fkTotem, 
            fkComponente, 
            fkCompanhia, 
            bytesEnviados, 
            bytesRecebidos,
            new Date()
        );
    }
    
    // INSERINDO NO BANCO LOCAL
    public void insertRegistroRam(Integer fkComponenteTotem){
        
        Integer fkComponente = 1;
        ShowMemoria memoria = new ShowMemoria();
        Long usoRam = memoria.showUsoRam();
        
        conLocal.update(
            "INSERT INTO registroComponente(fkComponenteTotem, valorUso, dataHoraCaptura) VALUES (?,?,?)", 
            fkComponenteTotem, 
            usoRam, 
            new Date()
        );
    }
    
    public void insertRegistroDisco(Integer fkComponenteTotem){
        Integer fkComponente = 2;
        ShowDisco disco = new ShowDisco();

        Double usoDisco = disco.showUsoDisco();
        Long tempoTransferencia = disco.showTempoTransferencia();
        Long total = disco.showTotal();

        conLocal.update(
            "INSERT INTO registroComponente(fkComponenteTotem, valorUso, tempoTransferencia, dataHoraCaptura) VALUES (?,?,?,?)",
            fkComponenteTotem, 
            usoDisco,
            tempoTransferencia, 
            new Date()
        );
    }
    
    public void insertRegistroCpu(Integer fkComponenteTotem){
        Integer fkComponente = 3;
        ShowCPU cpu = new ShowCPU();
        
        Integer usoCpu = cpu.showUsoCpu();
        Double clockCpu = cpu.showClockCpu();
        String tempoAtividade = cpu.showTempoAtividade();
        Date inicializado = Date.from(cpu.showInicializado());
        
        conLocal.update(
            "INSERT INTO registroComponente(fkComponenteTotem, valorUso, clock, dataHoraCaptura, tempoAtividade, dataInicializacao) VALUES (?,?,?, ?,?,?)", 
            fkComponenteTotem, 
            usoCpu, 
            clockCpu, 
            new Date(), 
            tempoAtividade,
            inicializado
        );
   }
    
    public void insertRegistroRede(Integer fkComponenteTotem){
        Integer fkComponente = 4;
        ShowRede rede = new ShowRede();
        Long bytesEnviados = rede.showBytesEnviados();
        Long bytesRecebidos = rede.showBytesRecebidos();

        conLocal.update(
            "INSERT INTO registroComponente(fkComponenteTotem, bytesEnviados, bytesRecebidos, dataHoraCaptura) VALUES (?,?,?, ?)",
            fkComponenteTotem,
            bytesEnviados, 
            bytesRecebidos,
            new Date()
        );
    }
    
    public void insertRegistros(List<Integer> idComponenteTotem){
        insertRegistroRam(idComponenteTotem.get(0));
        insertRegistroDisco(idComponenteTotem.get(1));
        insertRegistroCpu(idComponenteTotem.get(2));
        insertRegistroRede(idComponenteTotem.get(3));
    }
}
