
public class Bombilla {

    private boolean estado;

    public Bombilla() {
        this.estado = false;
    }

    public boolean estaEncendida() {
        return estado;
    }

    public void encender() {
        estado = true;
    }

    public void apagar() {
        estado = false;
    }

    public static void main(String[] args) {
        Bombilla bombilla = new Bombilla();
        System.out.println("La bombilla está encendida: " + bombilla.estaEncendida());
        bombilla.encender();
        System.out.println("La bombilla está encendida: " + bombilla.estaEncendida());
        bombilla.apagar();
        System.out.println("La bombilla está encendida: " + bombilla.estaEncendida());
    }
}