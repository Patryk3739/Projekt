package silnik;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.List;
import pojazdy.Pojazd;
import pojazdy.Samochod;
import pojazdy.Rower;
import pojazdy.Ciezarowka;
public class Statystyki {
    private String nazwaPliku = "statystyki.csv";

    private int liczbaSamochodow;
    private int liczbaRowerow;
    private int liczbaCiezarowek;
    private int liczbaLaczna;

    public Statystyki(){
        liczbaCiezarowek =0;
        liczbaSamochodow=0;
        liczbaRowerow=0;
        liczbaLaczna=0;
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku))) {
            writer.println("Tura;Samochody;Ciezarowki;Rowery;Lacznie");
        } catch (IOException e) {
            System.err.println("Błąd podczas inicjalizacji pliku CSV: " + e.getMessage());
        }
    }

    public void zbierzDane(List<Pojazd> listaPojazdow){
        liczbaSamochodow=0;
        liczbaRowerow=0;
        liczbaCiezarowek=0;


    for (Pojazd p:listaPojazdow){
        if (p instanceof Samochod){
            liczbaSamochodow++;
        }
        if (p instanceof Rower){
            liczbaRowerow++;
        }
        if (p instanceof Ciezarowka){
            liczbaCiezarowek++;
        }
    }

    liczbaLaczna = liczbaCiezarowek+liczbaRowerow+liczbaSamochodow;
    }

    public void stworzRaport(int aktualnaTura){
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku, true))) {

            writer.println(aktualnaTura + ";" + liczbaSamochodow + ";" + liczbaCiezarowek + ";" + liczbaRowerow + ";" + liczbaLaczna);

        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu do pliku CSV: " + e.getMessage());
        }

    }
}
