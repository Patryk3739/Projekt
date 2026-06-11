package silnik;

import pojazdy.StanPojazdu;
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

    private int licznikZastoju;
    private final int limitZastoju=5;
    private final int coIleTurZmianaSwiatla=5;

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
        if (aktualnaTura % coIleTurZmianaSwiatla == 0) {
            mapa.aktualizujSwiatla();
        }
        for (Pojazd p: listaPojazdow){
            p.jedzNastepnaTure();
        }

        usunPojazd();
        statystyki.zbierzDane(listaPojazdow);
        statystyki.stworzRaport(aktualnaTura);
    }

    public boolean czyMozliwyRuch(){
        for (Pojazd p:listaPojazdow){
            if (p.getStanPojazdu() == StanPojazdu.W_ruchu){
                licznikZastoju=0;
                return true;
            }
        }
        licznikZastoju++;

        if (licznikZastoju >= limitZastoju) {
            System.out.println("Mapa zostala zakorkowana (" + limitZastoju + " tur bezruchu z rzedu). Koniec symulacji.");
            return false;
        }

        System.out.println("[OSTRZEZENIE] Wszystkie auta stoja (Tura zastoju: " + licznikZastoju + "/" + limitZastoju + ")");        return true;
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