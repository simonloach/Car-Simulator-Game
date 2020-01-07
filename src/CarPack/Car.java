package CarPack;

class Car extends Thread {
    private boolean isOn = true;
    private int registrationNumber;
    private String model;
    private int maxSpeed;
    private Engine engine;
    private GearBox gearbox;
    private Position currentPos;
    private double wheelDiameter;
    private double x = 0;
    private double y = 0;
    private double V;
    private boolean running = false;

    public boolean isActive() {
        boolean active = false;
        return active;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    Car(int registrationNumber, String model, int maxSpeed, Engine engine, GearBox gearbox, double wheelDiameter) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.engine = engine;
        this.gearbox = gearbox;
        this.currentPos = CarUtilities.ZERO_POSITION;
        this.wheelDiameter = wheelDiameter;
    }

    public String toString() {
        return model;
    }

    public void run() {
        try {
            goTo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOn() {
        this.isOn = true;
        gearbox.setCurrentGear(1);
        engine.uruchom();
        gearbox.setCurrentRatio(0.005);
    }

    public void turnOff() {
        this.isOn = false;
        engine.zatrzymaj();
    }

    public void goTo() throws InterruptedException {
        while (true) {
            if (running) {
                double x = this.x;
                double y = this.y;
                double a = currentPos.getX();
                double b = currentPos.getY();
                double aconst = a;
                double bconst = b;

                double ashit = 0;
                double bshit = 0;
                double dist = currentPos.dist(x, y);
                if (dist > 0) {
                    ashit = ((x - a) / dist);
                    bshit = ((y - b) / dist);
                }

                if (!isOn) {
                    turnOn();
                }
                while (running) {

                    this.V = engine.getObroty() * gearbox.getCurrentRatio() * wheelDiameter; //obroty*przełożenie*obwódkoła
                    if (V > maxSpeed) {
                        V = maxSpeed;
                    }


                    if (currentPos.dist(aconst, bconst) > dist) {
                        turnOff();
                        running = false;
                    }
                    a += this.V * 0.02 * ashit;
                    b += this.V * 0.02 * bshit;

                    if (this.V > 0) {
                        this.currentPos.setX(a);
                        this.currentPos.setY(b);
                    }

                    //System.out.println(Double.toString(a) + " | " + Double.toString(b) + " || " + Double.toString(V) + " ||| " + Integer.toString(nrRejest));
                    Thread.sleep(510);
                }
            }
            Thread.sleep(500);
        }
    }

    public void gearUp() {
        if (engine.getObroty() > 2000) {
            gearbox.gearUp();
            engine.decreaseRpm(1000);
        }
    }

    public void gearDown() {
        if (gearbox.getCurrentGear() > 1) {
            gearbox.gearDown();
            engine.increaseRpm(1000);
        }
    }

    public void RPMup() {
        engine.increaseRpm(300);
    }

    public void RPMdown() {
        engine.decreaseRpm(300);
    }

    public void setDest(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getSilnikNazw() {
        return engine.getNazwa();
    }

    public String getSkrzNazw() {
        return gearbox.getNazwa();
    }

    public double getCurrentRatio() {
        return gearbox.getCurrentRatio();
    }

    public int getCurrentGear() {
        return gearbox.getCurrentGear();
    }

    public double getCurrentRPM() {
        return engine.getObroty();
    }
    public double getV() {
        return V;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getDest() {
        return this.x + " | " + this.y;
    }
    public Position getCurrentPosition() {
        return currentPos;
    }
    public int getWaga() {
        return engine.getWaga() + gearbox.getWaga();
    }
}

