package rahul_jenn.v1_rawSQL;

import rahul_jenn.CONSTANTS;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by denze on 7/13/2018.
 */

public class Main {

    public static void main(String[] args) {

        String firstName,lastName;
        String[] row;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    CONSTANTS.DB_URL
                    + "?user=" + CONSTANTS.USERNAME
                    + "&password=" + CONSTANTS.PASSWORD
            );
            Statement statement = connection.createStatement();

//            statement.execute(
//                    "create table user (id integer(20) auto_increment,firstName varchar(20), lastName varchar(20), primary key(id));");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into user(firstName,lastName) values (?,?)"
            );
            Scanner sc = new Scanner(new File(CONSTANTS.FILE_PATH));
//            while(sc.hasNextLine()){
//                row = sc.nextLine().split(",");
//                firstName = row[0];
//                lastName = row[1];
//                preparedStatement.setString(1,firstName);
//                preparedStatement.setString(2,lastName);
//                preparedStatement.executeUpdate();
//            }

            //verify insertion
            ResultSet resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()){
                System.out.println("User: " + resultSet.getString("id") + " | " + "firstName: " + resultSet.getString("firstName") + " | lastName: " + resultSet.getString("lastName"));
            }
            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void clearTable(Connection connection,String tableName){
        try{
            connection.createStatement().execute("truncate table " + tableName);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
