package pc1;
import java.util.*;

public class Tree {

    public Node root;
    public static Node encontrado;
    public static boolean parallel=false;


    public Tree(String rootData) {
        root = new Node(rootData);
        root.children = new ArrayList<Node>();
    }

    public static class Node {

        public Node(String data){
            this.data=data;
        }
        public Node(String data, Node parent){
            this.data=data;
            this.parent=parent;
        }
        public Node(int level, int order){
            this.level=level;
            this.order=order;
            this.data=Integer.toString(level) + "-"+ Integer.toString(order);
        }
        public String data;
        public int level=0, order=0;
        public Node parent;
        public ArrayList<Node> children = new ArrayList<>();

        public void addSon(String data){
            this.children.add(new Node(data, this));
        }
        public void addSon(Node node){
            this.children.add(node);
        }


    }

    public void BFS(Node r, String key, int maxlevel, Queue<Node> frontera, int numHilos){

        //System.out.println("BUSQUEDA POR BFS");
        Thread hilos[] = new Thread[numHilos];
        Node node = r;

        if(node.data.equals(key)){
            Tree.encontrado=node;
            return;
        }

        for(int i=(node.order)*2; i<(node.order+1)*2;++i){
            node.addSon(new Node(node.level+1, i));
        }


        //Queue<Node> frontera = new LinkedList<>();
        Iterator<Node> it = node.children.iterator();
        while(it.hasNext()){
            frontera.add(it.next());
        }

        Set<Node> reached = new HashSet<Node>();
        reached.add(node);

        while(!frontera.isEmpty()){
            node = frontera.remove();
            if(node.data.equals(key)){
                Tree.encontrado=node;
                return;
            }

            if(Tree.encontrado!=null){
                return;
            }


            //System.out.println("Nodo actual: " + node.data);
            if(node.level<maxlevel){
                for(int i=(node.order)*2; i<(node.order+1)*2;++i){
                    node.addSon(new Node(node.level+1, i));
                }

            }

            Iterator<Node> child = node.children.iterator();

            while(child.hasNext()){
                Node s = child.next();
                //System.out.println("HIJO: " + s.data);
                if(s.data.equals(key)){
                    Tree.encontrado=s;
                    //System.out.println("ENCONTRADO");
                    return;
                    //return node;
                }
                if(!reached.contains(s)){
                    reached.add(s);
                    frontera.add(s);
                }
            }

            if(Math.pow(2, node.level)-1== numHilos && !Tree.parallel){
                Tree.parallel=true;
                for(int i=0; i<numHilos; ++i){
                    //System.out.println("Hilo" + i);
                    Node bifurca = frontera.remove();
                    //System.out.println("BIFURCA EN "+ bifurca.data);
                    hilos[i]=new BFS_Threads(bifurca, key, maxlevel, new LinkedList<>());
                    hilos[i].start();
                }
            }
        }

        if(numHilos>0){
            for(int i=0; i<numHilos;++i){
                try {
                    hilos[i].join();
                    //System.out.println("Hilo "+i+ " termino");
                } catch (InterruptedException ex) {
                    System.out.println("error" + ex);
                }
            }
        }
    }

    public void DFS(Node r, String key, int maxlevel, Stack<Node> frontera, int numHilos){
        //System.out.println("BUSQUEDA POR DFS");
        Node node = r;

        if(node.data.equals(key)){
            Tree.encontrado=node;
            return;
        }

        for(int i=(node.order)*2; i<(node.order+1)*2;++i){
            node.addSon(new Node(node.level+1, i));
        }


        //Stack<Node> frontera = new Stack<>();
        Iterator<Node> it = node.children.iterator();
        while(it.hasNext()){
            frontera.add(it.next());
        }

        Set<Node> reached = new HashSet<Node>();
        reached.add(node);

        while(!frontera.isEmpty()){
            if(Tree.encontrado!=null){
                return;
            }

            node = frontera.pop();
            //System.out.println("Nodo actual: " + node.data);
            if(node.level<maxlevel){
                for(int i=(node.order)*2; i<(node.order+1)*2;++i){
                    node.addSon(new Node(node.level+1, i));
                }

            }

            Iterator<Node> child = node.children.iterator();

            while(child.hasNext()){
                Node s = child.next();
                if(s.data.equals(key)){
                    Tree.encontrado=s;
                    return;
                }
                if(!reached.contains(s)){
                    reached.add(s);
                    frontera.add(s);
                }
            }

            if((node.level+1) == numHilos && !Tree.parallel){
                Thread hilos[] = new Thread[numHilos];
                for(int i=0; i<numHilos; ++i){
                    //System.out.println("Hilo" + i);
                    Node bifurca = frontera.pop();
                    //System.out.println("BIFURCA EN "+ bifurca.data);
                    hilos[i]=new DFS_Threads(bifurca, key, maxlevel, new Stack<>());
                    hilos[i].start();
                }
                Tree.parallel=true;
            }
        }
        return;
    }

    class DFS_Threads extends Thread{
        Tree.Node r;
        String key;
        int maxlevel;
        Stack<Tree.Node> frontera;
        public DFS_Threads(Tree.Node r, String key, int maxlevel, Stack<Tree.Node> frontera){
            this.r=r;
            this.key=key;
            this.maxlevel=maxlevel;
            this.frontera=frontera;
        }

        public void run(){
            DFS(this.r, this.key, this.maxlevel, this.frontera,0);
        }
    }

    class BFS_Threads extends Thread{
        Tree.Node r;
        String key;
        int maxlevel;
        Queue<Tree.Node> frontera;
        public BFS_Threads(Tree.Node r, String key, int maxlevel, Queue<Tree.Node> frontera){
            this.r=r;
            this.key=key;
            this.maxlevel=maxlevel;
            this.frontera=frontera;
        }

        public void run(){
            BFS(this.r, this.key, this.maxlevel, this.frontera,0);
        }
    }

}


