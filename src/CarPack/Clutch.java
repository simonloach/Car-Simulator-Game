package CarPack;

class Clutch extends Component {
    private boolean clutchState = false;

    public Clutch(String nazwa, int waga, int cena) {
        super(nazwa, waga, cena);
    }

    public void footDown() {
        clutchState = true;
    }

    public void footUp() {
        clutchState = false;
    }

    public boolean getClutchState() {
        return clutchState;
    }
}
