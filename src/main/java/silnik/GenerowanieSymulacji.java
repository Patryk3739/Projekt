package silnik;
import skrzyzowania.Skrzyzowanie;
import pojazdy.Pojazd;
import java.util.Random;
import skrzyzowania.Rondo;
import skrzyzowania.Rownorzedne;
import skrzyzowania.Sygnalizacja;
import pojazdy.Samochod;
import pojazdy.Rower;
import pojazdy.Ciezarowka;

public class GenerowanieSymulacji {
        private Random random = new Random();
    public Mapa stworzMape(int wymiary){
        Mapa mapa = new Mapa(wymiary);
        int wymiaryMapy = mapa.getWymiar();

        for(int x = 0; x<wymiaryMapy; x++){
            for(int y = 0; y < wymiaryMapy; y++){
                Skrzyzowanie s = losujSkrzyz(x,y);
                mapa.wstawSkrzyzowanie(x, y, s);
            }
        }
        return mapa;

    }
    public Pojazd losujPojazd(int wymiary, Mapa mapa){
        int start_x = random.nextInt(wymiary);
        int start_y = random.nextInt(wymiary);
        int losowanie_pojazdu = random.nextInt(3);

        switch(losowanie_pojazdu){
            case 0: return new Rower(start_x, start_y, mapa);
            case 1: return new Samochod(start_x, start_y, mapa);
            case 2: return new Ciezarowka(start_x, start_y, mapa);
            default: return new Samochod(start_x, start_y, mapa);
        }
    }
    private Skrzyzowanie losujSkrzyz(int x, int y){
        int losowanie = random.nextInt(3);
        switch (losowanie){
            case 0: return new Sygnalizacja(x,y);
            case 1: return new Rondo(x,y);
            case 2: return new Rownorzedne(x,y);
            default: return new Rownorzedne(x,y);
        }
    }

}
