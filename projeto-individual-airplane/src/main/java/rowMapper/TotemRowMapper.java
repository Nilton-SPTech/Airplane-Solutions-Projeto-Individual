package rowMapper;

import com.mycompany.projeto.individual.airplane.model.Totem;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TotemRowMapper implements RowMapper<Totem>{
    @Override
    public Totem mapRow(ResultSet rs, int rowNum) throws SQLException {
        Totem totem = new Totem();
        totem.setIdTotem(rs.getInt("idTotem"));
        totem.setToken(rs.getString("token"));
        totem.setArquitetura(rs.getString("arquitetura"));
        totem.setSistemaOperacional(rs.getString("sistemaOperacional"));
        totem.setProcessador(rs.getString("processador"));
        return totem;
    }
}
