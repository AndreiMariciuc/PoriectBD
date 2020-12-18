package bazaDate;

public class Departamente {
    private int idDepartament;
    private String codDepartament, descriereDepartament;
    Departamente() {

    }

    public int getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(int idDepartament) {
        this.idDepartament = idDepartament;
    }

    public String getCodDepartament() {
        return codDepartament;
    }

    public void setCodDepartament(String codDepartament) {
        this.codDepartament = codDepartament;
    }

    public String getDescriereDepartament() {
        return descriereDepartament;
    }

    public void setDescriereDepartament(String descriereDepartament) {
        this.descriereDepartament = descriereDepartament;
    }

    public String toString() {
        return new String(idDepartament + " " + codDepartament + ": " + descriereDepartament);
    }
}
