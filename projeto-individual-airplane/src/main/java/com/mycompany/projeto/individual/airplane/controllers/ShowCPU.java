package com.mycompany.projeto.individual.airplane.controllers;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import java.time.Instant;


public class ShowCPU {
   private Looca looca = new Looca();
   private Processador processador = looca.getProcessador();
   
   public String nomeCpu(){
        return processador.getNome();
    }
    
    public String fabricanteCpu(){
        return processador.getFabricante();
    }
    
    public Double showFrequenciaCpu(){
        return Double.valueOf(processador.getFrequencia());
    }
    
    public String showModeloCpu(){
        return processador.getIdentificador();
    }
    
    public Double showClockCpu(){
        Integer clock = processador.getNumeroCpusFisicas() + processador.getNumeroCpusLogicas();
        Double d = clock.doubleValue();
        return d;
    }
    
    public Integer showUsoCpu(){
        return processador.getUso().intValue();
    }
    
    public String showTempoAtividade(){
        Long tempo = looca.getSistema().getTempoDeAtividade();
        return Conversor.formatarSegundosDecorridos(tempo);
    }
   
    public Instant showInicializado(){
        return looca.getSistema().getInicializado(); 
    }
}
