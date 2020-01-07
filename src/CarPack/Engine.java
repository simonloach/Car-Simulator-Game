package CarPack;

class Engine extends Component {

    private int maxobroty;
    private int obroty = 0;

    public int getObroty() {
        return obroty;
    }

    Engine(String nazwa, int waga, int cena, int maxobroty) {
        super(nazwa, waga, cena);
        this.maxobroty = maxobroty;
    }

    public void uruchom() {
        this.obroty = 1000;
    }

    public void zatrzymaj() {
        this.obroty = 0;
    }

    public void increaseRpm(int x) {
        if (obroty + x <= maxobroty) {
            this.obroty += x;
        } else {
            this.obroty = maxobroty;
            System.out.println("Stop or you will break the engine, RPM too high!");
        }
    }

    public void decreaseRpm(int x) {
        if (obroty - x > 1000) {
            this.obroty -= x;
        } else {
            this.obroty = 900;
            System.out.println("Stop trying to stall the engine!");
        }
    }
}
