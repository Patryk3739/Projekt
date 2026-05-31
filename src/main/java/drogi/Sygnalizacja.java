package drogi;
import pojazdy.Pojazd;

public class Sygnalizacja extends Skrzyzowanie{
    private StanSwiatel aktualneSwiatlo = StanSwiatel.Czerwone;

    public Sygnalizacja(int x, int y) {
        super(x, y);
    }

    public void swiatla(int x, int y) {
    }

    public Boolean czyZielone(Pojazd p) {
        return aktualneSwiatlo == StanSwiatel.Zielone;
    }

    public void zmianaSwiatla() {
        //  zmiana stanu
        if (this.aktualneSwiatlo == StanSwiatel.Zielone) {
            this.aktualneSwiatlo = StanSwiatel.Czerwone;
        } else {
            this.aktualneSwiatlo = StanSwiatel.Zielone;
        }
    }
}
