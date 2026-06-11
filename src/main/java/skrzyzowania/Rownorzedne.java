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
        Kierunki skadNadjezdza = null;
        /* przeglądamy wszystkie 4 kolejki(kierunki) na skrzyżowaniu
         żeby dowiedzieć się, w której kolejce znajduje sie pojazd*/
        for (Kierunki k : Kierunki.values()) {
            java.util.List<Pojazd> kolejka = getkolejkiKierunkowe().get(k);
            if (kolejka != null && kolejka.contains(p)) {
                skadNadjezdza = k; //jakby tutaj stoi
                break;
            }
        }
        // jeśli czemuś pojazdu nie ma w kolejkach, pozwól jechać
        if (skadNadjezdza == null) {
            return true;
        }
        //zabezpieczenie: jeśli na wszystkich 4 kolejkach stoi po 1 aucie, jak się zachowuje
        int zajeteKolejki = 0;
        for (Kierunki k : Kierunki.values()) {
            List<Pojazd> kol = getkolejkiKierunkowe().get(k);
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
        List<Pojazd> kolejkaZPrawej = getkolejkiKierunkowe().get(prawaStrona);

        if (kolejkaZPrawej != null && !kolejkaZPrawej.isEmpty()) {
            return false; // ustępuje
        }

        return true; // Droga wolna
    }

}
