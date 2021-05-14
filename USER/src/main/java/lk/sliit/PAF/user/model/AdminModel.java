package lk.sliit.PAF.user.model;

import lk.sliit.PAF.user.dto.AdminBuyerDTO;
import lk.sliit.PAF.user.dto.AdminLoginDTO;
import lk.sliit.PAF.user.dto.AdminResearcherDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {
    private static AdminModel adminModelInstance;

    public static AdminModel getInstance(){
        if(adminModelInstance == null){
            adminModelInstance = new AdminModel();
        }
        return adminModelInstance;
    }

    public static Connection connect(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "1234");
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public List<AdminResearcherDTO> findAllResearchers() throws SQLException {
        Connection connection = connect();
        if(connection == null){
            return null;
        }
        List<AdminResearcherDTO> adminResearcherDTOS = new ArrayList<>();
        String query = "SELECT * FROM researchers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            AdminResearcherDTO adminResearcherDTO = new AdminResearcherDTO();
            adminResearcherDTO.setId(resultSet.getInt(1));
            adminResearcherDTO.setfName(resultSet.getString(2));
            adminResearcherDTO.setlName(resultSet.getString(3));
            adminResearcherDTO.setEmail(resultSet.getString(4));
            adminResearcherDTO.setContactNo(resultSet.getString(5));
            adminResearcherDTO.setAddress(resultSet.getString(6));
            adminResearcherDTO.setZipCode(resultSet.getString(7));
            adminResearcherDTO.setRate(resultSet.getString(8));
            adminResearcherDTO.setPassword(resultSet.getString(9));

            adminResearcherDTOS.add(adminResearcherDTO);
        }
        return adminResearcherDTOS;
    }

    public boolean deleteResearcher(int id) throws SQLException {
        Connection connection = connect();
        if(connection == null){
            return false;
        }
        String query = "DELETE FROM researchers WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        connection.close();
        return true;
    }

    public List<AdminBuyerDTO> findAllBuyers() throws SQLException {
        Connection connection = connect();
        if(connection == null){
            return null;
        }
        List<AdminBuyerDTO> adminBuyerDTOS = new ArrayList<>();
        String query = "SELECT * FROM buyers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            AdminBuyerDTO adminBuyerDTO = new AdminBuyerDTO();
            adminBuyerDTO.setId(resultSet.getInt(1));
            adminBuyerDTO.setfName(resultSet.getString(2));
            adminBuyerDTO.setlName(resultSet.getString(3));
            adminBuyerDTO.setEmail(resultSet.getString(4));
            adminBuyerDTO.setContactNo(resultSet.getString(5));
            adminBuyerDTO.setAddress(resultSet.getString(6));
            adminBuyerDTO.setZipCode(resultSet.getString(7));
            adminBuyerDTO.setPassword(resultSet.getString(8));

            adminBuyerDTOS.add(adminBuyerDTO);
        }
        return adminBuyerDTOS;
    }

    public boolean deleteBuyer(int id) throws SQLException {
        Connection connection = connect();
        if(connection == null){
            return false;
        }
        String query = "DELETE FROM buyers WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        connection.close();
        return true;
    }
}
