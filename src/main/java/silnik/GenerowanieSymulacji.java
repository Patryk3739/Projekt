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

    public static Mapa stworzMape(int wymiary, int skrzyzowania){
        return new Mapa();

    }
    public static Pojazd losujPojazd(int wymiary){
        Random random = new Random();
        int start_x = random.nextInt(wymiary); //wymiary z maina
        int start_y = random.nextInt(wymiary);
        int losowanie_pojazdu = random.nextInt(3);

        switch(losowanie_pojazdu){
            case 0: return new Rower(start_x, start_y);
            case 1: return new Samochod(start_x, start_y);
            case 2: return new Ciezarowka(start_x, start_y);
            default: return new Samochod(start_x, start_y);
        }
    }
    private static Skrzyzowanie losujSkrzyz(int x, int y){
        Random random = new Random();
        int losowanie = random.nextInt(3);
        switch (losowanie){
            case 0: return new Sygnalizacja(x,y);
            case 1: return new Rondo(x,y);
            case 2: return new Rownorzedne(x,y);
            default: return new Rownorzedne(x,y);
        }
    }

}
