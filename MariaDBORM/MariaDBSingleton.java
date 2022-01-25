package MariaDBORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import MariaDBORM.Person;


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

  public ResultSet getAllData(String table){
    ResultSet result = null;
    try{
      Statement s = this.connection.createStatement();
      result = s.executeQuery(String.format("SELECT * FROM %s", table));
    }catch (SQLException e){
      e.printStackTrace();
    }
    
    return result;
  }

  public boolean insertPerson(Person p){
    try{

      PreparedStatement ps = this.connection.prepareStatement("insert into personas values (null, ?, ?, ?)");
      ps.setString(1, p.getNombre());
      ps.setString(2, p.getApellido());
      ps.setInt(3, p.getEdad());
      ps.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
      return false;
    }

    return true;
  }

  public boolean deletePerson(int id){
    try{

      PreparedStatement ps = this.connection.prepareStatement("delete from personas where id = ?");
      ps.setInt(1, id);
      ps.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
      return false;
    }

    return true;
  }

  public boolean updatePerson(int id, Person p){
    try{

      PreparedStatement ps = this.connection.prepareStatement("update personas set nombre=?, apellido=?, edad=? where id = ?");
      ps.setString(1, p.getNombre());
      ps.setString(2, p.getApellido());
      ps.setInt(3, p.getEdad());
      ps.setInt(4, id);
      ps.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
      return false;
    }

    return true;
  }


}
