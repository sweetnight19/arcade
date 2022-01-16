# Arcade
![Alt text](img/menu.png?raw=true "Menu")
## Herramientas necesarias para el funcionamiento del programa

- IDE (`IntelliJ IDEA` o també el `Visual Studio Code` )
- Java JDK 17

---

## Librerías extras utilizadas en todos los módulos del proyecto

- jfiglet-0.0.8
- Arcade

Ambas librerías se añaden al proyecto yendo a `file` -> `projectStructure` -> `librarías`.
A continuación se clica en `+` dentro del apartado `java` del menú que aparece y seleccionar todos los módulos del proyecto.

---

## Proceso de funcionamiento del proyecto

1. Inicializar `IntelliJ IDEA` para indexar todo el proyecto.

2. Inicializar mediante el botón `run` del `main` del módulo del cliente.

3. En el fichero Menu.java se encuentra la constante `SIZE` que contiene medidas de los minijuegos.

4. Al ejecutar, se abre un menú que permite al usuario escoger `Arcade de Laberintos` que permite indicar el 
algoritmo para resolver el primer minijuego: El laberinto. A continuación la siguiente opción es 
`Arcade de Palabras` donde podras tambien escoger el algoritmo.
5. Ya inicados ambas estrategias, podras escoger el tercer campo `Iniciar`.
    1. Se empezará por el primer minijuego: `El Laberinto` con el algoritmo escogido. Justo en el código en el fichero
   Labyrithn.java dentro de la carpeta src, verá las funciones de backtacking, backtrackingWithOpt y BrandAndBound con 
   una línea comentada de la variable renderer, si se descomenta, se verá el procedimiento que va siguiendo.
   
   ![Alt text](img/labyrinth.png?raw=true "Laberinto")
    
    3. Una vez terminado, en la consola se indicará el tiempo tomado para llevar a cabo los cálculos. 
    4. Por último empezará el siguiente minijuego: `La Sopa de Letras`
    
       ![Alt text](img/wordSearch.png?raw=true "Sopa de letras")

---

## Licencia

Distribuido bajo la licencia GPL-3.0. Consulte `LICENSE.txt` para obtener más información.

---
## Contacto

Pere Cusó - pere.cuso@salle.url.edu\
David Marquet - david.marquet@salle.url.edu

Link del proyecto: https://github.com/sweetnight19/arcade
