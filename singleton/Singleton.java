package singleton;

public class Singleton {
 
  private static Singleton singleton;
  private int valor;


  private Singleton(){
    this.valor = 4;
  }

  public static Singleton getInstance(){
    if(singleton == null){
      singleton = new Singleton();
    }

    return singleton;
  }

  public int getValor(){
    return this.valor;
  }

  public void addValor(int dato){
    this.valor += dato;
  }

}
