package bazaDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student extends User {
    private int idStatus, nrOre, anStudiu;
    private int idProfTitular;
    private ArrayList<String> denumireCursuri;
    private HashMap<String, Integer> activitatiCurente;
    private List<Calendar> toateActivitati;
    private List<Calendar> aziActivitati;
    private List<Carnet> notePartiale;
    private List<Carnet> noteFinale;


    public Student() {
        denumireCursuri = new ArrayList<>();
        activitatiCurente = new HashMap<>();
    }

    public List<Calendar> getToateActivitati() {
        return toateActivitati;
    }

    public void setToateActivitati(List<Calendar> toateActivitati) {
        this.toateActivitati = toateActivitati;
    }

    public List<Calendar> getAziActivitati() {
        return aziActivitati;
    }

    public void setAziActivitati(List<Calendar> aziActivitati) {
        this.aziActivitati = aziActivitati;
    }

    public List<Carnet> getNotePartiale() {
        return notePartiale;
    }

    public void setNotePartiale(List<Carnet> notePartiale) {
        this.notePartiale = notePartiale;
    }

    public List<Carnet> getNoteFinale() {
        return noteFinale;
    }

    public void setNoteFinale(List<Carnet> noteFinale) {
        this.noteFinale = noteFinale;
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
