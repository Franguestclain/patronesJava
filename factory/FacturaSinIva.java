package factory;

public class FacturaSinIva extends Factura {

  @Override
  public double getImporteIva() {
    return getImporte();
  }
  
}
