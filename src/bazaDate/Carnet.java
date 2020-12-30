package bazaDate;

import java.sql.Date;

public class Carnet {
    private String disciplina, activitate, numeProfesor, nota;
    private Date data;
    private int procent;

    public int getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getActivitate() {
        return activitate;
    }

    public void setActivitate(String activitate) {
        this.activitate = activitate;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Carnet{" +
                "disciplina='" + disciplina + '\'' +
                ", activitate='" + activitate + '\'' +
                ", numeProfesor='" + numeProfesor + '\'' +
                ", nota='" + nota + '\'' +
                ", data=" + data +
                ", procent=" + procent +
                '}';
    }
}
