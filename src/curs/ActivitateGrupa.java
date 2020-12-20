package curs;

import java.time.LocalDateTime;
import java.util.Date;

public class ActivitateGrupa {
    private int grupa;
    private int nrMinParticipanti;
    private int nrCurentParticipanti;
    private Date data;
    private int ora;
    private int durata;
    private LocalDateTime startProgramare;
    private LocalDateTime deadline;

    public ActivitateGrupa(int grupa, int nrMinParticipanti, int nrCurentParticipanti, Date data, int ora, int durata, LocalDateTime startProgramare, LocalDateTime deadline) {
        this.grupa = grupa;
        this.nrMinParticipanti = nrMinParticipanti;
        this.nrCurentParticipanti = nrCurentParticipanti;
        this.data = data;
        this.ora = ora;
        this.durata = durata;
        this.startProgramare = startProgramare;
        this.deadline = deadline;
    }

    public int getGrupa() {
        return grupa;
    }

    public int getNrMinParticipanti() {
        return nrMinParticipanti;
    }

    public int getNrCurentParticipanti() {
        return nrCurentParticipanti;
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

    public LocalDateTime getStartProgramare() {
        return startProgramare;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public void setNrMinParticipanti(int nrMinParticipanti) {
        this.nrMinParticipanti = nrMinParticipanti;
    }

    public void setNrCurentParticipanti(int nrCurentParticipanti) {
        this.nrCurentParticipanti = nrCurentParticipanti;
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

    public void setStartProgramare(LocalDateTime startProgramare) {
        this.startProgramare = startProgramare;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
