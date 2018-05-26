package scheduler;

/**
 * @author Trim Muharremi
 */
public class Termini {
    private String data;
    private String ora;
    private int pacientiId;

    public Termini(String data, String ora, int pacienti){
        this.data=data;
        this.ora=ora;
        this.pacientiId=pacienti;
    }
    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }

    public int getPacienti() {
        return pacientiId;
    }

}
