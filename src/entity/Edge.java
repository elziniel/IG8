package project.entity;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Edge {

	private String type;
	private int id;
	private List<Summit> listSummit = new ArrayList();;
    private int startX, startY, endX, endY; 
    private Text poids=null;
    private Color color=null;

    public Edge(String t, int startX, int startY, int endX, int endY) {
        type = t;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public Edge(String t, int startX, int startY, int endX, int endY, String p) {
        type = t;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        poids = new Text(p);
    }
    public int getId(){
    	return this.id;
    }
    public List<Summit> getSummitList(){
    	return this.listSummit;
    }
    public void setId(int i){
    	id=i;
    }
    public Text getPoids(){
    	return poids;
    }
    public void setPoids(String p){
    	poids = new Text();
    	this.poids.setText(p);
        this.poids.setX(this.startX);
        this.poids.setY(this.startY);
    	
    }
    private String getType() {
        return type;
    }

    public void setStartX(int x) {
        this.startX = x;
    }

    public void setStartY(int y) {
        this.startY = y;
    }
    public void setEndX(int x) {
        this.endX = x;
    }

    public void setEndY(int y) {
        this.endY = y;
    }
    public int getStartX() {
        return this.startX;
    }

    public int getStartY() {
        return this.startY;
    }
    public int getEndX() {
        return this.endX;
    }

    public int getEndY() {
        return this.endY;
    }
    public boolean equals(Edge s) {
        return s.getType().equals(type) && s.getStartX() == startX && s.getStartY() == startY && s.getEndX() == endX && s.getEndY() == endY;
    }

    public boolean equals(Node n, int m) {
        if (m == 2)
            return n instanceof Line && (int) (((Line) n).getEndX()) == endX && (int) (((Line) n).getEndY()) == endY;
        else {
            return n instanceof Line && (int) (((Line) n).getStartX()) == startX && (int) (((Line) n).getStartY()) == startY;
        }
   }	
    public void update(Node n) {
        if (n instanceof Line) {
        	    ((Line) n).setStartX(startX);
	            ((Line) n).setStartY(startY);
	            ((Line) n).setEndX(endX);
	            ((Line) n).setEndY(endY);
        }
        
    }

    public String toString() {
        if (poids != null) {
            return type+" "+startX+" "+startY+" "+endX+" "+endY+" "+poids.getText();
        }
        return type+" "+startX+" "+startY+" "+endX+" "+endY+" "+"null";
    }

}
