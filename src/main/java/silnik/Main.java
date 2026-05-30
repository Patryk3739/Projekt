package silnik;

import pojazdy.Pojazd;


import java.util.ArrayList;
import java.util.List;



public class Main {
    private int pojazdy;
    private int skrzyzowania;
    private int aktualnaTura;
    private List<Pojazd> listaPojazdow;
    private Mapa mapa;
    private Statystyki statystyki;

    public Main(){
        pojazdy=0;
        skrzyzowania=0;
        aktualnaTura=0;
        mapa=null;

        listaPojazdow = new ArrayList<>();
    }

    public void zacznijSymulacje(){
        System.out.println("Rozpoczeto symulacje.");
        pojazdy = 10;
        GenerowanieSymulacji generator = new GenerowanieSymulacji();
        mapa = generator.stworzMape(20,400);
        int wymiar = mapa.getWymiar();
        skrzyzowania = wymiar*wymiar;

        for (int i=0;i<pojazdy;i++){
            Pojazd p = generator.losujPojazd(wymiar);
            listaPojazdow.add(p);
        }
    }

    public void wykonajTure(){
        aktualnaTura++;
        mapa.aktualizujSwiatla();
        for (int i = 0;i < listaPojazdow.size();i++){
            Pojazd p = listaPojazdow.get(i);
            p.jedzNastepnaTure();
        }
        usunPojazd();

        statystyki.zbierzDane(listaPojazdow);


    }

    public boolean czyMozliwyRuch(){
        for (Pojazd p:listaPojazdow){
            if (mapa.czyWolne(p)){
                return true;
            }
        }
        System.out.println("Mapa zostala zakorkowana. Koniec symulacji.");
        statystyki.stworzRaport(aktualnaTura);
        return false;
    }

    public void usunPojazd(){
        for (int i= listaPojazdow.size()-1;i>=0;i--){
            Pojazd p = listaPojazdow.get(i);

            if (!p.czyNaMapie()){
                listaPojazdow.remove(i);
            }
        }

    }

    public boolean czyKoniecSymulacji(){
    return listaPojazdow.isEmpty();
    }

    public void zakonczSymulacje(){
        System.out.println("Zakonczono symulacje.");
        statystyki.stworzRaport(aktualnaTura);

    }

    public static void main(String[] args) {

        Main symulacja = new Main();

        symulacja.zacznijSymulacje();

        while(!symulacja.czyKoniecSymulacji() && symulacja.czyMozliwyRuch()){
            symulacja.wykonajTure();
        }

        symulacja.zakonczSymulacje();
    }
}