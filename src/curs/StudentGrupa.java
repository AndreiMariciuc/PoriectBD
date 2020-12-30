package curs;

public class StudentGrupa {
    private String nume;
    private String adresa;
    private String email;
    private String nrTel;
    private String admin;

    public StudentGrupa(String nume, String adresa, String email, String nrTel, String admin) {
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
        this.nrTel = nrTel;
        this.admin = admin;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
