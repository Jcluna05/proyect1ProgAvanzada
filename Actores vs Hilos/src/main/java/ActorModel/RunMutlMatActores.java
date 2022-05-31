package ActorModel;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class RunMutlMatActores {

    public static int[][] mat1 = {
            {1,2,3},{4,5,6}
    };
    static Matriz1 matriz1 = new Matriz1(mat1);

    public static int[][] mat2 = {
            {5,-1},{1,0},{-2,3}
    };
    static Matriz2 matriz2 = new Matriz2(mat2);

    public static ActorSystem system = ActorSystem.create("System");

    public static void main(String[] args) throws InterruptedException {

        ActorRef rMultiplicadormat = system.actorOf(MatrizActors.props(),"rmultiplicadormat");
        rMultiplicadormat.tell(matriz1 , ActorRef.noSender());
        Thread.sleep(1000);
        rMultiplicadormat.tell(matriz2 , ActorRef.noSender());

        system.terminate();
    }

}

