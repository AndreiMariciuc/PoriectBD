package bazaDate;

public class User {
    private int idUser;
    private int idRol;
    private String CNP;
    private String nume;
    private String prenume;
    private String adresa;
    private String nrTelefon;
    private String email;
    private String IBAN;
    private int nrContract;
    private String username;
    private String parola;

    public User() {

    }

    public User(int idUser, int idRol, String CNP, String nume, String prenume, String adresa, String nrTelefon, String email, String IBAN, int nrContract, String username, String parola) {
        this.idUser = idUser;
        this.idRol = idRol;
        this.CNP = CNP;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        this.email = email;
        this.IBAN = IBAN;
        this.nrContract = nrContract;
        this.username = username;
        this.parola = parola;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public int getNrContract() {
        return nrContract;
    }

    public void setNrContract(int nrContract) {
        this.nrContract = nrContract;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        String result = new String(idUser + " "  + idRol + " " + CNP + " " + nume + " " + prenume + " '" + adresa + "' " +
                nrTelefon + " " + email + " " + IBAN + " " + nrContract + " " + username + " " + parola + "\n");
        return result;
    }
}
