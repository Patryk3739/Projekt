package skrzyzowania;
import pojazdy.Pojazd;
public class Rondo extends Skrzyzowanie{

    private int aktualnaLiczbaAut = 0;
    // maksymalny limit aut na skrzyzowaniu (np. 10)
    private final int maxPojemnoscRonda = 10;

    public Rondo(int x, int y) {
        super(x, y);
    }
    @Override
    public Boolean czyWolne(Pojazd p) {
        return aktualnaLiczbaAut < maxPojemnoscRonda;
    }
    public Boolean czyJestes(Pojazd p){
        /*sprawdzamy wszystkie kolejki,
        jeśli żaden pojazd nie stoi w kolejce wjazdowej,
        znaczy że jest na rondzie */
        for (Kierunki k : Kierunki.values()) {
            java.util.List<Pojazd> kolejka = getkolejkiKierunkowe().get(k);
            if (kolejka != null && kolejka.contains(p)) {
                return false;
            }
        }
        return true;
    }
    // aktualizacja licznika na wjeździe i zjeździe
    @Override
    public void wjedzSkrzyz(Pojazd p) {
        aktualnaLiczbaAut++;
    }
    @Override
    public void opuscSkrzyz(Pojazd p) {
        if (aktualnaLiczbaAut > 0) {
            aktualnaLiczbaAut--;
        }
    }
}
