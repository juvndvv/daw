public class LibroObj {
    
    private String titulo;
    private String autor;
    private int año;
    private String editorial;

    public LibroObj(String titulo, String autor, int año, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAño() {
        return año;
    }

    public String getEditorial() {
        return editorial;
    }

    public String toString() {
        return String.format(
            "Titulo: %s%n" +
            "Autor: %s%n" +
            "Año de publicación: %d%n" +
            "Editorial: %s%n",
            this.titulo, this.autor, this.año, this.editorial
        );
    }

    public static void main(String[] args) {
        LibroObj libro1 = new LibroObj("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "Imprenta de Juan de la Cuesta");
        LibroObj libro2 = new LibroObj("El señor de las moscas", "William Golding", 1954, "Faber and Faber");

        System.out.println(libro1);
        System.out.println(libro2);
    }
}
