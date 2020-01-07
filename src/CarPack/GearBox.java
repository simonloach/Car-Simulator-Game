package CarPack;

class GearBox extends Component {
    private int currentGear = 1;
    private double currentRatio = 0.005;

    private Clutch clutch;
    private int numberOfGears;

    public GearBox(String nazwa, int waga, int cena, int iloscBiegow, Clutch clutch) {
        super(nazwa, waga+clutch.getWaga(), cena+clutch.getCena());
        this.numberOfGears = iloscBiegow;
        this.clutch = clutch;
    }

    public void gearUp() {
        if (currentGear < numberOfGears) {
            clutch.footDown();
            currentGear += 1;
            currentRatio += 0.02;
            clutch.footUp();
        }
    }

    public void gearDown() {
        if (currentGear > 1) {
            clutch.footDown();
            currentGear -= 1;
            currentRatio -= 0.02;
            clutch.footUp();
        }
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(double currentRatio) {
        this.currentRatio = currentRatio;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

}
