package project.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Algorithm {
	/*
	    private  List<Summit> summitList;
	    private List<Edge> edgeList;// le graphe de travail
	    private int n; // nombre de sommets de g
	    private Summit source; // source du plus court chemin recherche
	    private Summit dest; // destination du plus court chemin recherche
	    private int[] dist; // distances a la source
	    private int[] pred; // predecesseur de chaque sommet dans le parcours
	    private boolean[] settled; // sommets traites par l'algorithme
	    private PriorityQueue<Summit> unsettled; // sommets a traiter
	    private int steps; // compteur du nombre d'etapes

	    // constructeur
	    public Algorithm (List<Summit> lSummit,List<Edge> lEdge, Summit source, Summit dest) {
		this.summitList = lSummit;
		this.edgeList = lEdge;
		this.source = source;
		this.dest = dest;
		dist = new int[n];
		pred = new int[n];
		settled = new boolean[n];
		for (int i = 0; i < n; i++) {
		    dist[i] = Integer.MAX_VALUE;
		    pred[i] = -1;
		    settled[i] = false;
		}
		dist[source.getId()] = 0;
		pred[source.getId()] = source.getId();
		unsettled = new PriorityQueue<Summit>();
		unsettled.add(source);
		steps = 0;
	    }
	    Edge getEdgeBetweenSummits(int x, int y){
	    	Edge n = null;
	    	for (Edge e:summitList.get(x).getListEdge()){
	    		if (summitList.get(y).getListEdge().contains(e)){
	    			return e;
	    		}else{
	    			return n;
	    		}
	    	}
	    	return null;
	    }
	    int valuePoids (int x, int y){	    	
	    	if (summitList.get(x).getNeighbours().contains(y)){
	    		return Integer.parseInt(getEdgeBetweenSummits(x,y).getPoids().getText());
	    	}else{
	    		return 0;
	    	}
	    } 
	    // mise a jour de la distance, de la priorite, et du predecesseur d'un sommet
	    void update(int y, int x) {
		int newDist = dist[x] + valuePoids(x,y);
		// 
		if (dist[y] > newDist) {
		    pred[y] = x;
		    dist[y] = newDist;
		    unsettled.add(summitList.get(y));
		    //ajouter un pas
		}
	    }
	    // trouve le prochain sommet de unvisited non traite
	    int nextNode() {
		while (!unsettled.isEmpty()) {
		    int x = unsettled.poll().getId();
		    if (!settled[x]) return x;
		}
		return -1;
	    }
	    // une etape de l'algorithme Dijkstra
	    int oneStep() {
		steps++;
		int x = nextNode();
		if (x == -1) return -1;
		settled[x] = true;
		//g.drawSettledPoint(f, x); faire un truc		
		for (int y : summitList.get(x).getNeighbours()) //parcourir les successeurs
		    update(y, x);
		return x;
	    }
	    // algorithme de Dijkstra complet
	    int compute() {
		int x = source.getId();
		while (x != dest.getId() && x != -1)
		    x = oneStep();
		return (x == -1 ? -1 : dist[dest.getId()]);
	    }
	    
	*/
}
