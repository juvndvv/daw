class ClaseA {
    private ClaseB claseB;

    public ClaseA() {
        claseB = new ClaseB();
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
