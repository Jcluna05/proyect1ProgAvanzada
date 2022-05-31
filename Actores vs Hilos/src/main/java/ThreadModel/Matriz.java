package ThreadModel;

import java.util.ArrayList;
import java.util.List;

public class Matriz {

    int[][] matriz;
    List<CalculoElementoMatriz> hilos = new ArrayList<>();

    public Matriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public Matriz multiplicacionHilos(Matriz matriz2) throws InterruptedException {

        if(matriz.length == matriz2.matriz[0].length){

            int[][] matrizfinal = new int[matriz.length][matriz2.matriz[0].length];//Se asigana el tamaño de la matriz final

            for(var i=0 ; i< matrizfinal.length ; i++){
                for(var j=0 ; j< matrizfinal[0].length ; j++){
                    CalculoElementoMatriz thread = new CalculoElementoMatriz(this , matriz2 , i , j); // Creamos un nuevo hilo para que calcule el valor del elemento
                    thread.start(); //Inizializamos el hilo
                    hilos.add(thread);
                }
            }

            sincronizarHilos(hilos);

            for(var t : hilos){

                matrizfinal[t.getFila()][t.getColumna()] = t.getSuma(); //Colocamos los elementos calculados por los hilos en la matriz final
            }

            return new Matriz(matrizfinal);

        }else{
            throw new IllegalArgumentException("No se puede multiplicar");
        }
    }

    public void sincronizarHilos( List<CalculoElementoMatriz> hilos) throws InterruptedException {

        for(var hilo:hilos){

            hilo.join();
        }

    }
}
