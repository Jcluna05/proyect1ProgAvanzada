package ThreadModel;

public class CalculoElementoMatriz extends Thread{

    Matriz m1;
    Matriz m2;
    int fila;
    int columna;
    int suma;

    public CalculoElementoMatriz(Matriz m1, Matriz m2, int fila, int columna) {
        this.m1 = m1;
        this.m2 = m2;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public void run() {
        calculo();
    }

    public void calculo(){
        suma = 0;
        for(var i=0 ; i<m1.matriz.length ;i++){
            suma = suma + m1.matriz[fila][i] * m2.matriz[i][columna];
        }
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getSuma() {
        return suma;
    }
}
