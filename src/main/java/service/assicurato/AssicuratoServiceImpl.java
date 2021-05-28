package service.assicurato;

import connection.MyConnection;
import dao.Constants;
import dao.assicuratonew.assicurato.AssicuratoDAOImpl;
import dao.assicuratonew.notprocessed.NotProcessedDAOImpl;
import dao.assicuratoold.OldAssicuratoDAOImpl;
import model.Assicurato;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AssicuratoServiceImpl {

    private AssicuratoDAOImpl assicuratoDAOImpl = new AssicuratoDAOImpl();
    private NotProcessedServiceImpl notProcessedDAOImpl = new NotProcessedServiceImpl();
    private OldAssicuratoServiceImpl oldAssicuratoServiceImpl = new OldAssicuratoServiceImpl();

    public void dataMigrator() throws Exception {

        List<Assicurato> assicurati = oldAssicuratoServiceImpl.listAll();

        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEW_SCHEMA)) {

            assicuratoDAOImpl.setConnection(connection);

            for (Assicurato assicuratoItem : assicurati) {
                System.out.println(assicuratoItem);
                if (validate(assicuratoItem))
                    assicuratoDAOImpl.insert(assicuratoItem);
                else
                    notProcessedDAOImpl.insert(assicuratoItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean validate(Assicurato input) {

        if (input.getNumeroSinistri() == null) {
            return false;
        } else if (input.getNome().isEmpty() || input.getNome().equals("")) {
            return false;
        } else if (input.getCognome().isEmpty() || input.getCognome().equals("")) {
            return false;
        } else if (input.getCodiceFiscale().isEmpty() || input.getCodiceFiscale().equals("")) {
            return false;
        }

        return true;

    }

}
