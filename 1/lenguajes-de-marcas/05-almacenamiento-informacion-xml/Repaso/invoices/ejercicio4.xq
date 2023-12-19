<table>
  <tr>
    <th>FECHA</th>
    <th>ID</th>
    <th>CLIENTE</th>
    <th>PRECIO</th>
    <th>DESCUENTO</th>
    <th>FINAL</th>
  </tr>
  {
    for $factura in //Invoice
      let $precioTotal := sum	(
                                for $linea in //Line[@id_fac=$factura/Id] 
                                  return $linea/Quantity * $linea/UnitPrice
                               )
      let $descuento := $factura/Discount
      let $precioFinal := round($precioTotal - ($precioTotal * $descuento), 2)
      order by $factura/Date, xs:integer($factura/Client), $precioFinal
      return
      (
        <tr>
          <td>{$factura/Date/text()}</td>
          <td>{$factura/Id/text()}</td>
          <td>{$factura/Client/text()}</td>
          <td>{$precioTotal}</td>
          <td>{$descuento/text()}</td>
          <td>{$precioFinal}</td>
        </tr>
      )
  }
</table>