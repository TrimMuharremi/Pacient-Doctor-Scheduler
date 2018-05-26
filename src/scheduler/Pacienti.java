package scheduler;

/**
 * @author Trim Muharremi
 */
public class Pacienti {
    private int pacientID;
    private String emri;
    private String mbiemri;
    private String adresa;
    private String informata;
    private String datelindja;



    public Pacienti(int pacientID, String emri, String mbiemri, String adresa, String informata, String datelindja){
        this.pacientID = pacientID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.adresa = adresa;
        this.informata = informata;
        this.datelindja = datelindja;
    }


    public int getPacientId(){
        return pacientID;
    }

    public String getAdresa() {
        return adresa;
    }


  public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }


    public String getInformata() {
        return informata;
    }


    public String getDatelindja() {
        return datelindja;
    }
/*

    public void setDatelindja(String datelindja) {
        this.datelindja = datelindja;
    }
*/


}