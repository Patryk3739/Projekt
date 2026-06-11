package pojazdy;

import skrzyzowania.Kierunki;
import skrzyzowania.Skrzyzowanie;
import silnik.Mapa;

import java.util.Random;

public abstract class Pojazd {
    private int rozmiar;
    private int wspolrzedna_x;
    private int wspolrzedna_y;
    private StanPojazdu aktualnyStanPojazdu;
    private Kierunki aktualnyKierunek;
    protected Mapa mapa;

    Random random = new Random();
    public Kierunki getAktualnyKierunek() {
        return this.aktualnyKierunek;
    }

    public Pojazd(int wspolrzedna_x, int wspolrzedna_y, int rozmiar, Mapa mapa){
        this.wspolrzedna_x = wspolrzedna_x;
        this.wspolrzedna_y = wspolrzedna_y;
        this.rozmiar = rozmiar;
        this.aktualnyStanPojazdu = StanPojazdu.W_ruchu;
        this.mapa = mapa;
        int losowy_kierunek = random.nextInt(4);
        switch(losowy_kierunek){
            case 0: this.aktualnyKierunek = Kierunki.Gora;
                break;
            case 1: this.aktualnyKierunek = Kierunki.Prawo;
                break;
            case 2: this.aktualnyKierunek = Kierunki.Dol;
                break;
            case 3: this.aktualnyKierunek = Kierunki.Lewo;
                break;
        }
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
        switch(aktualnyKierunek) {
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
            this.aktualnyStanPojazdu = StanPojazdu.W_ruchu;
            jedz();
        }
        else{
            this.aktualnyStanPojazdu = StanPojazdu.W_korku;
        }
    }

    public boolean czyNaMapie() {
        int wymiarMapy = mapa.getWymiar();
        return (wspolrzedna_x>=0 && wspolrzedna_x< wymiarMapy) &&
                (wspolrzedna_y >=0 && wspolrzedna_y< wymiarMapy);
    }

    public void setZmianaKierunku(Kierunki zmianaKierunku){
        this.aktualnyKierunek = zmianaKierunku;
    }

    public StanPojazdu getStanPojazdu(){
        return this.aktualnyStanPojazdu;
    }
    
    public void setStanPojazdu(StanPojazdu nowyStan){
        this.aktualnyStanPojazdu = nowyStan;
    }
}
