public enum Color {

    ROJO("Rojo"),
    AMARILLO("Amarillo"),
    VERDE("Verde"),
    AZUL("Azul");

    private String name;

    private Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void mostrarColores() {
        Color[] colores = Color.values();
        for (int i = 0; i < colores.length; i++) {
            System.out.printf("%d. %s%n", i + 1, colores[i].getName());
        }
    }
}
