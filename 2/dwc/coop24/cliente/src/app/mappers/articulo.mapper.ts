import { Injectable } from '@angular/core';
import { Articulo } from '../types/Articulo';
import { getImageUrl } from '../lib/cloudinary';

@Injectable({
  providedIn: 'root',
})
export class ArticuloMapper {
  mapArticle(article: any): Articulo {
    article = {
      id: parseInt(article.id),
      nombre: article.nombre,
      descripcion: article.descripcion,
      precio: parseFloat(article.precio),
      img: getImageUrl(
        article.imagen.replace('.png', '').replace('.jpg', ''),
        200
      ),
      categoria: parseInt(article.categoria),
      vendedor: parseInt(article.vendedor),
      estado: article.estado,
    };

    return article;
  }
}
