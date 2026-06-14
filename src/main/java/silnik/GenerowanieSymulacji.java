package silnik;
import java.util.Random;
import skrzyzowania.*;
import pojazdy.*;

public class GenerowanieSymulacji {

        private static final Random random = new Random();

    public Mapa stworzMape(int wymiary){
        Mapa mapa = new Mapa(wymiary);

        for(int x = 0; x< mapa.getWymiar(); x++){
            for(int y = 0; y < mapa.getWymiar(); y++){
                Skrzyzowanie s = losujSkrzyz(x,y);
                mapa.wstawSkrzyzowanie(x, y, s);
            }
        }
        return mapa;
    }

    public Pojazd losujPojazd(Mapa mapa){
        int wymiary = mapa.getWymiar();
        int startX;
        int startY;
        Skrzyzowanie s;
        Pojazd nowyPojazd;
        do {
            startX = random.nextInt(wymiary);
            startY = random.nextInt(wymiary);
            int losowaniePojazdu = random.nextInt(3);
            nowyPojazd = switch (losowaniePojazdu) {
                case 0 -> new Samochod(startX, startY, mapa);
                case 1 -> new Rower(startX, startY, mapa);
                default -> new Ciezarowka(startX, startY, mapa);
            };
            s = mapa.pobierzWspolrzedneSkrzyz(startX, startY);
        } while (!s.czyZmiesciSie(nowyPojazd));
        s.wjedzSkrzyz(nowyPojazd);
        return nowyPojazd;
    }
    private Skrzyzowanie losujSkrzyz(int x, int y){
        int losowanie = random.nextInt(3);
        return switch (losowanie){
            case 0 -> new Sygnalizacja(20);
            case 1 -> new Rondo(12);
            default -> new Rownorzedne(16);
        };
    }
}
