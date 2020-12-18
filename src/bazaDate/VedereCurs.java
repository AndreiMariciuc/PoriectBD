package bazaDate;

public class VedereCurs {
    private int idCursActivitate, nrZiSaptamana, oraInceput, nrMaxParticipanti, idCurs;
    private String denumireCurs, descriereCurs;
    private String numeProf, prenumeProf;

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public int getIdCurs() {
        return this.idCurs;
    }

    public int getIdCursActivitate() {
        return idCursActivitate;
    }

    public void setIdCursActivitate(int idCursActivitate) {
        this.idCursActivitate = idCursActivitate;
    }

    public int getNrZiSaptamana() {
        return nrZiSaptamana;
    }

    public void setNrZiSaptamana(int nrZiSaptamana) {
        this.nrZiSaptamana = nrZiSaptamana;
    }

    public int getOraInceput() {
        return oraInceput;
    }

    public void setOraInceput(int oraInceput) {
        this.oraInceput = oraInceput;
    }

    public int getNrMaxParticipanti() {
        return nrMaxParticipanti;
    }

    public void setNrMaxParticipanti(int nrMaxParticipanti) {
        this.nrMaxParticipanti = nrMaxParticipanti;
    }

    public String getDenumireCurs() {
        return denumireCurs;
    }

    public void setDenumireCurs(String denumireCurs) {
        this.denumireCurs = denumireCurs;
    }

    public String getDescriereCurs() {
        return descriereCurs;
    }

    public void setDescriereCurs(String descriereCurs) {
        this.descriereCurs = descriereCurs;
    }

    public String getNumeProf() {
        return numeProf;
    }

    public void setNumeProf(String numeProf) {
        this.numeProf = numeProf;
    }

    public String getPrenumeProf() {
        return prenumeProf;
    }

    public void setPrenumeProf(String prenumeProf) {
        this.prenumeProf = prenumeProf;
    }

    public String toString() {
        return new String(idCursActivitate + ". " + denumireCurs + ": " + descriereCurs + ", tinut de: " + numeProf + " " + prenumeProf + ", ziua: " + nrZiSaptamana + ", ora de inceput: " + oraInceput + " maxim (" + nrMaxParticipanti + " participanti)");
    }
}
