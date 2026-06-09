package silnik;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.List;

import pojazdy.*;

public class Statystyki {
    private String nazwaPliku = "statystyki.csv";

    private int liczbaSamochodow;
    private int liczbaRowerow;
    private int liczbaCiezarowek;
    private int liczbaLaczna;
    private int liczbaWKorku;

    public Statystyki(){
        liczbaCiezarowek =0;
        liczbaSamochodow=0;
        liczbaRowerow=0;
        liczbaLaczna=0;
        liczbaWKorku=0;
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku))) {
            writer.println("Tura;Samochody;Ciezarowki;Rowery;W_korku;Lacznie");
        } catch (IOException e) {
            System.err.println("Błąd podczas inicjalizacji pliku CSV: " + e.getMessage());
        }
    }

    public void zbierzDane(List<Pojazd> listaPojazdow){
        liczbaSamochodow=0;
        liczbaRowerow=0;
        liczbaCiezarowek=0;
        liczbaWKorku=0;


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

        if (p.getStanPojazdu() == StanPojazdu.W_korku) {
            liczbaWKorku++;
        }
    }

    liczbaLaczna = liczbaCiezarowek+liczbaRowerow+liczbaSamochodow;
    }

    public void stworzRaport(int aktualnaTura){
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku, true))) {

            writer.println(aktualnaTura + ";" + liczbaSamochodow + ";" + liczbaCiezarowek + ";" + liczbaRowerow + ";"+ liczbaWKorku +";" + liczbaLaczna);

        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu do pliku CSV: " + e.getMessage());
        }

    }

    public int getLiczbaCiezarowek() {
        return liczbaCiezarowek;
    }

    public int getLiczbaRowerow() {
        return liczbaRowerow;
    }

    public int getLiczbaSamochodow() {
        return liczbaSamochodow;
    }

    public int getLiczbaLaczna() {
        return liczbaLaczna;
    }

    public int getLiczbaWKorku() {
        return liczbaWKorku;
    }
}
