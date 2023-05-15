package com.mycompany.projeto.individual.airplane.model;

import lombok.Getter;
import lombok.Setter;

public class Totem {
    @Getter @Setter private Integer idTotem; 
    @Getter @Setter private String token; 
    @Getter @Setter private String arquitetura;
    @Getter @Setter private String sistemaOperacional;
    @Getter @Setter private String processador;

    @Override
    public String toString() {
        return "Totem: " + "idTotem = " + idTotem + ", token = " + token + ", arquitetura = " + arquitetura + ", sistemaOperacional = " + sistemaOperacional + ", processador = " + processador + "\n";
    }
    
    
}
