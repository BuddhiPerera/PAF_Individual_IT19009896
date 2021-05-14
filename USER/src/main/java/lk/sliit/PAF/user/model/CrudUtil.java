package lk.sliit.PAF.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {
    public static <T> T execute(String sql, Object... params) throws Exception {
        Connection con = BuyerModel.connect();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject((i + 1), params[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }
        return (T)((Boolean) (preparedStatement.executeUpdate() > 0));
    }
}
