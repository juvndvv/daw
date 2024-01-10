document.getElementById("parques").innerHTML += '<li id="tablas">Tablas </li>'

document.getElementById("tablas").textContent += 'de Daimiel';

document.querySelector(".titulo").setAttribute('id', 'titulo');

const nodoH1 = document.getElementById("titulo");
console.log(nodoH1.classList.length);
nodoH1.classList.add("titulo-parque");
console.log(nodoH1.classList.length);
console.log(nodoH1.classList);
nodoH1.classList.remove("titulo")
console.log(nodoH1.classList.length);

let nodoLi = document.createElement("li");
nodoLi.id = "nevada";
nodoLi.textContent = "Sierra Nevada";
document.getElementById("parques").appendChild(nodoLi);

nodoLi = document.createElement("li");
nodoLi.id = "ordesa";
nodoLi.appendChild(document.createTextNode("Ordesa y Monte Perdido"));
document.getElementById("parques").appendChild(nodoLi);

nodoLi = document.createElement("li")
nodoLi.id = "cabañeros"
nodoLi.appendChild(document.createTextNode("Cabañeros"))
document.getElementById("parques").insertBefore(nodoLi, document.getElementById("doñana"))

document.getElementById("parques").insertAdjacentHTML("afterbegin", "<li id=monfrague>Monfragüe</li>");
nodoLi = document.createElement("li");
nodoLi.id = "picos";
nodoLi.textContent = "Picos de Europa"
document.getElementById("parques").insertAdjacentElement("afterbegin", nodoLi);

nodoLi = document.createElement("li");
nodoLi.id = "Cabrera";
nodoLi.appendChild(document.createTextNode("Archipiélago de Cabrera"));
document.getElementById("parques").replaceChild(nodoLi, document.getElementById("teide"));

document.getElementById("parques").removeChild(document.getElementById("cabañeros"));

document.getElementById("doñana").parentNode.removeChild(document.getElementById("doñana"));