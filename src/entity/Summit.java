package project.entity;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Summit {

    private String type;
    private int id;
    private boolean visited=false;
    private List<Edge> listEdge = new ArrayList();
    private int oldX, oldY, x, y, size;
    private Color color=null;
    private Text name = null;
    private List<Summit> neighbours = new ArrayList();
    
    public Summit(String t, int x, int y) {
        type = t;
        oldX = x;
        oldY = y;
        this.x = x;
        this.y = y;
    }

    public Summit(String t, int x, int y, String n, int s, Color c) {
        type = t;
        oldX = x;
        oldY = y;
        this.x = x;
        this.y = y;
        name = new Text(n);
        size = s;
        color = c;
    }
    public List<Summit> getNeighbours(){
    	return this.neighbours;
    }
    public boolean getVisited(){
    	return this.visited;
    }
    public void setVisited(boolean bool){
    	this.visited = bool;
    }
    public int getId(){
    	return this.id;
    }
    public void setId(int  i){
    	id =i;
    }
    public List<Edge> getListEdge(){
    	return this.listEdge;
    }
    
    public int getSize(){
    	return this.size;
    }
    public Color getColor(){
    	return this.color;
    }
    public void setColor(Color color){
    	this.color = color;
    }
    public void setSize(int val){
    	this.size=val;
    }
    public String getType() {
        return type;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setOldX(int x) {
        oldX = x;
    }
    public void setOldY(int y) {
        oldY = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Text getName(){
    	return this.name;
    }
    public void setName(String s){
    	name = new Text();
    	this.name.setText(s);
    	this.name.setX(this.x);
    	this.name.setY(this.y);
    }

    public boolean equals(Summit s) {
        return s.getType().equals(type) && s.getX() == x && s.getY() == y;
    }

    public boolean equals(Node n) {
    	if (n instanceof Rectangle) {
            return type.equals("r") && (int) (((Rectangle) n).getX() + size/2) == oldX && (int) (((Rectangle) n).getY() + size/2) == oldY;
        }
        if (n instanceof Circle) {
            return type.equals("c") && (int) (((Circle) n).getCenterX()) == oldX && (int) (((Circle) n).getCenterY()) == oldY;
        }
        return n instanceof Polygon && type.equals("t") && (int) (n.getLayoutX() + size/2) == oldX && (int) (n.getLayoutY() + size/2) == oldY;
    }
        
    public void update(Object n) {
        if (n instanceof Rectangle) {
            ((Rectangle) n).setX(x-size/2);
            ((Rectangle) n).setY(y-size/2);
            ((Rectangle) n).setFill(color);
        }
        if (n instanceof Circle) {
            ((Circle) n).setCenterX(x);
            ((Circle) n).setCenterY(y);
        }
        if (n instanceof Polygon) {
            ((Node) n).setLayoutX(x-size/2);
            ((Node) n).setLayoutY(y-size/2);
        }
    }

    public String toString() {
        if (name != null) {
            return type+" "+x+" "+y+" "+name.getText()+" "+size+" "+color.toString();
        }
        return type+" "+x+" "+y+" null "+size+" "+color.toString();
    }

}
