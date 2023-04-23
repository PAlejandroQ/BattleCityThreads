package pc1;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ThreadSearch {

    public static void main(String[] args) {
        Tree arbol = new Tree("0-0");
        //Queue<Tree.Node> frontera = new LinkedList<>();
        Stack<Tree.Node> frontera = new Stack<>();
        int H=22;

        Instant start = Instant.now();
        arbol.DFS(arbol.root,"23-0", 23, frontera, H);
        Instant end  = Instant.now();

        Duration tiempo = Duration.between(start, end);

        if(Tree.encontrado==null){
            System.out.println("Nodo no encontrado");
        }
        else{
            System.out.println("Nodo "+ Tree.encontrado.data + " encontrado");
        }
        System.out.println("Tiempo: " + tiempo.toMillis() + " milisegundos");

    }


}
