package bazaDate;

public class Status {
    private int idStatus;
    private String descriereStatus;

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescriereStatus() {
        return descriereStatus;
    }

    public void setDescriereStatus(String descriereStatus) {
        this.descriereStatus = descriereStatus;
    }

    @Override
    public String toString() {
        return descriereStatus;
    }
}
