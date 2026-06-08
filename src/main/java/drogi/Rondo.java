package drogi;
import pojazdy.Pojazd;
public class Rondo extends Skrzyzowanie{
    public Rondo(int x, int y) {
        super(x, y);
    }

    public Boolean czyJestes(Pojazd p) {
        return false;
    }
}
