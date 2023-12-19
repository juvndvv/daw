public class Rectangulo {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /* Setters y getters */
    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX1(int x1) {
        if (x1 < x2)
            this.x1 = x1;
        else
            System.err.println("ERROR la coordenada x1 no es valida");
    }

    public void setY1(int y1) {
        if (y1 < y2)
            this.y1 = y1;
        else
            System.err.println("ERROR la coordenada y1 no es valida");
    }

    public void setX2(int x2) {
        if (x2 > x1)
            this.x2 = x2;
        else
            System.err.println("ERROR la coordenada x2 no es valida");
    }

    public void setY2(int y2) {
        if (y2 > y1)
            this.y2 = y2;
        else
            System.err.println("ERROR la coordenada y2 no es valida");
    }

    public void setX1Y1(int x1, int y1) {
        this.setX1(x1);
        this.setY1(y1);
    }

    public void setX2Y2(int x1, int y2) {
        this.setX2(x2);
        this.setY2(y2);
    }

    public void setAll(int x1, int y1, int x2, int y2) {
        this.setX1Y1(x1, y1);
        this.setX2Y2(x1, y2);
    }

    /* Metodos publicos */
    public void imprime() {
            System.out.printf("Esquina inferior izquierda: (%d, %d)", this.x1, this.y1);
            System.out.printf("Esquina superior derecha: (%d, %d)", this.x2, this.y2);
    }

    public int getPerimetro() {
        return (this.getBase() + this.getAltura()) * 2;
    }

    public int getArea() {
        return this.getBase() * this.getAltura();
    }

    private int getBase(){
        return this.x2 - this.x1;
    }

    private int getAltura() {
        return this.y2 - this.y1;
    }
}