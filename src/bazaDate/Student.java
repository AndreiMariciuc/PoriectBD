package bazaDate;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    private int idStatus, nrOre, anStudiu;
    private int idProfTitular;
    private ArrayList<String> denumireCursuri;
    private HashMap<String, Integer> activitatiCurente;

    public Student() {
        denumireCursuri = new ArrayList<>();
        activitatiCurente = new HashMap<>();
    }

    public void clearMap() {
        activitatiCurente = new HashMap<>();
    }

    public void addToMap(String k, int v) {
        activitatiCurente.put(k, v);
    }

    public HashMap<String, Integer> getActivitatiCurente() {
        return activitatiCurente;
    }

    public int getIdProfTitular() {
        return idProfTitular;
    }

    public void setIdProfTitular(int idProfTitular) {
        this.idProfTitular = idProfTitular;
    }

    public ArrayList<String> getDenumireCursuri() {
        return denumireCursuri;
    }

    public void setDenumireCursuri(ArrayList<String> denumireCursuri) {
        this.denumireCursuri = denumireCursuri;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getNrOre() {
        return nrOre;
    }

    public void setNrOre(int nrOre) {
        this.nrOre = nrOre;
    }

    public String getAnStudiu() {
        return anStudiu + "";
    }

    public void setAnStudiu(int anStudiu) {
        this.anStudiu = anStudiu;
    }
}
