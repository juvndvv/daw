for $cliente in distinct-values(//Invoice/Client)
  let $cantidadFacturas := count(//Invoice/Client[text()=$cliente])
  order by $cantidadFacturas
  return <p>{concat('El cliente ', $cliente, ' tiene ', $cantidadFacturas, ' facturas')}</p>