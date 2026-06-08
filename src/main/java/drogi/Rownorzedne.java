package drogi;
import pojazdy.Pojazd;

public class Rownorzedne extends Skrzyzowanie {
    public Rownorzedne(int x, int y) {
        super(x, y);
    }
    @Override
    public Boolean czyWolne(Pojazd p) {
        return regulaPrawejReki(p);
    }

    public Boolean regulaPrawejReki(Pojazd p) {
        Kierunki skadNadjezdza = null;

        /* przeglądamy wszystkie 4 kolejki(kierunki) na skrzyżowaniu
         żeby dowiedzieć się, w której kolejce znajduje sie pojazd*/
        for (Kierunki k : Kierunki.values()) {
            java.util.List<Pojazd> kolejka = kolejkiKierunkowe.get(k);
            if (kolejka != null && kolejka.contains(p)) {
                skadNadjezdza = k; //jakby tutaj stoi
                break;
            }
        }

        // jeśli czemuś pojazdu nie ma w kolejkach, pozwól jechać
        if (skadNadjezdza == null) {
            return true;
        }

        Kierunki prawaStrona = switch (skadNadjezdza) {
            case Gora   -> Kierunki.Lewo;
            case Lewo   -> Kierunki.Dol;
            case Dol    -> Kierunki.Prawo;
            case Prawo  -> Kierunki.Gora;
        };

        // sprawdzenie, czy po prawej jest pojazd
        java.util.List<Pojazd> kolejkaZPrawej = kolejkiKierunkowe.get(prawaStrona);

        if (kolejkaZPrawej != null && !kolejkaZPrawej.isEmpty()) {
            return false; // ustępuje
        }

        return true; // Droga wolna
    }

}
