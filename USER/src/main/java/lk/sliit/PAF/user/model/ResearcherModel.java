package lk.sliit.PAF.user.model;

import lk.sliit.PAF.user.dto.ResearcherDTO;

import java.sql.*;


public class ResearcherModel {
    private static ResearcherModel researcherModelInstance;

    public static ResearcherModel getInstance(){
        if(researcherModelInstance == null){
            researcherModelInstance = new ResearcherModel();
        }
        return researcherModelInstance;
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

    public int getLastResearcherId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM researchers ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    public int insertResearcherDetail(String fName, String lName, String email, String contactNo, String address, String zipCode, String rate, String password) throws Exception {
        String output = "";
        int id = getLastResearcherId();
        try{
            Connection connection = connect();
            if(connection == null){
                return 0;
            }
            String query = "insert into researchers (`id`, `fName`, `lName`, `email`, `contactNo`, `address` , `zipCode` , `rate` , `pass`) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id+1);
            preparedStatement.setString(2,fName);
            preparedStatement.setString(3,lName);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,contactNo);
            preparedStatement.setString(6,address);
            preparedStatement.setString(7,zipCode);
            preparedStatement.setString(8,rate);
            preparedStatement.setString(9,password);

            preparedStatement.execute();
            connection.close();


        }catch(Exception e){

            e.printStackTrace();
        }
        return  id + 1;
    }

    public ResearcherDTO findResearcher(String researcherId) {
        Connection connection = connect();
        String sql = "select * from `researchers` where `id` =" + researcherId;
        ResearcherDTO researcherDTO = new ResearcherDTO();
        try{
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            if(resultSet.next()){
                researcherDTO.setId(resultSet.getInt(1));
                researcherDTO.setfName(resultSet.getString(2));
                researcherDTO.setlName(resultSet.getString(3));
                researcherDTO.setEmail(resultSet.getString(4));
                researcherDTO.setContactNo(resultSet.getString(5));
                researcherDTO.setAddress(resultSet.getString(6));
                researcherDTO.setZipCode(resultSet.getString(7));
                researcherDTO.setRate(resultSet.getString(8));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return researcherDTO;
    }

    public String updateResearcherDetail(String getfName, String getlName, String email, String contactNo, String address, String zipCode, String rate) throws Exception {
        String output = "";
        int id = getLastResearcherId();
        try{
            Connection connection = connect();
            if(connection == null){
                return "Error connecting to the database";
            }
            String query = "UPDATE researchers SET fName=?, lName=?, email=?, contactNo=?, address=? , zipCode=? , rate =? WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);


            preparedStatement.setString(1, getfName);
            preparedStatement.setString(2, getlName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, contactNo);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, zipCode);
            preparedStatement.setString(7, rate);
            preparedStatement.setInt(8, id);

            preparedStatement.execute();
            connection.close();
            output = "Researcher Details Updated Successfully";
        }catch(Exception e){
            output = "Error while Updating Researcher";
            e.printStackTrace();
        }
        return  output;
    }

    public boolean deleteResearcher(int researcherId) {
        String output = "";
        try
        {
            Connection connection = connect();
            if (connection == null){
                return false;
            }

            String query = "delete from researchers where id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, (researcherId));

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
}
