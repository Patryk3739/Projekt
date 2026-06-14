package skrzyzowania;
import pojazdy.Pojazd;

public class Sygnalizacja extends Skrzyzowanie{
    private StanSwiatel aktualnaFaza = StanSwiatel.wszystkieCzerwone;
    private StanSwiatel swiatloPoCzerwonym = StanSwiatel.zielonePoziome;

    public Sygnalizacja(int pojemnosc) {
        super(pojemnosc);
    }
    @Override
    public boolean czyWolne(Pojazd p) {
        if(!czyZmiesciSie(p)){
            return false;
        }

        if (aktualnaFaza == StanSwiatel.wszystkieCzerwone){
            return false;
        }
        Kierunki skadNadjezdza = p.getAktualnyKierunek();

        //po czerwonym najpierw startują kierunki pionowe
        if (aktualnaFaza == StanSwiatel.zielonePionowe) {
            return skadNadjezdza == Kierunki.Gora || skadNadjezdza == Kierunki.Dol;
        }
        if (aktualnaFaza == StanSwiatel.zielonePoziome){
            return skadNadjezdza == Kierunki.Lewo || skadNadjezdza == Kierunki.Prawo;
        }
        return false;
    }
    public void zmianaSwiatla() {
        if (aktualnaFaza == StanSwiatel.wszystkieCzerwone) {
            // zmieniamy oś na przeciwną
            if (swiatloPoCzerwonym == StanSwiatel.zielonePoziome) {
                aktualnaFaza = StanSwiatel.zielonePionowe;
            } else {
                aktualnaFaza = StanSwiatel.zielonePoziome;
            }
        } else { // faza wszystkieCzerwone i zapisujemy, jakie światło było przed
            swiatloPoCzerwonym = aktualnaFaza;
            aktualnaFaza = StanSwiatel.wszystkieCzerwone;
        }

    }
}
