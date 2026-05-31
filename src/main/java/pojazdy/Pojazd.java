package pojazdy;

import drogi.Kierunki;
import drogi.Skrzyzowanie;

public abstract class Pojazd {
    private int rozmiar;
    private int wspolrzedna_x;
    private int wspolrzedna_y;
    private StanPojazdu aktualny_stan_pojazdu;
    private Kierunki aktualny_kierunek;

    public Pojazd(int wspolrzedna_x, int wspolrzedna_y){
        this.wspolrzedna_x = wspolrzedna_x;
        this.wspolrzedna_y = wspolrzedna_y;
    }
    public int getWspolrzedna_x(){
        return wspolrzedna_x;
    }
    public int getWspolrzedna_y(){
        return wspolrzedna_y;
    }
    public void setWspolrzedna_x(int wspolrzedna_x){
        this.wspolrzedna_x=wspolrzedna_x;
    }
    public void setWspolrzedna_y(int wspolrzedna_y){
        this.wspolrzedna_y=wspolrzedna_y;
    }
    private void jedz() {

    }

    public void jedzNastepnaTure(Skrzyzowanie s) {

    }

    public Boolean czyNaMapie() {

        return true; //chwilowo zeby sie kompilowalo
    }
}
