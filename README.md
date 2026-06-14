# 🚦 Symulacja Ruchu Drogowego

![Java](https://img.shields.io/badge/Java-21-orange) 
![JUnit](https://img.shields.io/badge/JUnit-5-green) 
![Gradle](https://img.shields.io/badge/Gradle-Build-blue)

W pełni obiektowy silnik symulacji ruchu drogowego napisany w Javie. Projekt modeluje zachowania pojazdów na siatce miejskiej (mapie), uwzględniając różne typy skrzyżowań, maszyny stanów oraz zasady fizyki ruchu.

## ⚙️ Kluczowe mechaniki

* **Inteligentne Skrzyżowania:** Obsługa skrzyżowań równorzędnych (z wbudowanym bezpiecznikiem zapobiegającym zakleszczeniom) oraz rond.
* **Maszyna Stanów Sygnalizacji:** Cykle świetlne z wbudowanym buforem bezpieczeństwa na opuszczenie skrzyżowania, zapobiegające kolizjom.
* **Fizyka Pojazdów:** Autonomiczna nawigacja po mapie, stany pojazdów (`W_ruchu`, `W_korku`) oraz wykrywanie krawędzi siatki.
* **Analityka i Raportowanie:** Zbieranie danych o ruchu w czasie rzeczywistym i automatyczny eksport wyników symulacji do pliku `.csv`.
* **Czysta Architektura:** Projekt pokryty testami jednostkowymi (JUnit 5) z weryfikacją pokrycia kodu (JaCoCo).

## 🚀 Szybki start (Quick Start)
Projekt wykorzystuje system budowania **Gradle**. Aby go uruchomić, wystarczy użyć wbudowanego wrappera z poziomu terminala.

### 1. Uruchomienie symulacji
Aby skompilować i odpalić główną pętlę symulacji, wpisz w terminalu:
```./gradlew run```

### 2. Uruchomienie testów i raportu JaCoCo
Aby odpalić wszystkie testy jednostkowe i wygenerować raport pokrycia kodu:
```./gradlew clean test jacocoTestReport```
