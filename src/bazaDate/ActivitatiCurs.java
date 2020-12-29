package bazaDate;

import java.sql.Time;

public class ActivitatiCurs {
    private int id;
    private int idCurs;
    private int idActivitate;
    private int perioada;
    private int nrZiSaptamana;
    private int procent;
    private int idProfDelegat;
    private int idProfTitular;
    private Time oraInceput;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public int getIdActivitate() {
        return idActivitate;
    }

    public void setIdActivitate(int idActivitate) {
        this.idActivitate = idActivitate;
    }

    public int getPerioada() {
        return perioada;
    }

    public void setPerioada(int perioada) {
        this.perioada = perioada;
    }

    public int getNrZiSaptamana() {
        return nrZiSaptamana;
    }

    public void setNrZiSaptamana(int nrZiSaptamana) {
        this.nrZiSaptamana = nrZiSaptamana;
    }

    public int getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public int getIdProfDelegat() {
        return idProfDelegat;
    }

    public void setIdProfDelegat(int idProfDelegat) {
        this.idProfDelegat = idProfDelegat;
    }

    public int getIdProfTitular() {
        return idProfTitular;
    }

    public void setIdProfTitular(int idProfTitular) {
        this.idProfTitular = idProfTitular;
    }

    public Time getOraInceput() {
        return oraInceput;
    }

    public void setOraInceput(Time oraInceput) {
        this.oraInceput = oraInceput;
    }
}
