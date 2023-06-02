
import com.mycompany.projeto.individual.airplane.controllers.Totem;
import com.mycompany.projeto.individual.airplane.models.Connection;
import com.mycompany.projeto.individual.airplane.models.RegistroTotemCRUD;
import com.mycompany.projeto.individual.airplane.models.TablesCreate;
import com.mycompany.projeto.individual.airplane.models.TotemCRUD;

import java.util.Scanner;
import org.springframework.jdbc.core.JdbcTemplate;

public class App {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        TotemCRUD totemCrud = new TotemCRUD();
        RegistroTotemCRUD registroCrud = new RegistroTotemCRUD();
        
        try{
            Connection conexao = new Connection(false);
            JdbcTemplate con = conexao.getConnection();
            
            con.queryForRowSet("SELECT * FROM componente");
        }
        catch(Exception e){
            System.out.println("Deu ruim");
            TablesCreate t = new TablesCreate();
            t.creatTables();
        }
        
        
        System.out.println("Digite o token para acessar máquina:");
        String token = leitor.nextLine();
        
        Totem totem = totemCrud.getTotemByToken(token);
        
        if(totem != null){
            if(token.equals(totem.getToken())){
                System.out.println("TOKEN VÁLIDO");
                
                try{
                    totemCrud.updateBoolCaptura(token, 1);
                    registroCrud.insertRegistroComponente(totem.getIdTotem(), totem.getFkCompanhia(), token);
                }
                catch(Exception e){
                    System.out.println("Erro na obtenção dos dados");
                }               
            }
        }
        else{
            System.out.println("TOKEN INVÁLIDO");
        }
        
        
        
    }
}
