package lk.sliit.PAF.user.model;

import lk.sliit.PAF.user.dto.BuyerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerModel {
    private static BuyerModel buyerModelInstance;
    public static BuyerModel getInstance(){
        if(buyerModelInstance == null){
            buyerModelInstance = new BuyerModel();
        }
        return buyerModelInstance;
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

    public int getLastBuyerId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM buyers ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    public int insertBuyerDetail(String fName, String lName, String email, String contactNo, String address, String zipCode, String password) throws Exception {
        String output = "";
        int id = getLastBuyerId();
        try{
            Connection connection = connect();

            if(connection == null){
                return 0;
            }
            String query = "insert into buyers (`id`, `fName`, `lName`, `email`, `contactNo`, `address` , `zipCode` , `pass`) values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id+1);
            preparedStatement.setString(2, fName);
            preparedStatement.setString(3, lName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, contactNo);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, zipCode);
            preparedStatement.setString(8, password);

            preparedStatement.execute();
            connection.close();
            output = "Buyer registered Successfully";
        }catch(Exception e){
            output = "Error while registering buyer";
            e.printStackTrace();
        }
        return  id + 1;
    }

    public String updateBuyerDetail(String fName, String lName, String email, String contactNo, String address, String zipCode,String pass){
        String output = "";
        try{
            Connection connection = connect();
            int id = getLastBuyerId();
            if(connection == null){
                return "Error connecting to the database";
            }
            String query = "UPDATE buyers SET fName=?, lName=?, email=?, contactNo=?, address=? , zipCode=? ,`pass` =? WHERE id=?";
            String q2 = "SELECT `pass` FROM buyers WHERE id = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            Statement st = connection.createStatement();

            ResultSet rs = CrudUtil.execute("SELECT `pass` FROM buyers WHERE id = " + id);
            if (rs.next()) {
                pass = rs.getString(1);
            }

            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, contactNo);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, zipCode);
            preparedStatement.setString(7, pass);
            preparedStatement.setInt(8, id);

            preparedStatement.execute();
            connection.close();
            output = "Buyer Details Updated Successfully";
        }catch(Exception e){
            output = "Error while Updating Buyer";
            e.printStackTrace();
        }
        return  output;
    }

    public boolean deleteBuyer(int buyerId)
    {
        String output = "";
        try
        {
            Connection connection = connect();
            if (connection == null){
                return false;
            }

            String query = "delete from buyers where id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, (buyerId));

            preparedStatement.execute();
            connection.close();
            output = "Deleted successfully";
        }
        catch (Exception e)
        {
            output = "Error while deleting the account.";
            System.err.println(e.getMessage());
        }
        return true;
    }

    public BuyerDTO findBuyer(String buyerId) {
        Connection con = connect();
        String sql = "select * from buyers where `id` =" + buyerId;
        BuyerDTO buyerDTO = new BuyerDTO();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                buyerDTO.setId(rs.getInt(1));
                buyerDTO.setfName(rs.getString(2));
                buyerDTO.setlName(rs.getString(3));
                buyerDTO.setEmail(rs.getString(4));
                buyerDTO.setContactNo(rs.getString(5));
                buyerDTO.setAddress(rs.getString(6));
                buyerDTO.setZipCode(rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return buyerDTO;
    }

    //    public List<BuyerDTO> listAllBuyers() throws Exception {
//        Connection con = connect();
//        int id = getLastBuyerId();
//        if (con == null)
//        {return null; }
//        List<BuyerDTO> buyers = new ArrayList<>();
//        String query = "Select * from buyers";
//        try{
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//                BuyerDTO buyerDTO = new BuyerDTO();
//                buyerDTO.setId(rs.getInt(1));
//                buyerDTO.setfName(rs.getString(2));
//                buyerDTO.setlName(rs.getString(3));
//                buyerDTO.setEmail(rs.getString(4));
//                buyerDTO.setContactNo(rs.getString(5));
//                buyerDTO.setAddress(rs.getString(6));
//                buyerDTO.setZipCode(rs.getString(7));
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return buyers;
//    }
}