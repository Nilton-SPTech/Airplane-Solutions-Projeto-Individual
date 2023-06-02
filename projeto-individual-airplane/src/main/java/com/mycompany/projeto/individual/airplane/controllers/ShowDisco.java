package com.mycompany.projeto.individual.airplane.controllers;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;


public class ShowDisco {
    private Looca looca = new Looca();
    
    public String showNomeDisco(){
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();         
        return discos.get(0).getNome();
    }
    
    public Long showTotal(){
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();         
        return discos.get(0).getTamanho();
    }
    
    public Double showUsoDisco() {
        File disk = new File("/"); // diretório raiz do disco
        long totalSpace = disk.getTotalSpace(); // tamanho total do disco em bytes
        long usedSpace = totalSpace - disk.getFreeSpace(); // espaço usado em bytes

        Double usedGB = (double) usedSpace / (1024 * 1024 * 1024);
        
        Double value = usedGB;
        DecimalFormat df = new DecimalFormat("#,##0.00"); // define o padrão de formatação
        String formattedValue = df.format(value); // formata o valor double
        Double numero = Double.parseDouble(formattedValue.replace(",", "."));
        
        return numero;
    }
    
    public Long showTempoTransferencia(){
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();         
        return discos.get(0).getTempoDeTransferencia();
    }
    
}
