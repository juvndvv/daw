package CuadradoPuntos;
public class CuadradoPuntos {
    /* Atributos */
    private float x1, y1, x2, y2;
    private int color;

    /* Constructores */
    public CuadradoPuntos() {
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
        this.color = 0;
    }

    public CuadradoPuntos(float x1, float y1, float x2, float y2, int color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public CuadradoPuntos(CuadradoPuntos o) {
        this.x1 = o.getX1();
        this.y1 = o.getY1();
        this.x2 = o.getX2();
        this.y2 = o.getY2();
        this.color = o.getColor();
    } 

    /* Métodos */
    public float calcularDiagonal() {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public float calcularPerimetro() {
        float diagonal = calcularDiagonal();
        float lado = (float) (diagonal / Math.sqrt(2));
        return (4 * lado);
    }

    public float calcularArea() {
        double diagonal = calcularDiagonal();
        return (float) (0.5 * diagonal * diagonal);
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void modificaValores(float x1_p, float y1_p, float x2_p, float y2_p, int color_p) {
        x1 = x1_p;
        y1 = y1_p;
        x2 = x2_p;
        y2 = y2_p;
        color = color_p;
    }
}
