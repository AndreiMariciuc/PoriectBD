package bazaDate;

public class Ore {
    private int ora, nrStudentiOcupati;

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getNrStudentiOcupati() {
        return nrStudentiOcupati;
    }

    public void setNrStudentiOcupati(int nrStudentiOcupati) {
        this.nrStudentiOcupati = nrStudentiOcupati;
    }

    @Override
    public String toString() {
        return new String("La ora " + ora + ", sunt " + nrStudentiOcupati + " studenti ocupati.");
    }
}
