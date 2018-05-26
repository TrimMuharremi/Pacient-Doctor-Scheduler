package scheduler;

import java.util.ArrayList;


public class Databaza {

    /**
     * @author Trim Muharremi
     *
     * Kjo klase eshte simulim i nje baze te te dhenave(access, sql, mysql)
     * Ne kete aplikacion ruhen kryesisht dy lloje te te dhenave, termine dhe paciente.
     * Ruajtja e pacienteve dhe termineve behet ne Listat e tipave Pacienti dhe Termini, perkatesisht.
     */

    private static ArrayList<Pacienti> pacienti = new ArrayList<Pacienti>();
      private static ArrayList<Termini> termini = new ArrayList<Termini>();


    /**
     * Shton nje objekt Pacienti ne listen @pacienti
     * @param emri emri i pacientit
     * @param mbiemri mbiemri i pacientit
     * @param adresa adresa e pacientit
     * @param informata informata lidhur me pacientin
     * @param datelindja datelindja e pacientit
     */
    public void shtoPacient(String emri, String mbiemri, String adresa, String informata,String datelindja){

            int i = pacienti.size();
        if(i==0){ pacienti.add(new Pacienti(i + 1, emri, mbiemri, adresa, informata, datelindja));}
        else {
            i = pacienti.get(i-1).getPacientId();
            pacienti.add(new Pacienti(i + 1, emri, mbiemri, adresa, informata, datelindja));
        }
    }

    /**
     * Kthen Listen me pacientet e ruajtur ne te
     * @return Lista me Pacientet e regjistruar
     */
    public ArrayList<Pacienti> getPacientet(){
        return pacienti;
    }

    /**
     *
     * @return integjer IDPacienti i ardhshem
     */
    public int nextPatientId(){
        return pacienti.size();
    }

    /**
     * Fshirja e pacienteve ne liste
     * @param i elementi i i-te ne listen pacienti
     */
    public void deletePacient(int i){
        pacienti.remove(i);
    }

    /**
     * Shto nje termin
     * @param date data terminit
     * @param ora ora terminit
     * @param pacienti ID e pacientit
     */
    public void shtoTermin(String date, String ora, int pacienti) {
        int i = termini.size();
        termini.add(new Termini(date, ora, pacienti));
    }

    /**
     * Fshin nje termin
     * @param i elementi i i-te qe do te fshihet
     */
    public void deleteTermin(int i){
        termini.remove(i);
    }

    /**
     *
     * @return Listen me termine
     */
    public ArrayList<Termini> getTermini(){
        return termini;
    }


    /**
     *
     * @return listen me id-te e pacienteve te regjistruar
     */
    public ArrayList<Integer> getIdPacienteve(){
        ArrayList<Integer> idt = new ArrayList<Integer>();
        for(int i = 0 ; i < pacienti.size();i++){
            idt.add(pacienti.get(i).getPacientId());
        }
        return idt;
    }
}
