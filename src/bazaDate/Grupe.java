package bazaDate;

public class Grupe {
    private int idGrupa;
    private String numeAdmin, prenumeAdmin, denumireMaterie, descriereMaterie;
    private String numeProfesor, prenumeProfesor;

    public int getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public String getNumeAdmin() {
        return numeAdmin;
    }

    public void setNumeAdmin(String numeAdmin) {
        this.numeAdmin = numeAdmin;
    }

    public String getPrenumeAdmin() {
        return prenumeAdmin;
    }

    public void setPrenumeAdmin(String prenumeAdmin) {
        this.prenumeAdmin = prenumeAdmin;
    }

    public String getDenumireMaterie() {
        return denumireMaterie;
    }

    public void setDenumireMaterie(String denumireMaterie) {
        this.denumireMaterie = denumireMaterie;
    }

    public String getDescriereMaterie() {
        return descriereMaterie;
    }

    public void setDescriereMaterie(String descriereMaterie) {
        this.descriereMaterie = descriereMaterie;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public String getPrenumeProfesor() {
        return prenumeProfesor;
    }

    public void setPrenumeProfesor(String prenumeProfesor) {
        this.prenumeProfesor = prenumeProfesor;
    }

    @Override
    public String toString() {
        String result = "";
        result += idGrupa + ". Student responzabil: " + numeAdmin + " " + prenumeAdmin + ", materia: (" + denumireMaterie + ") " + descriereMaterie;
        if (numeProfesor != null && prenumeProfesor != null) {
            result += ". Prof. responsabil: " + numeProfesor + " " + prenumeProfesor;
        }
        return result;
    }
}
