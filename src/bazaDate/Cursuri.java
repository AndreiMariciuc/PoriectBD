package bazaDate;

public class Cursuri {
    private int idCurs;
    private String denumireCurs, descriereCurs;

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
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

    @Override
    public String toString() {
        return new String(idCurs + ". " + denumireCurs + ": " + descriereCurs);
    }
}
