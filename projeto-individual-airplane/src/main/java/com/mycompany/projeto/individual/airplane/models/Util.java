package com.mycompany.projeto.individual.airplane.models;
public class Util {
    public String replaceProcessador(String processador){
        processador = processador.replace("Intel(R) Xeon(R) CPU", "").replace("v4 @ 2.30GHz", "").replace("", "");
        return processador;
    }
    
    public String convertByteForGb(Long valor){
        return String.valueOf(valor / 1000000000); 
    }
}
