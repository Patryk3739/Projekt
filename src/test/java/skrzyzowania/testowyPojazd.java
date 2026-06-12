package skrzyzowania;
import pojazdy.Pojazd;

public class testowyPojazd extends Pojazd{
    private int rozmiar;
    private Kierunki kierunek;

    public testowyPojazd(int rozmiar, Kierunki kierunek) {
        super(0, 0, rozmiar, null); // współrzędne 0,0 i brak mapy wystarczą do testów
        this.rozmiar = rozmiar;
        this.kierunek = kierunek;
    }

    @Override
    public int getRozmiar() {
        return this.rozmiar;
    }

    @Override
    public Kierunki getAktualnyKierunek() {
        return this.kierunek;
    }
}
