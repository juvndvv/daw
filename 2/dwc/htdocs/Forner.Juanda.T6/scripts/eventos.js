document.addEventListener('click', () => {
    console.log("Gestor del documento");
})

document.getElementById('parrafo').addEventListener('click', () => {
    console.log("Gestor del párrafo");
})

document.getElementById('propagacion').addEventListener('click', (e) => {
    console.log("Gestor del botón");
    e.stopPropagation();
})

document.getElementById('masinfo').addEventListener('click', (e) => {
    console.log("Al asociar el manejador de evento el enlace no funciona");
    e.preventDefault();
})

window.addEventListener('load', () => {
    console.log("Pagina totalmente cargada");
})

