package drogi;
import pojazdy.Pojazd;

import java.util.List;
import java.util.Map;

public abstract class Skrzyzowanie {
    protected int pojemnosc;
    protected int wspolrzednaX;  //atributy klasy
    protected int wspolrzednaY;

    protected Map<Kierunki, List<Pojazd>> kolejkiKierunkowe;


    public Boolean czyWolne(Pojazd p) {
        return true;
    }
    public void wjedzSkrzyz(Pojazd p) {
    }

    public void opuscSkrzyz(Pojazd p) {
    }
}
