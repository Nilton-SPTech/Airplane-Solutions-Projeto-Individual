package com.mycompany.projeto.individual.airplane.controllers;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;

public class ShowMemoria {
    private Looca looca = new Looca();
    private Memoria memoria = looca.getMemoria();
    
    public Memoria showMemoria(){
        return memoria;
    }
    
    public Long showUsoRam(){
        return memoria.getEmUso();
    }
    
    public Long showDisponivelRam(){
        return memoria.getDisponivel();
    }
    
    public Long showTotalRam(){
        return memoria.getTotal();
    }
}
