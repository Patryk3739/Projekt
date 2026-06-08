package silnik;

import skrzyzowania.Skrzyzowanie;
import pojazdy.Pojazd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private int poczatkowaLiczbaPojazdow;
    private int aktualnaTura;
    private List<Pojazd> listaPojazdow;
    private Mapa mapa;
    private Statystyki statystyki;

    public Main(int liczbaPojazdowNaStart){
        poczatkowaLiczbaPojazdow=liczbaPojazdowNaStart;
        aktualnaTura=0;
        mapa=null;
        statystyki=new Statystyki();
        listaPojazdow = new ArrayList<>();
    }

    public void zacznijSymulacje(){
        System.out.println("Rozpoczeto symulacje z liczba pojazdow: "+poczatkowaLiczbaPojazdow+".");
        GenerowanieSymulacji generator = new GenerowanieSymulacji();

        int wymiarMapy = 20;
        mapa = generator.stworzMape(wymiarMapy);

        for (int i=0;i<poczatkowaLiczbaPojazdow;i++){
            Pojazd p = generator.losujPojazd(wymiarMapy,mapa);
            listaPojazdow.add(p);
        }

        statystyki.zbierzDane(listaPojazdow);
        statystyki.stworzRaport(0);
    }

    public void wykonajTure(){
        aktualnaTura++;
        mapa.aktualizujSwiatla();

        for (Pojazd p: listaPojazdow){
            p.jedzNastepnaTure();
        }

        usunPojazd();
        statystyki.zbierzDane(listaPojazdow);
        statystyki.stworzRaport(aktualnaTura);
    }

    public boolean czyMozliwyRuch(){
        for (Pojazd p:listaPojazdow){

            int x = p.getWspolrzedna_x();
            int y = p.getWspolrzedna_y();

            Skrzyzowanie skrzyzowanie = mapa.pobierzWspolrzedneSkrzyz(x,y);

            if (skrzyzowanie.czyWolne(p)){
                return true;
            }
        }
        System.out.println("Mapa zostala zakorkowana. Koniec symulacji.");
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
    }

    public static void main(String[] args) {

        Random losowanie = new Random();

        int minPojazdow = 10;
        int maxPojazdow= 50;

        int wylosowanaLiczbaPojazdow = losowanie.nextInt(minPojazdow,maxPojazdow+1);

        Main symulacja = new Main(wylosowanaLiczbaPojazdow);

        symulacja.zacznijSymulacje();

        while(!symulacja.czyKoniecSymulacji() && symulacja.czyMozliwyRuch()){
            symulacja.wykonajTure();
        }

        symulacja.zakonczSymulacje();
    }
}