package curs;

public class Curs {
    private String nume;
    private String activitate;
    private String perioada;
    private String zi_a_saptamanii;
    private int procent;
    private int ora_inceput;
    private int durata;

    public Curs(String nume, String activitate, String perioada, String zi_a_saptamanii, int procent, int ora_inceput, int durata) {
        this.nume = nume;
        this.activitate = activitate;
        this.perioada = perioada;
        this.zi_a_saptamanii = zi_a_saptamanii;
        this.procent = procent;
        this.ora_inceput = ora_inceput;
        this.durata = durata;
    }

    public String getNume() {
        return nume;
    }

    public String getActivitate() {
        return activitate;
    }

    public String getPerioada() {
        return perioada;
    }

    public String getZi_a_saptamanii() {
        return zi_a_saptamanii;
    }

    public int getProcent() {
        return procent;
    }

    public int getOra_inceput() {
        return ora_inceput;
    }

    public int getDurata() {
        return durata;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setActivitate(String activitate) {
        this.activitate = activitate;
    }

    public void setPerioada(String perioada) {
        this.perioada = perioada;
    }

    public void setZi_a_saptamanii(String zi_a_saptamanii) {
        this.zi_a_saptamanii = zi_a_saptamanii;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public void setOra_inceput(int ora_inceput) {
        this.ora_inceput = ora_inceput;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}
