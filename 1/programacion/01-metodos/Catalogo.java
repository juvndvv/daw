package practicas;

public class Catalogo {
    public static int buscaIndice(String[] cadenas, String c) {
        int i = 0;
        try {
            while (!c.equalsIgnoreCase(cadenas[i])) i++;
        } catch (Exception e) {
        System.out.println("No se ha encontrado");
            i = -1;
        }
        return i;
    }

    public static int obtieneEntero() {
        int numero;
        try {
            numero = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.print("Por favor, introduce un numero entero: ");
            numero = obtieneEntero();
        }
        return numero;
    }

    public static float obtienePrecio() {
        float precio;
        try {
            precio = Float.parseFloat(System.console().readLine());
        } catch (Exception e) {
            System.out.println("No es un precio valido!");
            precio = obtienePrecio();
        }
        return precio;
    }

    public static void main(String[] args) {
        // Paises
        System.out.print("Introduce el numero de paises: ");
        int numeroPaises = obtieneEntero();
        String[] paises = new String[numeroPaises];

        for (int i = 0; i < numeroPaises; i++) {
            System.out.printf("Introduce el nombre del pais %d: ", i + 1);
            paises[i] = System.console().readLine();
        }

        // Articulos
        System.out.print("Introduce el numero de articulos: ");
        int numeroArticulos = obtieneEntero();
        String[] articulos = new String[numeroArticulos];

        for (int i = 0; i < numeroArticulos; i++) {
            System.out.printf("Introduce el nombre del articulo %d: ", i + 1);
            articulos[i] = System.console().readLine();
        }

        float[][] catalogo = new float[numeroArticulos][numeroPaises];

        // Bucle del menu
        int opcion = 0;
        while (opcion != 9) {

            boolean catalogoInicializado = false;

            int i = 0;
            int j = 0;
            float precioMasAlto;
            float precioMasBajo;
            String articuloBuscado;

            System.out.println();
            System.out.println("1: Llenar datos");
            System.out.println("2: Mostrar");
            System.out.println("3: Modificar un precio Art/pais");
            System.out.println("4: Modificar precio de articulo");
            System.out.println("5: Unificar precios de un articulo");
            System.out.println("6: Dado un  Articulo precio mas bajo y el mas alto en un pais");
            System.out.println("7: Articulo precio mas alto");
            System.out.println("8: Media por articulo");
            System.out.println("9: Salir");

            opcion = obtieneEntero();

            if (!catalogoInicializado && opcion != 1 && opcion != 9) {
                System.out.println("No se han introducido precios en el catalogo");
            }

            switch (opcion) {
                // Llenar datos
                case 1:
                    for (i = 0; i < numeroArticulos; i++){
                        System.out.printf("Introduce precios para el articulo \'%s\' en diferentes paises.%n", articulos[i]);
                        for (j = 0; j < numeroPaises; j++){
                            System.out.printf("%s: ", paises[j]);
                            catalogo[i][j] = obtienePrecio();
                        }
                    }
                    catalogoInicializado = true;
                    break;
                
                // Mostrar catalogo
                case 2:
                    for (i = 0; i < numeroArticulos; i++){
                        for (j = 0; j < numeroPaises; j++){
                            System.out.printf("Precio de \'%s\' en %s: %.2feur%n", articulos[i], paises[j], catalogo[i][j]);
                        }
                    }
                    break;

                // Modificar precio de un articulo en un pais
                case 3:
                    i = -1;
                    while (i == -1) {
                        System.out.print("Introduce el articulo buscado: ");
                        articuloBuscado = System.console().readLine();
                        i = buscaIndice(articulos, articuloBuscado);
                    };

                    // buscamos el pais
                    j = -1;
                    while (j == -1) {
                        System.out.print("Introduce el pais: ");
                        String paisBuscado = System.console().readLine();
                        j = buscaIndice(paises, paisBuscado);
                    }

                    // Cambiamos el precio
                    System.out.printf("Precio actual del articulo \'%s\' en %s: %.2feur%n", articulos[i], paises[j], catalogo[i][j]);
                    System.out.print("Introduce el precio nuevo: ");
                    catalogo[i][j] = obtienePrecio();
                    System.out.println("Precio modificado");
                    break;
                
                // Modificar el precio de un articulo en todos los paises
                case 4:
                    i = -1;
                    while (i == -1) {
                        System.out.print("Introduce el articulo buscado: ");
                        articuloBuscado = System.console().readLine();
                        i = buscaIndice(articulos, articuloBuscado);
                    };

                    for (j = 0; j < numeroPaises; j++) {
                        System.out.printf("Introduce el nuevo precio de \'%s\' en %s: ", articulos[i], paises[j]);
                        catalogo[i][j] = obtienePrecio();
                    }
                    break;

                // Unificar precios de articulos
                case 5:
                    i = -1;
                    while (i == -1) {
                        System.out.print("Introduce el articulo buscado: ");
                        articuloBuscado = System.console().readLine();
                        i = buscaIndice(articulos, articuloBuscado);
                    };

                    System.out.printf("Introduce el precio unificado para el articulo '%s': ", articulos[i]);
                    float precioUnificado = obtienePrecio();

                    for (j = 0; j < numeroPaises; j++){
                        catalogo[i][j] = precioUnificado;
                    }
                    break;

                // Buscar precio mas alto y mas bajo de un articulo 
                case 6:
                    i = -1;
                    while (i == -1) {
                        System.out.print("Introduce el articulo buscado: ");
                        articuloBuscado = System.console().readLine();
                        i = buscaIndice(articulos, articuloBuscado);
                    };

                    precioMasAlto = Float.MIN_VALUE;
                    precioMasBajo = Float.MAX_VALUE;

                    for (j = 0; j < numeroPaises; j++) {
                        if (catalogo[i][j] > precioMasAlto) precioMasAlto = catalogo[i][j];
                        if (catalogo[i][j] < precioMasBajo) precioMasBajo = catalogo[i][j];
                    }

                    System.out.printf("El precio mas alto de '%s': %.2feur%n", articulos[i], precioMasAlto);
                    System.out.printf("El precio mas bajo de '%s': %.2feur%n", articulos[i], precioMasBajo);
                    break;

                // Articulo con el precio mas alto
                case 7:
                    int indiceArticuloMasCaro = 0;
                    int indicePaisConArticuloMasCaro = 0;
                    precioMasAlto = Float.MIN_VALUE;
                    for (i = 0; i < numeroArticulos; i++) {
                        for (j = 0; j < numeroPaises; j++) {
                            if (catalogo[i][j] > precioMasAlto) {
                                indiceArticuloMasCaro = i;
                                indicePaisConArticuloMasCaro = j;
                                precioMasAlto = catalogo[i][j];
                            }
                        }
                    }

                    System.out.printf("El articulo con el precio mas alto es: %s%n", articulos[indiceArticuloMasCaro]);
                    System.out.printf("Su precio es de %.2feur en %s%n", catalogo[indiceArticuloMasCaro][indicePaisConArticuloMasCaro], paises[indicePaisConArticuloMasCaro]);
                    break;

                // Media de precios por articulo
                case 8:
                    for (i = 0; i < numeroArticulos; i++) {
                        float media = 0;
                        for (j = 0; j < numeroPaises; j++) {
                            media += catalogo[i][j];
                        }
                        media = media / numeroPaises;
                        System.out.printf("El precio medio de '%s' es: %.2feur%n", articulos[i], media);
                    }
                    break;
                case 9:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opcion incorrecta!");
            }
        }
    }
}
