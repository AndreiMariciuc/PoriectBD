package bazaDate;


import java.sql.Time;
import java.sql.Timestamp;

public class Mesaj {
    private int idGrupa, programare;
    private String numeFrom, prenumeFrom, subiect, continutMesaj;
    private Timestamp timpPrimire; // timestamp

    public String getPrenumeFrom() {
        return prenumeFrom;
    }

    public void setPrenumeFrom(String prenumeFrom) {
        this.prenumeFrom = prenumeFrom;
    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public int getProgramare() {
        return programare;
    }

    public void setProgramare(int programare) {
        this.programare = programare;
    }

    public String getNumeFrom() {
        return numeFrom;
    }

    public void setNumeFrom(String numeFrom) {
        this.numeFrom = numeFrom;
    }

    public String getSubiect() {
        return subiect;
    }

    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public String getContinutMesaj() {
        return continutMesaj;
    }

    public void setContinutMesaj(String continutMesaj) {
        this.continutMesaj = continutMesaj;
    }

    public Timestamp getTimpPrimire() {
        return timpPrimire;
    }

    public void setTimpPrimire(Timestamp timpPrimire) {
        this.timpPrimire = timpPrimire;
    }

    @Override
    public String toString() {
        String result = new String("");
        result += "From: " + numeFrom + " " + prenumeFrom + "\t" + timpPrimire + "\n" + "Subiect: " + getSubstring(subiect, 30, "...", 0) + "\n" + getSubstring(continutMesaj, 50, "...", 0);
        return result;
    }

    private String getSubstring(String input, int nChars, String sep, int start) {
        String result = "";
        char[] temp = input.toCharArray();
        for (int i = start; i < input.length(); i++) {
            result += temp[i];
            if (i >= start + nChars) {
                break;
            }
        }
        return result + sep;
    }
    public String getWholeMsg() {
        String result = new String("");
        result += "From: " + numeFrom + " " + prenumeFrom + "\t" + timpPrimire + "\n" + "Subiect: ";
        for (int i = 0; i < subiect.length(); i += 70) {
            result += getSubstring(subiect, 70, "", i) + "\n";
        }
        result += "Continut: ";
        for (int i = 0; i < continutMesaj.length(); i += 70) {
            result += getSubstring(continutMesaj, 70, "", i) + "\n";
        }
        return result;
    }
}