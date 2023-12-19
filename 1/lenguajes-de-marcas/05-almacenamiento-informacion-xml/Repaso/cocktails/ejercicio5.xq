for $component in /Cocktails/Cocktail/Component
where data($component/Quantity) = max(data(/Cocktails/Cocktail/Component/Quantity))
return (
<p>
  {concat('En el cocktail ', $component/../Name, ' con precio ', $component/../Price, ' se ha usado el ingrediente ', data(/Ingredients/Ingredient[@id = $component/@id]/Name), ' ', data($component/Quantity), ' veces')}
</p>
)