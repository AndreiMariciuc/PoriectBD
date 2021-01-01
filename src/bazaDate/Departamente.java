package bazaDate;

public class Departamente {
    private int id;
    private String cod, descriere;
    Departamente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String toString() {
        return new String(id + " " + cod + ": " + descriere);
    }
}
