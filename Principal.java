import java.sql.ResultSet;
import java.sql.SQLException;

import MariaDBORM.MariaDBSingleton;
import MariaDBORM.Person;

public class Principal {
    public static void main(String args[]) {

       MariaDBSingleton db = MariaDBSingleton.connect("jdbc:mysql://localhost:3306", "test_user", "12341234", "test");
       // La tabla personas solamente tiene los siguientes campos
       /**
        * id int(11) not null auto_increment
        * nombre varchar(15),
        * apellido varchar (20),
        * edad int(2)
        */
       ResultSet rs = db.query("SELECT * FROM personas");

       try{

        while(rs.next()){
          System.out.println(
            String.format("%s\t|\t%s\t|\t%s\t|\t%s",
              rs.getInt("id"),
              rs.getString("nombre"),
              rs.getString("apellido"),
              rs.getInt("edad")
            )
          );
        }

       }catch(SQLException e){
         e.printStackTrace();
       }

    }
}


  // Singleton
  //   Singleton s1 = Singleton.getInstance();
  //   Singleton s2 = Singleton.getInstance();
  
  // System.out.println(s1.getValor());
  // s1.addValor(10);
  // System.out.println(s2.getValor());

  // // Ejemplo Factory
  // Factura factura = Factory.getFactura("iva");
  // factura.setData(1);
  // factura.setImporte(1500);
  // System.out.println(factura.getImporteIva());