package com.mycompany.projeto.individual.airplane.controllers;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Totem {
    @Getter @Setter private Integer idTotem;
    @Getter @Setter private Integer fkCompanhia;
    @Getter @Setter private String fabricante;
    @Getter @Setter private String arquitetura;
    @Getter @Setter private String sistemaOperacional;
    @Getter @Setter private String processador;
    @Getter @Setter private String localizacaoTotem;
    @Getter @Setter private String token;

    @Getter @Setter private List<ComponenteTotem> componentes; 
    
    public Totem(){}
    
    public Totem(Integer idTotem, Integer fkCompanhia, String fabricante, String arquitetura, String sistemaOperacional, String processador, String localizacaoTotem, String token) {
        this.idTotem = idTotem;
        this.fkCompanhia = fkCompanhia;
        this.fabricante = fabricante;
        this.arquitetura = arquitetura;
        this.sistemaOperacional = sistemaOperacional;
        this.processador = processador;
        this.localizacaoTotem = localizacaoTotem;
        this.token = token;
    }

    public ComponenteTotem showRam(){
        return componentes.get(0);
    }
    
    public ComponenteTotem showDisco(){
        return componentes.get(1);
    }
    
    public ComponenteTotem showCpu(){
        return componentes.get(2);
    }
    public ComponenteTotem showRede(){
        return componentes.get(3);
    }
}
