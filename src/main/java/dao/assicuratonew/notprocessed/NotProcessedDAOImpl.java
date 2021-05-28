package dao.assicuratonew.notprocessed;

import dao.AbstractMySQLDAO;
import model.Assicurato;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NotProcessedDAOImpl extends AbstractMySQLDAO {

    public void insert(Assicurato input) throws Exception {

        if (isNotActive())
            throw new Exception("Connessione non attiva in insert() di AssicuratoDAOImpl. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        try(PreparedStatement pstmt = connection.prepareStatement("INSERT INTO NOT_PROCESSED (CODICE_FISCALE, OLD_ID) VALUES (?, ?);")) {

            pstmt.setString(1, input.getCodiceFiscale());
            pstmt.setLong(2, input.getId());
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
