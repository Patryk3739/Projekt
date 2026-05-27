package drogi;

public class Sygnalizacja extends Skrzyzowanie{
    private StanSwiatel aktualneSwiatlo;

    public Sygnalizacja(int x, int y) {
        this.wspolrzednaX = x;
        this.wspolrzednaY = y;
        this.aktualneSwiatlo = StanSwiatel.Czerwone; // startowy stan swiatel
    }

    public void swiatla(int x, int y) {
    }

    public Boolean czyZielone(Pojazd p) {
        return aktualneSwiatlo == StanSwiatel.Zielone;
    }

    public void zmianaSwiatla() {
        // Prosta logicznie zmiana stanu
        if (this.aktualneSwiatlo == StanSwiatel.Zielone) {
            this.aktualneSwiatlo = StanSwiatel.Czerwone;
        } else {
            this.aktualneSwiatlo = StanSwiatel.Zielone;
        }
    }
}
