package silnik;

import skrzyzowania.Skrzyzowanie;
import skrzyzowania.Sygnalizacja;

public class Mapa {
    private final int wymiar;
    private final Skrzyzowanie[][] mapa;

    public Mapa(int wymiar){
        this.wymiar=wymiar;
        this.mapa = new Skrzyzowanie[wymiar][wymiar];
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

    public Skrzyzowanie pobierzWspolrzedneSkrzyz(int x, int y){
        return mapa[x][y];
    }

    public void wstawSkrzyzowanie(int x, int y, Skrzyzowanie s){
        mapa[x][y] = s;
    }
}
