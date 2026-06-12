package skrzyzowania;
import pojazdy.Pojazd;
import java.util.List;

public class Rownorzedne extends Skrzyzowanie {
    public Rownorzedne(int x, int y, int pojemnosc) {
        super(x, y, pojemnosc);
    }
    @Override
    public boolean czyWolne(Pojazd p) {

        return czyZmiesciSie(p) && regulaPrawejReki(p);
    }

    private boolean regulaPrawejReki(Pojazd p) {
        Kierunki skadNadjezdza = p.getAktualnyKierunek();
        //zabezpieczenie: jeśli na wszystkich 4 kolejkach stoi po 1 aucie, jak się zachowuje
        int zajeteKolejki = 0;
        for (Kierunki k : Kierunki.values()) {
            List<Pojazd> kol = getKolejkiKierunkowe().get(k);
            if (kol != null && !kol.isEmpty()) {
                zajeteKolejki++;
            }
        }
        if (zajeteKolejki == 4) {
            // bezwzględne pierwszeństwo kierunkowi Góra na przełamanie korka
            return skadNadjezdza == Kierunki.Gora;
        }

        Kierunki prawaStrona = switch (skadNadjezdza) {
            case Gora   -> Kierunki.Lewo;
            case Lewo   -> Kierunki.Dol;
            case Dol    -> Kierunki.Prawo;
            case Prawo  -> Kierunki.Gora;
        };

        // sprawdzenie, czy po prawej jest pojazd
        List<Pojazd> kolejkaZPrawej = getKolejkiKierunkowe().get(prawaStrona);
        if (kolejkaZPrawej != null && !kolejkaZPrawej.isEmpty()) {
            return false; // ustępuje
        }

        return true; // Droga wolna
    }

}
