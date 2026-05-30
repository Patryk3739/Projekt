package silnik;

import drogi.Skrzyzowanie;
import drogi.Sygnalizacja;

public class Mapa {
    private int wymiary;
    private Skrzyzowanie[][] mapa;

    public Mapa(){
        wymiary =10;
        mapa = new Skrzyzowanie[wymiary][wymiary];
    }

    public Skrzyzowanie pobierzWspolrzedneSkrzyz(int x, int y){
    return mapa[x][y];
    }

    public void aktualizujSwiatla(){
        for (int i=0; i< wymiary;i++){
            for (int j=0;j<wymiary;j++){
                Skrzyzowanie s = mapa[i][j];

                if (s instanceof Sygnalizacja swiatla){
                    swiatla.zmianaSwiatla();
                }
            }
        }

    }

}
