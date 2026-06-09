package silnik;

import org.junit.jupiter.api.Test;
import pojazdy.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatystykiTest {

    @Test
    void czyPoprawnieZliczaTypyPojazdow(){
        Statystyki statystyki = new Statystyki();
        Mapa mapa = new Mapa(20);
        List<Pojazd> listaTestowa = new ArrayList<>();

        listaTestowa.add(new Samochod(3,3,mapa));
        listaTestowa.add(new Samochod(5,1,mapa));
        listaTestowa.add(new Rower(1,9,mapa));
        listaTestowa.add(new Ciezarowka(12,8,mapa));
        listaTestowa.add(new Ciezarowka(2,4,mapa));
        listaTestowa.add(new Ciezarowka(8,2,mapa));

        statystyki.zbierzDane(listaTestowa);

        assertEquals(2,statystyki.getLiczbaSamochodow(), "Powinny byc 2 samochody.");
        assertEquals(3,statystyki.getLiczbaCiezarowek(),"Powinny byc 3 ciezarowki.");
        assertEquals(1,statystyki.getLiczbaRowerow(),"Powinien byc 1 rower.");
        assertEquals(6,statystyki.getLiczbaLaczna(),"Razem powinno byc 6 pojazdow.");
    }

    @Test
    void czyPoprawnieZliczaPojazdyWKorku(){
        Statystyki statystyki = new Statystyki();
        Mapa mapa = new Mapa(20);
        List<Pojazd> listaTestowa2 = new ArrayList<>();

        Pojazd p1 = new Samochod(1,1, mapa);

        p1.setStanPojazdu(p1.StanPojazdu.W_korku);

        listaTestowa2.add(p1);

        statystyki.zbierzDane(listaTestowa2);

        assertEquals(1,statystyki.getLiczbaWKorku(),"Powinien byc 1 pojazd w korku.");

    }
}
