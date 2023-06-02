package com.mycompany.projeto.individual.airplane.controllers;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import java.util.ArrayList;
import java.util.List;


public class ShowRede {
  private final Looca looca = new Looca();
  private final Rede rede = looca.getRede();
  
  public Long showBytesRecebidos(){
    List ipv4 = new ArrayList(); 

    List<RedeInterface> rede = looca.getRede().getGrupoDeInterfaces().getInterfaces();

    Long bytes = null;
    for(RedeInterface redeInterface : rede){
        if(redeInterface.getEnderecoIpv4().size() > 0){
            return bytes = redeInterface.getBytesRecebidos();
        }
    }      
    return null;
  }
  
  public Long showBytesEnviados(){
    List ipv4 = new ArrayList(); 

    List<RedeInterface> rede = looca.getRede().getGrupoDeInterfaces().getInterfaces();

    Long bytes = null;
    for(RedeInterface redeInterface : rede){
        if(redeInterface.getEnderecoIpv4().size() > 0){
            return bytes = redeInterface.getBytesEnviados();
        }
    }      
    return null;
  }

    
}
