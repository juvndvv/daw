<html>
<head></head>
<body>
  <table>
    <tr>
      <th>Calorias totales</th>
      <th>Precio</th>
    </tr>
    {
      for $cocktail in /Cocktails/Cocktail
        let $calorias := sum(for $component in $cocktail/Component let $ingredient := /Ingredients/Ingredient[@id=$component/@id] return $component/Quantity * $ingredient/Calories)
        let $precio := data($cocktail/Price)
        order by $precio
        return
        (
          <tr>
            <td>{$calorias}</td>
            <td>{$precio}</td>
          </tr>
        )
    }
  </table>
</body>
</html>