package MariaDBORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MariaDBSingleton {
  
  private static MariaDBSingleton db;
  private Connection connection;


  private MariaDBSingleton(String url, String username, String password, String database){
    try{
      Class.forName("org.mariadb.jdbc.Driver");
      this.connection = DriverManager.getConnection(String.format(url+"/%s", database), username, password);
    } catch (ClassNotFoundException e){
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }

  public static MariaDBSingleton connect(String url, String username, String password, String database){
    if(db == null){
      db = new MariaDBSingleton(url, username, password, database);
    }

    return db;
  }

  public Connection getConnection(){
    return this.connection;
  }

  public ResultSet query(String query){
    ResultSet result = null;
    try{
      Statement s = this.connection.createStatement();
      result = s.executeQuery(query);
    }catch (SQLException e){
      e.printStackTrace();
    }
    
    return result;
  }

  public boolean execute(String query){
    try{
      this.connection.createStatement().execute(query);
    }catch(SQLException e){
      e.printStackTrace();
      return false;
    }

    return true;
  }


}
