package com.mycompany.projeto.individual.airplane.controllers;

import lombok.Getter;
import lombok.Setter;

public class ComponenteTotem {
    @Getter @Setter private Integer fkTotem; 
    @Getter @Setter private Integer fkComponente; 
    @Getter @Setter private Long total; 
    @Getter @Setter private Long frequencia;
    @Getter @Setter private String enderecoIPv4Rede;
    @Getter @Setter private String enderecoIPv4Totem;
}
