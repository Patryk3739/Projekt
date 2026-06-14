package skrzyzowania;
import pojazdy.Pojazd;

public class Rondo extends Skrzyzowanie{
    public Rondo(int pojemnosc) {
        super(pojemnosc);
    }
    @Override
    public boolean czyWolne(Pojazd p) {
        return czyZmiesciSie(p);
    }
}
