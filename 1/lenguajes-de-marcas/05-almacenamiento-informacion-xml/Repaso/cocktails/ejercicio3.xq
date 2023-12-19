<html>
<head></head>
<body>
  {
    for $cocktail in /Cocktails/Cocktail
      return
      (
        <div>
          <h3>{concat(data($cocktail/Name), ' ', data($cocktail/Price), ' euros')}</h3>
            {
              for $ingredient in $cocktail/Component
              let $cantidad := data($ingredient/Quantity)
              let $calorias := data(//Ingredient[@id=$ingredient/@id]/Calories)
              return
              (
                <p>
                  {concat('Nombre: ', data(//Ingredient[@id=$ingredient/@id]/Name), ' Calorias que aporta: ', $cantidad*$calorias)}
                </p>
              )             
            }
        </div>
      )
      
  }
</body>
</html>