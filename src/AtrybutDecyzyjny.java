public class AtrybutDecyzyjny {
    private String name;
    private int ilosc;

    public AtrybutDecyzyjny(String name) {
            this.name = name;
            this.ilosc = 0;
    }

    public String toString() {
        return name +" "+ilosc;
    }


    public String getName() {
        return name;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
