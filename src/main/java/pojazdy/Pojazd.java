package pojazdy;

import drogi.Kierunki;
import drogi.Skrzyzowanie;
import silnik.Mapa;

public abstract class Pojazd {
    private int rozmiar;
    private int wspolrzedna_x;
    private int wspolrzedna_y;
    private StanPojazdu aktualny_stan_pojazdu;
    private Kierunki aktualny_kierunek;
    protected Mapa mapa;

    public Pojazd(int wspolrzedna_x, int wspolrzedna_y, int rozmiar, Mapa mapa){
        this.wspolrzedna_x = wspolrzedna_x;
        this.wspolrzedna_y = wspolrzedna_y;
        this.rozmiar = rozmiar;
        this.aktualny_stan_pojazdu = StanPojazdu.W_ruchu;
        this.mapa = mapa;
    }
    public int getRozmiar(){
        return rozmiar;
    }

    public int getWspolrzedna_x(){
        return wspolrzedna_x;
    }
    public int getWspolrzedna_y(){
        return wspolrzedna_y;
    }

    private void jedz() {
        switch(aktualny_kierunek) {
            case Gora:
                wspolrzedna_y++;
                break;
            case Prawo:
                wspolrzedna_x++;
                break;
            case Dol:
                wspolrzedna_y--;
                break;
            case Lewo:
                wspolrzedna_x--;
                break;
        }
    }

    public void jedzNastepnaTure() {
        Skrzyzowanie s = mapa.pobierzWspolrzedneSkrzyz(this.wspolrzedna_x, this.wspolrzedna_y);
        if (s.czyWolne(this)){
            this.aktualny_stan_pojazdu = StanPojazdu.W_ruchu;
            jedz();
        }
        else{
            this.aktualny_stan_pojazdu = StanPojazdu.W_korku;
        }
    }

    public boolean czyNaMapie() {
        int wymiarMapy = mapa.getWymiar();
        return (wspolrzedna_x>=0 && wspolrzedna_x< wymiarMapy) &&
                (wspolrzedna_y >=0 && wspolrzedna_y< wymiarMapy);
    }
}
