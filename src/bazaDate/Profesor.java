package bazaDate;
public class Profesor extends User{
    private int nrMinOre;
    private int nrMaxOre;
    private int idDepartament;
    public Profesor(int idUser, int idRol, String CNP, String nume, String prenume, String adresa, String nrTelefon, String email, String IBAN, int nrContract, String username, String parola, int nrMinOre, int nrMaxOre, int idDepartament) {
        super(idUser, idRol, CNP, nume, prenume, adresa, nrTelefon, email, IBAN, nrContract, username, parola);
        this.nrMaxOre = nrMaxOre;
        this.nrMinOre = nrMinOre;
        this.idDepartament = idDepartament;
    }
    public int getNrMinOre() {
        return nrMinOre;
    }
    public int getNrMaxOre() {
        return nrMaxOre;
    }
    public int getIdDepartament() {
        return idDepartament;
    }
    public void setNrMinOre(int nrMinOre) {
        this.nrMinOre = nrMinOre;
    }
    public void setNrMaxOre(int nrMaxOre) {
        this.nrMaxOre = nrMaxOre;
    }
    public void setIdDepartament(int idDepartament) {
        this.idDepartament = idDepartament;
    }
}
