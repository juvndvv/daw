export function createMovieCard(movie) {
  const card = document.createElement("article");
  card.classList.add("pelicula");
  card.innerHTML = `
    <div>
      <img src="https://image.tmdb.org/t/p/w500${movie.poster}" alt="${movie.title}">
    </div>
    <h3>${movie.title}</h3>
  `;

  card.addEventListener("click", () => {
    console.log(movie);
    const details = createMovieDetails(movie);
    document.body.appendChild(details);
    details.showModal();
  });

  return card;
}

export function createMovieDetails(movie) {
  const details = document.createElement("dialog");
  details.innerHTML = `
    <img src="https://image.tmdb.org/t/p/w500${movie.poster}" alt="${
    movie.title
  }">
  <button>X</button>
  <div>
    <h3>${movie.title}</h3>
      <p>${
        movie.overview === "" ? "Descripción no disponible" : movie.overview
      }</p>
    </div>
  `;

  details.querySelector("button").addEventListener("click", () => {
    details.remove();
  });

  return details;
}
