let $maxCalorias := max(for $component in /Cocktails/Cocktail/Component  let $ingredient := /Ingredients/Ingredient[@id=$component/@id] return $component/Quantity * $ingredient/Calories)
for $component in /Cocktails/Cocktail/Component
  let $cantidad := data($component/Quantity)
  let $calorias := data(/Ingredients/Ingredient[@id = $component/@id]/Calories)
  let $caloriasAportadas := $calorias * $cantidad
  where $caloriasAportadas = $maxCalorias
  return
  (
    <p>{concat('cocktail: ', $component/../Name, ', precio: ', $component/../Price, ', ingrediente: ', //Ingredient[@id=$component/@id]/Name, ', calorias_aportadas: ', $caloriasAportadas)}</p> 
  )