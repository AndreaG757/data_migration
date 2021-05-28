package dao.assicuratoold;

import dao.AbstractMySQLDAO;
import model.Assicurato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OldAssicuratoDAOImpl extends AbstractMySQLDAO {

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Assicurato> selectFromOldSchema() throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        List<Assicurato> assicurati = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT D.ID, D.CODICE_FISCALE, A.NOME, A.COGNOME, A.DATA_NASCITA, COUNT(S.ID) AS NUMERO_SINISTRI\n" +
                        "FROM DATI_FISCALI D\n" +
                        "INNER JOIN ANAGRAFICA A ON A.FK_DATI_FISCALI = D.ID\n" +
                        "LEFT JOIN SINISTRI S ON D.ID = S.FK_ANAGRAFICA\n" +
                        "GROUP BY D.ID;")) {

            ResultSet rs = ps.executeQuery();   

            while(rs.next()) {
                Assicurato temp = new Assicurato();
                temp.setId(rs.getLong("ID"));
                temp.setCodiceFiscale(rs.getString("CODICE_FISCALE"));
                temp.setNome(rs.getString("NOME"));
                temp.setCognome(rs.getString("COGNOME"));
                temp.setDataNascita(rs.getDate("DATA_NASCITA"));
                temp.setNumeroSinistri(rs.getInt("NUMERO_SINISTRI"));

                assicurati.add(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return assicurati;
    }

}
