package silnik;

import drogi.Skrzyzowanie;
import drogi.Sygnalizacja;

public class Mapa {
    private int wymiar;
    private Skrzyzowanie[][] mapa;

    public Mapa(){
        wymiar =10;
        mapa = new Skrzyzowanie[wymiar][wymiar];
    }

    public Skrzyzowanie pobierzWspolrzedneSkrzyz(int x, int y){
        return mapa[x][y];
    }

    public void aktualizujSwiatla(){
        for (int i=0; i< wymiar;i++){
            for (int j=0;j<wymiar;j++){
                Skrzyzowanie s = mapa[i][j];

                if (s instanceof Sygnalizacja swiatla){
                    swiatla.zmianaSwiatla();
                }
            }
        }

    }

    public int getWymiar() {
        return wymiar;
    }
}
