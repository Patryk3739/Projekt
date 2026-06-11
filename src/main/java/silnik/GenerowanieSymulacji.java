package silnik;
import java.util.Random;
import skrzyzowania.*;
import pojazdy.*;

public class GenerowanieSymulacji {
        private Random random = new Random();
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
        int startX = random.nextInt(wymiary);
        int startY = random.nextInt(wymiary);
        int losowanie_pojazdu = random.nextInt(3);

        Pojazd nowyPojazd = switch(losowanie_pojazdu){
            case 0 -> new Samochod(startX, startY, mapa);
            case 1 -> new Rower(startX, startY, mapa);
            default -> new Ciezarowka(startX, startY, mapa);
        };
        Skrzyzowanie s = mapa.pobierzWspolrzedneSkrzyz(startX, startY);
        s.wjedzSkrzyz(nowyPojazd);
        return nowyPojazd;
    }
    private Skrzyzowanie losujSkrzyz(int x, int y){
        int losowanie = random.nextInt(3);
        return switch (losowanie){
            case 0 -> new Sygnalizacja(x, y, 20);
            case 1 -> new Rondo(x, y, 12);
            default -> new Rownorzedne(x, y, 16);
        };
    }

}
