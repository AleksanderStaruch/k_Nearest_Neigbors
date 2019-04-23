public class Len {
    private String name;
    private double l;

    public Len(String name, double l) {
        this.name = name;
        this.l = l;
    }

    public double getL() {
        return l;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name+" "+l;
    }
}
