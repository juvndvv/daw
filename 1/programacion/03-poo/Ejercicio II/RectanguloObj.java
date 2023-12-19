public class RectanguloObj {
    
    private int base;
    private int altura;

    public RectanguloObj(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void dibujar() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < base; j++) {
                if (i == 0 || i == altura - 1) {
                    System.out.print("X");
                } else {
                    if (j == 0 || j == base - 1) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        RectanguloObj rectangulo = new RectanguloObj(10, 7);
        rectangulo.dibujar();
    }
}
