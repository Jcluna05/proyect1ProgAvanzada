package ActorModel;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MatrizActors extends AbstractActor {

    int[][] matriz;
    int[][] matrizfinal;
    ActorSystem system = ActorSystem.create("system");

    public MatrizActors() {
    }

    public static Props props() { // Se crea el props que permitira crear un ActorRef con este tipo de clase
        return Props.create(MatrizActors.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().create()
                .match(DevolverElemento.class , this::definirMatrizfinal)
                .match(Matriz2.class, this::multiplicarMatrices)
                .match(Matriz1.class ,this::setMatriz)
                .build();
    }

    public void multiplicarMatrices(Matriz2 matriz2) throws InterruptedException {

        if(matriz.length == matriz2.mat[0].length){

            ActorRef elemento = system.actorOf(Props.create(MatElementos.class),"elemento" );
            matrizfinal = new int[matriz.length][matriz2.mat[0].length];

            for(var i=0 ; i<matrizfinal.length ; i++){
                for(var j=0 ; j<matrizfinal[0].length ; j++){
                    elemento.tell(new PasoAElementos(this.matriz,matriz2.mat,i,j), getSelf());
                    //system.stop(elemento);
                }

            }

        }else{
            System.out.println("No se puede multiplicar las matrices");
        }
    }

    public void definirMatrizfinal(DevolverElemento mfinal) {
        matrizfinal[mfinal.fila][mfinal.columna] = mfinal.valor;
        if(mfinal.fila == matriz.length-1 && mfinal.columna == matrizfinal[0].length-1){
            imprimirMatriz();
            system.terminate();
        }
    }

    public void setMatriz(Matriz1 matriz) {
        this.matriz = matriz.mat;
    }

    public void imprimirMatriz(){
        for(int i=0 ; i<matrizfinal.length ; i++){
            for(int j=0 ; j<matrizfinal[0].length ; j++){
                System.out.print(matrizfinal[i][j]+" ");
            }
            System.out.println();
        }
    }
}
