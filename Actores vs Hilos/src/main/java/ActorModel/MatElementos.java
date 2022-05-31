package ActorModel;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class MatElementos extends AbstractActor {

    @Override
    public Receive createReceive(){
        return receiveBuilder().create()
                .match(PasoAElementos.class , this::calcularElemento)
                .build();
    }

    public static Props props() { // Se crea el props que permitira crear un ActorRef con este tipo de clase
        return Props.create(MatElementos.class);
    }

    public void calcularElemento(PasoAElementos elemento){
        int suma = 0;
        for(var i=0 ; i<elemento.mat1.length ;i++){
            suma = suma + (elemento.mat1[elemento.fila][i] * elemento.mat2[i][elemento.columna]);
        }
        getSender().tell(new DevolverElemento(elemento.fila, elemento.columna,suma),getSelf());
    }
}
