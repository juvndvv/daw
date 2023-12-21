// Obtiene los datos de la API
function fetchMoviesData(page) {
  const URL = `https://api.themoviedb.org/3/movie/popular?api_key=192e0b9821564f26f52949758ea3c473&language=es-ES&page=`;
  return new Promise((resolve, reject) => {
    fetch(`${URL}${page}`)
      .then((response) => response.json())
      .then((data) => {
        const movies = data.results.map((movie) => {
          return {
            title: movie.title,
            poster: movie.poster_path,
            overview: movie.overview,
          };
        });
        resolve(movies);
      })
      .catch((error) => reject(error));
  });
}

// Funciones para crear elementos
function createMovieCard({ title, poster, overview }) {
  // Crea la tarjeta de la pelicula
  const card = document.createElement("article");
  card.classList.add("pelicula");
  card.innerHTML = `
    <div>
      <img src="https://image.tmdb.org/t/p/w500${poster}" alt="${title}">
    </div>
    <h3>${title}</h3>
  `;

  // Muestra los detalles de la pelicula
  card.addEventListener("click", () => {
    const details = createMovieDetails({ title, poster, overview });
    document.body.appendChild(details);
    details.showModal();
  });

  return card;
}

function createMovieDetails({ title, poster, overview }) {
  // Crea el dialogo
  const details = document.createElement("dialog");
  details.innerHTML = `
    <img src="https://image.tmdb.org/t/p/w500${poster}" alt="${title}">
    <button>X</button>
    <div>
      <h3>${title}</h3>
      <p>${overview === "" ? "Descripción no disponible" : overview}</p>
    </div>
  `;

  // Cierra el dialogo
  details.querySelector("button").addEventListener("click", () => {
    details.remove();
  });

  return details;
}

// Renderiza las peliculas en el DOM
function renderMovies(movies) {
  const moviesContainer = document.querySelector(".peliculas-container");

  // Elimina las peliculas anteriores
  moviesContainer
    .querySelectorAll(".pelicula")
    .forEach((pelicula) => pelicula.remove());

  // Renderiza las peliculas
  movies.forEach((movie) => {
    const movieCard = createMovieCard(movie);
    moviesContainer.appendChild(movieCard);
  });
}

// Obtiene los datos de la API y renderiza las peliculas
function fetchMovies() {
  fetchMoviesData(currPage)
    .then((movies) => renderMovies(movies))
    .catch((error) => console.log(error));
}

let currPage = 1;

// Boton de siguiente pagina
document.getElementById("next").addEventListener("click", () => {
  currPage++;
  fetchMovies();
  window.scrollTo(0, 0);
});

// Boton de pagina anterior
document.querySelector("#prev").addEventListener("click", () => {
  if (currPage === 1) return;
  currPage--;
  fetchMovies();
  window.scrollTo(0, 0);
});

// Renderiza las peliculas al cargar la pagina
fetchMovies();
