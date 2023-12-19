public class Punto {

    private int x;
    private int y;

    /* Constructores */
    public Punto() {
        this.x = 0;
        this.y = 0;
    }

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* Getters y setters */
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* Metodos publicos */
    public void imprime() {
        System.out.printf("(%d, %d%n)", this.x, this.y);
    }

    public void desplaza(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int distancia(Punto other) {
        return (int) Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }
}
