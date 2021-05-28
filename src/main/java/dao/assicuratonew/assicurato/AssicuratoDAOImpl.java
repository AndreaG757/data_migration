package dao.assicuratonew.assicurato;

import dao.AbstractMySQLDAO;
import model.Assicurato;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AssicuratoDAOImpl extends AbstractMySQLDAO {

    public void insert(Assicurato input) throws Exception {

        if (isNotActive())
            throw new Exception("Connessione non attiva in insert() di AssicuratoDAOImpl. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ASSICURATO (NOME, COGNOME, DATA_NASCITA, NUMERO_SINISTRI, CODICE_FISCALE) VALUES (?, ?, ?, ?, ?);")) {

            pstmt.setString(1, input.getNome());
            pstmt.setString(2, input.getCognome());
            pstmt.setDate(3, new java.sql.Date(input.getDataNascita().getTime()));
            pstmt.setInt(4, input.getNumeroSinistri());
            pstmt.setString(5, input.getCodiceFiscale());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
