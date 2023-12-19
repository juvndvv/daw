<table>
  <tr>
    <th>Vendedor</th>
    <th>Año</th>
    <th>Numero facturas</th>
  </tr>
  {
    for $year in distinct-values(//Invoice/Date)
      order by $year
      return
      (
        <div>
          {
            for $vendedor in distinct-values(//Invoice/Vendor)
              let $numeroFacturas := count(//Invoice[Date=$year and Vendor=$vendedor])
              return
              (
                <tr>
                  <td>{$vendedor}</td>
                  <td>{$year}</td>
                  <td>{$numeroFacturas}</td>
                </tr>
              )
          }
        </div>
      )
      
  }
</table>