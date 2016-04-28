/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// New BookMyShow Clone project

package bookmyshowui;

import java.sql.*;
import java.util.UUID;
/**
 *
 * @author Sanjib
 */

public class BookMyShow {
    private String screenId;
    public void setSereenId(String scrnId)
    {
        screenId = scrnId;
    }
    
    public String getSereenId()
    {
        return screenId;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        //String url = "jdbc:microsoft:sqlserver://allium.arvixe.com;DatabaseName=dewsacademydb";
        
        //String url ="jdbc:sqlserver://HP-PC\\SQLEXPRESS;databaseName=DewsAcademy;integratedSecurity=true";
        //String url = "jdbc:sqlserver://HP-PC;instanceName=SQLEXPRESS;DatabaseName=BookMyShow;integratedSecurity=true";
//        String user = "dumanhill";
//        String pass = "Elex@123";
        
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        Connection myCon = DriverManager.getConnection(url);
//        
//        Statement myStmt = myCon.createStatement();
//        ResultSet myRs = myStmt.executeQuery("select * from Theatre");
//        while(myRs.next())
//        {
//            String theatreName = myRs.getString("TheatreName");
//            System.out.println(theatreName);
//        }
        
        Theatre t = new Theatre();
        //t.retreiveRecord();
        //t.insertRecord("Biscope", "Multiplex", "Kolkata");
        //t.updateRecord("05164601-7CC3-458F-A62D-9D80BBF53C56", "City", "Delhi");
        //t.deleteRecord("C1FF2B1C-A571-4788-87E7-12353D504BD0");
        
        User u = new User();
        //u.insertRecord("sanjib1991", "dell@143");
        //u.retreiveRecord();
        //u.updateRecord("34447E95-956B-4CBB-ADD0-E58418EAB967", "UserName", "sanjib1992");
        //u.deleteRecord("34447E95-956B-4CBB-ADD0-E58418EAB967");
        
        Screen sc = new Screen();
        //sc.insertRecord("C2D01992-161A-417D-BD67-92650CF94C6E", "Scree2");
        //sc.retreiveRecord();
        //sc.updateRecord("C0FAEF85-1098-43C0-AEED-E8F5AB33DD80", "ScreenName", "Screen2");
        //sc.deleteRecord("C0FAEF85-1098-43C0-AEED-E8F5AB33DD80");
        
        
        Home hm = new Home();
        hm.LogOff.setVisible(false);
        hm.jButton1.setVisible(false);
        hm.setVisible(true); 
        
    }
}

class BookSeats
{
    private String url = "jdbc:sqlserver://HP-PC;instanceName=SQLEXPRESS;DatabaseName=BookMyShow;integratedSecurity=true";
    private Connection myCon;
    private Statement stmt;
    private ResultSet myRs;
    
    public ResultSet showSeatPrice(UUID screenId) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String seatPriceRecord = "select * from ScreenRateMaster" +
                                            " where ScreenId = '"+ screenId +"'";
            myRs = stmt.executeQuery(seatPriceRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        
        return  myRs;
    }
    
    public ResultSet getAllSeats(UUID screenId) throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String seatPriceRecord = "select * from SeatDetails" +
                                "  join ScreenRateMaster" +
                                "  on SeatDetails.ScreenId = ScreenRateMaster.ScreenId and SeatDetails.SeatType = ScreenRateMaster.SeatType" +
                                "  where SeatDetails.ScreenId = '"+ screenId +"'";
            myRs = stmt.executeQuery(seatPriceRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        
        return  myRs;
    }
}

class Theatre
{
    private String url = "jdbc:sqlserver://HP-PC;instanceName=SQLEXPRESS;DatabaseName=BookMyShow;integratedSecurity=true";
    private Connection myCon;
    private Statement stmt;
    
    public void retreiveRecord()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String retreiveTheatreRecord = "SELECT * FROM Theatre";
            ResultSet myRs = stmt.executeQuery(retreiveTheatreRecord);
            while(myRs.next())
            {
                String theatreName = myRs.getString("TheatreName");
                System.out.println(theatreName);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void insertRecord(String theatreName, String theatreType, String city)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String insertTheatreRecord = "INSERT INTO Theatre" + " VALUES (NEWID(), '"+theatreName+"', '"+theatreType+"', '"+city+"')";
            stmt.executeUpdate(insertTheatreRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateRecord(String theatreId, String columnName, String colValue)
    {
        UUID tId = UUID.fromString(theatreId);
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String updateTheatreRecord = "UPDATE Theatre" + " SET "+columnName+"='"+colValue+"' WHERE TheatreId = '"+tId+"'";
            stmt.executeUpdate(updateTheatreRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void deleteRecord(String theatreId)
    {
        UUID tId = UUID.fromString(theatreId);
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String deleteTheatreRecord = "DELETE FROM Theatre WHERE TheatreId = '"+tId+"'";
            stmt.executeUpdate(deleteTheatreRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}


class User
{
    private String url = "jdbc:sqlserver://HP-PC;instanceName=SQLEXPRESS;DatabaseName=BookMyShow;integratedSecurity=true";
    private Connection myCon;
    private Statement stmt;
    
    public void retreiveRecord()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String retreiveTheatreRecord = "SELECT * FROM [User]";
            ResultSet myRs = stmt.executeQuery(retreiveTheatreRecord);
            while(myRs.next())
            {
                String userName = myRs.getString("UserName");
                System.out.println(userName);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void insertRecord(String userName, String password)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String insertUserRecord = "INSERT INTO [User]" + " VALUES (NEWID(), '"+userName+"', '"+password+"')";
            stmt.executeUpdate(insertUserRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateRecord(String userId, String columnName, String colValue)
    {
        UUID tId = UUID.fromString(userId);
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String updateUserRecord = "UPDATE [User]" + " SET "+columnName+"='"+colValue+"' WHERE UserId = '"+tId+"'";
            stmt.executeUpdate(updateUserRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void deleteRecord(String userId)
    {
        UUID tId = UUID.fromString(userId);
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String deleteUserRecord = "DELETE FROM [User] WHERE UserId = '"+tId+"'";
            stmt.executeUpdate(deleteUserRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}


class Screen
{
    private String url = "jdbc:sqlserver://HP-PC;instanceName=SQLEXPRESS;DatabaseName=BookMyShow;integratedSecurity=true";
    private Connection myCon;
    private Statement stmt;
    
    public void retreiveRecord()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String retreiveScreenRecord = "SELECT * FROM Screen";
            ResultSet myRs = stmt.executeQuery(retreiveScreenRecord);
            while(myRs.next())
            {
                String screenName = myRs.getString("ScreenName");
                System.out.println(screenName);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void insertRecord(String theatreId, String screenName)
    {
        try
        {
            UUID tId = UUID.fromString(theatreId);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String insertScreenRecord = "INSERT INTO Screen" + " VALUES (NEWID(), '"+tId+"', '"+screenName+"')";
            stmt.executeUpdate(insertScreenRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateRecord(String screenId, String columnName, String colValue)
    {
        UUID tId = UUID.fromString(screenId);
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String updateScreenRecord = "UPDATE Screen" + " SET "+columnName+"='"+colValue+"' WHERE ScreenId = '"+tId+"'";
            stmt.executeUpdate(updateScreenRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void deleteRecord(String screenId)
    {
        UUID tId = UUID.fromString(screenId);
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection(url);
            stmt = myCon.createStatement();
            String deleteScreenRecord = "DELETE FROM Screen WHERE ScreenId = '"+tId+"'";
            stmt.executeUpdate(deleteScreenRecord);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}





