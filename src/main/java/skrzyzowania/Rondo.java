package skrzyzowania;
import pojazdy.Pojazd;

public class Rondo extends Skrzyzowanie{

    public Rondo(int x, int y,int pojemnosc) {
        super(x, y, pojemnosc);
    }
    @Override
    public boolean czyWolne(Pojazd p) {
        return czyZmiesciSie(p);
    }


}
