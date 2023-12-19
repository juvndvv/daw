"use strict";

// ✅ async / await
async function getData() {
  try {
    const response = await fetch("./data.json");
    const json = await response.json();
    console.log(json);
  } catch (error) {
    console.log(error);
  }
}

getData();

// ✅ Promise
fetch("https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0")
  .then((response) => response.json())
  .then((json) => console.log(json.results))
  .catch((error) => console.log(error));
