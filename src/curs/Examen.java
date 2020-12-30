package curs;

public class Examen {
    private String curs;
    private String tip;
    private String data;
    private int durata;

    public Examen(String curs, String tip, String data, int durata) {
        this.curs = curs;
        this.tip = tip;
        this.data = data;
        this.durata = durata;
    }

    public String getCurs() {
        return curs;
    }

    public void setCurs(String curs) {
        this.curs = curs;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}
