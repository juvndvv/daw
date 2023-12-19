for $factura in //Invoice
return
(
  <div>
      <h3>{$factura/Id/text()}</h3>
      <h3>{$factura/Client/text()}</h3>
      <h3>{$factura/Vendor/text()}</h3>
    {
      for $linea in //Line[@id_fac=$factura/Id]
      let $precioTotal := $linea/UnitPrice * $linea/Quantity
      return
      (
        <p>{concat('articulo: ', $linea/Article, ', cantidad: ', $linea/Quantity, ', precio_total: ', $precioTotal)}</p>
      )
    }
  </div>
)
