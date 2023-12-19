<html>
<head></head>
<body>
  <table>
    <tr>
      <th>Ingrediente</th>
      <th>Usos</th>
    </tr>
    {
      for $ingredient in /Ingredients/Ingredient/@id
        let $usos := count(/Cocktails/Cocktail/Component[@id=$ingredient])
        order by xs:integer($usos)
        return
        (
          <tr>
            <td>{data(/Ingredients/Ingredient[@id=$ingredient]/Name)}</td>
            <td>{$usos}</td>
          </tr>
        )
    }
  </table>
</body>
</html>