const MONTHS = ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre'];

const date = new Date();
const indexOfMonth = date.getMonth();

const dateIndicator = document.querySelector('footer>div>p:last-child');
dateIndicator.textContent = `${date.getDate()} de ${MONTHS[indexOfMonth]} de ${date.getFullYear()}`;
