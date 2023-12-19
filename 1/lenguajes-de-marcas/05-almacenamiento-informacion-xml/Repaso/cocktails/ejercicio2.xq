<html>
<head></head>
<body>
  <h1>Ingredientes no utilizados</h1>
  <table>
    <tr>
      <th>Nombre</th>
    </tr>
    {
        for $ingredient in /Ingredients/Ingredient/@id
          let $usos := count(/Cocktails/Cocktail/Component[@id=$ingredient])
          where xs:integer($usos) = 0
          return
          (
            <tr>
              <td>{data(/Ingredients/Ingredient[@id=$ingredient]/Name)}</td>
            </tr>
          )
      }
  </table>
</body>
</html>