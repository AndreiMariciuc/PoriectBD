package curs;

import java.time.LocalDateTime;
import java.util.Date;

public class ActivitateGrupa {
    private int grupa;
    private String disciplina;
    private Date data;
    private int ora;
    private int durata;

    public ActivitateGrupa(int grupa, String disciplina, Date data, int ora, int durata) {
        this.grupa = grupa;
        this.disciplina = disciplina;
        this.data = data;
        this.ora = ora;
        this.durata = durata;
    }

    public int getGrupa() {
        return grupa;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public Date getData() {
        return data;
    }

    public int getOra() {
        return ora;
    }

    public int getDurata() {
        return durata;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}
