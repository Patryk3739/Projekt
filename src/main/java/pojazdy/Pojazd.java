package pojazdy;

import skrzyzowania.Kierunki;
import skrzyzowania.Skrzyzowanie;
import silnik.Mapa;

import java.util.Random;

public abstract class Pojazd {
    private int rozmiar;
    private int wspolrzednaX;
    private int wspolrzednaY;
    private StanPojazdu aktualnyStanPojazdu;
    private Kierunki aktualnyKierunek;
    protected Mapa mapa;

    private static final Random random = new Random();

    public Kierunki getAktualnyKierunek() {
        return this.aktualnyKierunek;
    }

    public Kierunki gdzieJechac() {
        Kierunki[] kierunki = Kierunki.values();
        return kierunki[random.nextInt(kierunki.length)];
    }

    public Pojazd(int wspolrzednaX, int wspolrzednaY, int rozmiar, Mapa mapa) {
        this.wspolrzednaX = wspolrzednaX;
        this.wspolrzednaY = wspolrzednaY;
        this.rozmiar = rozmiar;
        this.aktualnyStanPojazdu = StanPojazdu.W_ruchu;
        this.mapa = mapa;
        this.aktualnyKierunek = gdzieJechac();
    }

    public int getRozmiar() {
        return rozmiar;
    }

    public int getWspolrzednaX() {
        return wspolrzednaX;
    }

    public int getWspolrzednaY() {
        return wspolrzednaY;
    }

    private void jedz() {
        switch (aktualnyKierunek) {
            case Gora -> wspolrzednaY--;
            case Prawo -> wspolrzednaX++;
            case Dol -> wspolrzednaY++;
            case Lewo -> wspolrzednaX--;
        }
    }

    public void jedzNastepnaTure() {
        if (this.aktualnyStanPojazdu == StanPojazdu.W_ruchu) {
            Skrzyzowanie stareSkrzyzowanie = mapa.pobierzWspolrzedneSkrzyz(this.wspolrzednaX, this.wspolrzednaY);
            stareSkrzyzowanie.opuscSkrzyz(this);
            this.aktualnyKierunek = gdzieJechac();
            jedz();
            if (czyNaMapie()) {
                Skrzyzowanie noweSkrzyzowanie = mapa.pobierzWspolrzedneSkrzyz(this.wspolrzednaX, this.wspolrzednaY);
                noweSkrzyzowanie.wjedzSkrzyz(this);
            }
        }
        if (czyNaMapie()) {
          Skrzyzowanie aktualneSkrzyzowanie = mapa.pobierzWspolrzedneSkrzyz(this.wspolrzednaX, this.wspolrzednaY);
          if (aktualneSkrzyzowanie.czyWolne(this)) {
              this.aktualnyStanPojazdu = StanPojazdu.W_ruchu;
          }
          else {
              this.aktualnyStanPojazdu = StanPojazdu.W_korku;
          }
        }
        //wyjechal poza mape
        else {
            this.aktualnyStanPojazdu = StanPojazdu.W_korku;
        }
    }

    public boolean czyNaMapie() {
        int wymiarMapy = mapa.getWymiar();
        return (wspolrzednaX >= 0 && wspolrzednaX < wymiarMapy) &&
                (wspolrzednaY >= 0 && wspolrzednaY < wymiarMapy);
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
