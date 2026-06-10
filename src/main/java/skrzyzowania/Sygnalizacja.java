package skrzyzowania;
import pojazdy.Pojazd;

import java.util.List;

public class Sygnalizacja extends Skrzyzowanie{
    private stanSwiatel aktualnaFaza = stanSwiatel.wszystkieCzerwone;
    private stanSwiatel swiatloPoCzerwonym = stanSwiatel.zielonePoziome;

    public Sygnalizacja(int x, int y, int pojemnosc) {
        super(x, y, pojemnosc);
    }
    @Override
    public boolean czyWolne(Pojazd p) {
        if(!czyZmiesciSie(p)){
            return false;
        }
        if (aktualnaFaza == stanSwiatel.wszystkieCzerwone){
            return false;
        }
        Kierunki skadNadjezdza = null;
        for (Kierunki k: Kierunki.values()){
            List<Pojazd> kolejka = getkolejkiKierunkowe().get(k);
            if (kolejka != null && kolejka.contains(p)){
                skadNadjezdza = k;
                break;
            }
        }
        if (skadNadjezdza == null){
            return true;
        }
        //po czerwonym najpierw startują kierunki pionowe
        if (aktualnaFaza == stanSwiatel.zielonePionowe) {
            return skadNadjezdza == Kierunki.Gora || skadNadjezdza == Kierunki.Dol;
        }
        if (aktualnaFaza == stanSwiatel.zielonePoziome){
            return skadNadjezdza == Kierunki.Lewo || skadNadjezdza == Kierunki.Prawo;
        }
        return false;
    }
    public void zmianaSwiatla() {
        if (aktualnaFaza == stanSwiatel.wszystkieCzerwone) {
            // zmieniamy oś na przeciwną
            if (swiatloPoCzerwonym == stanSwiatel.zielonePoziome) {
                aktualnaFaza = stanSwiatel.zielonePionowe;
            } else {
                aktualnaFaza = stanSwiatel.zielonePoziome;
            }
        } else { // faza wszystkieCzerwone i zapisujemy, jakie światło było przed
            swiatloPoCzerwonym = aktualnaFaza;
            aktualnaFaza = stanSwiatel.wszystkieCzerwone;
        }

    }
}
