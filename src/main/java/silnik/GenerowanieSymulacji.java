package silnik;
import drogi.Skrzyzowanie;
import pojazdy.Pojazd;
import java.util.Random;
import drogi.Rondo;
import drogi.Rownorzedne;
import drogi.Sygnalizacja;
import pojazdy.Samochod;
import pojazdy.Rower;
import pojazdy.Ciezarowka;

public class GenerowanieSymulacji {
        private static Random random = new Random();
    public static Mapa stworzMape(int wymiary, int skrzyzowania){
        Mapa mapa = new Mapa();
        int wymiaryMapy = mapa.getWymiar();

        for(int x = 0; x<wymiaryMapy; x++){
            for(int y = 0; y < wymiaryMapy; y++){
                Skrzyzowanie s = losujSkrzyz(x,y);
                mapa.wstawSkrzyzowanie(x, y, s); //dobrze by bylo dodac taka metode
            }
        }
        return mapa;

    }
    public static Pojazd losujPojazd(int wymiary, Mapa mapa){
        int start_x = random.nextInt(wymiary); //wymiary z maina
        int start_y = random.nextInt(wymiary);
        int losowanie_pojazdu = random.nextInt(3);

        switch(losowanie_pojazdu){
            case 0: return new Rower(start_x, start_y, mapa);
            case 1: return new Samochod(start_x, start_y, mapa);
            case 2: return new Ciezarowka(start_x, start_y, mapa);
            default: return new Samochod(start_x, start_y, mapa);
        }
    }
    private static Skrzyzowanie losujSkrzyz(int x, int y){
        int losowanie = random.nextInt(3);
        switch (losowanie){
            case 0: return new Sygnalizacja(x,y);
            case 1: return new Rondo(x,y);
            case 2: return new Rownorzedne(x,y);
            default: return new Rownorzedne(x,y);
        }
    }

}
