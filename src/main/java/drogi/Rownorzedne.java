package drogi;
import pojazdy.Pojazd;

public class Rownorzedne extends Skrzyzowanie {
    public Rownorzedne(int x, int y) {
        this.wspolrzednaX = x;
        this.wspolrzednaY = y;
    }

    public Boolean regulaPrawejReki(Pojazd p) {
        return true;
    }
}
