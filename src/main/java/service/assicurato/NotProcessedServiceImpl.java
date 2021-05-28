package service.assicurato;

import connection.MyConnection;
import dao.Constants;
import dao.assicuratonew.notprocessed.NotProcessedDAOImpl;
import model.Assicurato;

import java.sql.Connection;

public class NotProcessedServiceImpl {

    private NotProcessedDAOImpl notProcessedDAOImpl = new NotProcessedDAOImpl();

    public void insert(Assicurato assicurato) throws Exception {

        try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEW_SCHEMA)) {

            notProcessedDAOImpl.setConnection(connection);

            notProcessedDAOImpl.insert(assicurato);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }



}
