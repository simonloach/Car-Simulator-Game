package CarPack;

class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double dist(double x, double y){
        double odl = Math.sqrt((this.x-x)*(this.x-x) + (this.y-y)*(this.y-y));

        return odl;
    }
}
