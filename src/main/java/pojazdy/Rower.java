package pojazdy;
import drogi.Skrzyzowanie;
public class Rower extends Pojazd {

    public Rower(int wspolrzedna_x, int wspolrzedna_y){
        super(wspolrzedna_x, wspolrzedna_y);
    }

    private void jedz() {

    }
    @Override
    public void jedzNastepnaTure(Skrzyzowanie s) {

    }
    @Override
    public Boolean czyNaMapie() {

        return true; //chwilowo zeby sie kompilowalo
    }
}
