package factory;

public abstract class Factura {

  private int dato;
  private double importe;

  public int getDato(){
    return this.dato;
  }

  public void setData(int data){
    this.dato = data;
  }

  public double getImporte(){
    return this.importe;
  }

  public void setImporte(double importe){
    this.importe = importe;
  }

  public abstract double getImporteIva();

}
