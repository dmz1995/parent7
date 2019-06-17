package cn.itcast.oracle;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.Test;

import java.sql.*;

public class OracleTest {
    @Test
    public void test() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.174.10:1521:orcl","scott","tiger");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from person where pid=?");
        preparedStatement.setLong(1,3);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String pname = resultSet.getString("pname");
            System.out.println(pname);
        }

    }

    @Test
    public void test2() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.174.10:1521:orcl", "scott", "tiger");
        CallableStatement cs = connection.prepareCall("{call p2(?,?)}");
        cs.setLong(1,7788);
        cs.registerOutParameter(2, OracleTypes.NUMBER);
        cs.execute();
        System.out.println(cs.getLong(2));
    }
}
