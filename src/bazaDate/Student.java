package bazaDate;

public class Student extends User{
    private int idStatus, nrOre, anStudiu;
    public Student(int idUser, int idRol, String CNP, String nume, String prenume, String adresa, String nrTelefon, String email, String IBAN, int nrContract, String username, String parola, int idStatus, int nrOre, int anStudiu) {
        super(idUser, idRol, CNP, nume, prenume, adresa, nrTelefon, email, IBAN, nrContract, username, parola);
        this.idStatus = idStatus;
        this.nrOre = nrOre;
        this.anStudiu = anStudiu;
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
    public int getAnStudiu() {
        return anStudiu;
    }
    public void setAnStudiu(int anStudiu) {
        this.anStudiu = anStudiu;
    }
}