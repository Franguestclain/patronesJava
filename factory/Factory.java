package factory;

public class Factory {
  
  public static Factura getFactura(String tipo){
    if("iva".equalsIgnoreCase(tipo)){
      return new FacturaIva();
    }

    return new FacturaSinIva();
  }

}
