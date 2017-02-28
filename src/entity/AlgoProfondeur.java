package project.entity;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class AlgoProfondeur {
	private List<Summit> summitList;
	private List<Edge> edgeList;
	private Summit source;
	private List<Object> steps= new ArrayList();
	private List<Object> oldSteps= new ArrayList();
	public AlgoProfondeur(List<Summit> lSummit, List<Edge> lEdge, Summit src){
		summitList = lSummit;
		edgeList = lEdge;
		source = src;
	}
	Edge getEdgeBetweenSummits(Summit s1, Summit s2){
    	Edge n = null;
    	for (Edge e:s1.getListEdge()){
    		if (s2.getListEdge().contains(e)){
    			return e;
    		}else{
    			return n;
    		}
    	}
    	return null;
    }
	public void launch(Summit src){
		oldSteps.add(src);
		src.setColor(Color.RED);
		src.setSize((int) (src.getSize()*1.5));
		steps.add(src);
		src.setVisited(true);
		for (Summit s:src.getNeighbours()){
			if(s.getVisited() != true){
				oldSteps.add(getEdgeBetweenSummits(src,s));
				steps.add(getEdgeBetweenSummits(src,s));
				launch(s);
			}
		}
	}
	public List<Object> execute(){
		return this.steps;
	}
	public List<Object> oldExecute(){
		return this.oldSteps;
	}
}
