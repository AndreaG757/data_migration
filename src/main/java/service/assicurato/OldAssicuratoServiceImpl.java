package service.assicurato;

import connection.MyConnection;
import dao.Constants;
import dao.assicuratoold.OldAssicuratoDAOImpl;
import model.Assicurato;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OldAssicuratoServiceImpl {

    private OldAssicuratoDAOImpl oldAssicuratoDAO = new OldAssicuratoDAOImpl();

    public List<Assicurato> listAll() throws Exception {

       List<Assicurato> result = new ArrayList<>();

       try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_OLD_SCHEMA)) {

           oldAssicuratoDAO.setConnection(connection);

           result = oldAssicuratoDAO.selectFromOldSchema();

       } catch (Exception e) {
           e.printStackTrace();
       }

       return result;

    }

}
