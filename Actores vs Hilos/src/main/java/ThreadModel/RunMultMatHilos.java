package ThreadModel;

public class RunMultMatHilos {
    public static void main(String[] args) throws InterruptedException {

        int[][] mat1 = {
                {1,2,3},{4,5,6}
        };
        Matriz primeraMatriz = new Matriz(mat1);

        int[][] mat2 = {
                {5,-1},{1,0},{-2,3}
        };
        Matriz segundaMatriz = new Matriz(mat2);

        Matriz resultadomultiplicacion = primeraMatriz.multiplicacionHilos(segundaMatriz);

        for(var i=0 ; i<resultadomultiplicacion.matriz.length ; i++){
            for(var j=0 ; j<resultadomultiplicacion.matriz[0].length ; j++){
                System.out.print(resultadomultiplicacion.matriz[i][j]+" ");
            }
            System.out.println();
        }
    }
}
