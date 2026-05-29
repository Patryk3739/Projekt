package silnik;

import drogi.Pojazd;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private int pojazdy;
    private int skrzyzowania;
    private int aktualnaTura;
    private List<Pojazd> listaPojazdow;

    public Main(){
        pojazdy=0;
        skrzyzowania=0;
        aktualnaTura=0;

        listaPojazdow = new ArrayList<>();
    }

    public void zacznijSymulacje(){}

    public void wykonajTure(){}

    public boolean czyMozliwyRuch(){return true;}

    public void usunPojazd(){}

    public boolean czyKoniecSymulacji(){return true;}

    public void zakonczSymulacje(){}

    public static void main(String[] args) {

        Main symulacja = new Main();


    }
}