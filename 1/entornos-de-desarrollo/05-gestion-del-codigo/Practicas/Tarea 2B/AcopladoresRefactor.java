/*
 * En el siguiente codigo se ha utilizado el siguiente metodo de refactorizacion:
 * 
 *      1. Inyeccion de dependencias: En este caso, la clase ClaseA depende de una instancia
 *      de ClaseB que se le pasa en el constructor, en lugar de crear una instancia dentro
 *      de su propio constructor. Esto reduce el acoplamiento y hace que ClaseA sea más
 *      flexible y fácil de probar y mantener. Además, si se desea utilizar una implementación
 *      diferente de ClaseB, solo se necesita cambiar la instancia que se pasa a ClaseA.
 * 
 * TF. Manejar las generalizaciones
 */

class ClaseA {
    private ClaseB claseB;

    public ClaseA(ClaseB claseB) {
        this.claseB = claseB;
    }

    public void hacerAlgo() {
        claseB.metodoB();
    }
}

class ClaseB {
    public void metodoB() {
        // Hacer algo
    }
}
