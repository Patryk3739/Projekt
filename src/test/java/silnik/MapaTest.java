package silnik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MapaTest {

    @Test
    void czyMapaMaPoprawnyWymiar(){
     Mapa mapa = new Mapa(20);
     assertEquals(20,mapa.getWymiar(),"Sprawdzenie przypisania przez konstruktor wymiaru 20 do mapy.");
    }

    @Test
    void czyMapaNieJestPusta(){
        Mapa mapa = new Mapa(20);
        assertNotNull(mapa.pobierzWspolrzedneSkrzyz(5,5),"Sprawdzenie czy mapa zwara nulla.");
    }
}
