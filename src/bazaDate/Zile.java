package bazaDate;

public class Zile {
    private int idZi;
    private String zi;

    public int getIdZi() {
        return idZi;
    }

    public void setIdZi(int idZi) {
        this.idZi = idZi;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    @Override
    public String toString() {
        return idZi + ". " + zi;
    }
}
