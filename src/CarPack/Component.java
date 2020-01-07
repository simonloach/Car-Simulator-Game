package CarPack;

class Component {
    private String nazwa;
    private int waga;
    private int cena;

    Component(String nazwa, int waga, int cena) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
    }

    public String getNazwa() {
        return this.nazwa;
    }

    public int getWaga() {
        return this.waga;
    }

    public int getCena() {
        return this.cena;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
