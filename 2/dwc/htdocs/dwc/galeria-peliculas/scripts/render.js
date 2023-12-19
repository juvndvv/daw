import { createMovieCard } from './dom.js';

export function renderMovies(movies) {
  const moviesContainer = document.querySelector('.peliculas-container');
  
  moviesContainer.querySelectorAll('.pelicula').forEach(pelicula => pelicula.remove());

  movies.forEach(movie => {
    const movieCard = createMovieCard(movie);
    moviesContainer.appendChild(movieCard);
  });
}