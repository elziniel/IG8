package project.view;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import project.controller.AbstractController;
import javafx.scene.layout.BorderPane;
import project.entity.Edge;
import project.entity.Summit;
import project.listener.MouseDraggedListener;
import project.listener.MousePressedListener;
import project.listener.MouseReleasedListener;
import project.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class View extends BorderPane implements Observer {

    private List<Node> nodeList;
    private IGSummit igSummit;
    private static Summit sommet;
    private static List<Node> edgeList;
    public View(AbstractController c) {
        nodeList = new ArrayList<Node>();

        IGMenu menu = new IGMenu(c);
        this.setTop(menu);

        igSummit = new IGSummit(c);
        this.setLeft(igSummit);

        IGParcours igParcours = new IGParcours(c);
        igParcours.setAlignment(Pos.CENTER);
        this.setBottom(igParcours);

        setCenter(new Rectangle(600, 500, Color.WHITE));

        IGEdge igEdge = new IGEdge(c);
        this.setRight(igEdge);
        this.setOnMousePressed(new MousePressedListener(c));
        this.setOnMouseDragged(new MouseDraggedListener(c));
        this.setOnMouseReleased(new MouseReleasedListener(c));
    }

    private void refresh() {
    	this.getChildren().removeAll(nodeList);
    	this.getChildren().addAll(nodeList);
    }

    @Override
    public void update(Summit s, List<Edge> lEdge, boolean r) {
    	if (!r) {
	    	int size=s.getSize();
	    	int [] decalage = new int[2];
	    	for (Node n : nodeList) {
	            if (s.equals(n)) {
	                decalage = calcDecalage(n, s);
	                if(sommet != s){
	                    sommet = s;
	                    edgeList = getEdgeList(n, nodeList);
	                }
	                check(n, edgeList, decalage,lEdge);
	                s.update(n);
	                refresh();
	                return;
	            }
	    	}
    	
            switch (s.getType().charAt(0)) {
                case 'r':
                    Rectangle rectangle = new Rectangle(size, size);
                    rectangle.setX(s.getX() - size/2);
                    rectangle.setY(s.getY() - size/2);
                    rectangle.setFill(s.getColor());
                    nodeList.add(rectangle);
                    break;
                case 'c':
                    Circle circle = new Circle(size/2);
                    circle.setCenterX(s.getX());
                    circle.setCenterY(s.getY());
                    circle.setFill(s.getColor());
                    nodeList.add(circle);
                    break;
                case 't':
                    Polygon triangle = new Polygon(size/2, 0, 0, size, size, size);
                    triangle.setLayoutX(s.getX() - size/2);
                    triangle.setLayoutY(s.getY() - size/2);
                    triangle.setFill(s.getColor());
                    nodeList.add(triangle);
                    break;
            }
            refresh();
        }
        else {
            for (Node n : nodeList) {
                if (s.equals(n)) {
                    nodeList.remove(n);
                    this.getChildren().remove(n);
                    refresh();
                    return;
                }
            }
        }
    }

	@Override
	public void update(Text t, int x, int y) {		
		for (Node n : nodeList) {
			if (t.equals(n)) {
            	((Text) n).setX(x);
            	((Text) n).setY(y);
                refresh();
                return;
            }
        }
		Text tx;
        tx = t;
        nodeList.add(tx);
		refresh();
	}

	@Override
	public void update(Edge e, int m){
		for (Node n : nodeList) {
			if (e.equals(n,m)) {
				e.update(n);
				refresh();
                return;
            }
        }
		Line l = new Line (e.getStartX(), e.getStartY(), e.getEndX(), e.getEndY());
		nodeList.add(l);
		refresh();
	}

	 @Override
	    public void update(int v) {
	        igSummit.setSizeLabel("Size : "+v);
	    }

    @Override
    public void update(String n, int s, String c) {
        igSummit.setName(n);
        igSummit.setSizeLabel("Size : "+s);
        igSummit.setSize(s);
        igSummit.setColor(c);
    }

    @Override
    public void update(Summit s, int v) {
        for (Node n : nodeList) {
            if (s.equals(n)) {
                getChildren().remove(n);
                if (n instanceof Rectangle) {
                    System.out.println("true");
                    ((Rectangle) n).setWidth(v);
                    ((Rectangle) n).setHeight(v);
                    //((Rectangle) n).setX(((Rectangle) n).getX() - v/2);
                    //((Rectangle) n).setY(((Rectangle) n).getY() - v/2);
                    getChildren().add(n);
                    refresh();
                }
            }
        }
    }

    public void check(Node n,List<Node> l, int[] coo, List<Edge> lEdge){
		Line line;
		Bounds b = n.getBoundsInParent();
		for (Node e : l) {
				line = (Line) e;
				if (line.getStartX() <= b.getMaxX() && line.getStartX() >= b.getMinX() && line.getStartY() <= b.getMaxY() && line.getStartY() >= b.getMinY()){
					for (Edge ed : lEdge) {
						if (line.getStartX() == ed.getStartX() && line.getStartY() == ed.getStartY()){
							ed.setStartX((int)((Line) e).getStartX()-coo[0]);
							ed.setStartY((int)((Line) e).getStartY()-coo[1]);
						}
		            }
					((Line) e).setStartX(((Line) e).getStartX()-coo[0]);
		            ((Line) e).setStartY(((Line) e).getStartY()-coo[1]);		            
				}else if(line.getEndX() <= b.getMaxX() && line.getEndX() >= b.getMinX() && line.getEndY() <= b.getMaxY() && line.getEndY() >= b.getMinY()){		            
					for (Edge ed : lEdge) {
						if (line.getEndX() == ed.getEndX() && line.getEndY() == ed.getEndY()){
							ed.setEndX((int)((Line) e).getEndX()-coo[0]);
							ed.setEndY((int)((Line) e).getEndY()-coo[1]);
						}
		            }
					((Line) e).setEndX(((Line) e).getEndX()-coo[0]);
		            ((Line) e).setEndY(((Line) e).getEndY()-coo[1]);
		            
				}
			}
	}

    private List<Node> getEdgeList(Node n, List<Node> l){
		Line line;
		List<Node> result = new ArrayList<Node>();
		Bounds b = n.getBoundsInParent();
		for (Node e : l) {
			if (e instanceof Line){
				line = (Line) e;
				if (line.getStartX() <= b.getMaxX() && line.getStartX() >= b.getMinX() && line.getStartY() <= b.getMaxY() && line.getStartY() >= b.getMinY()){
					result.add(e);
				}
                else if(line.getEndX() <= b.getMaxX() && line.getEndX() >= b.getMinX() && line.getEndY() <= b.getMaxY() && line.getEndY() >= b.getMinY()){
		            result.add(e);
				}
			}
        }
		return result;
	}

    public List<Node> getNodeList(Node n, List<Node> l){
    	Line line;
		List<Node> result = new ArrayList();
		Bounds b;
		for (Node e : l) {
			
			if (!(e instanceof Line)){
				line = (Line) n;
				b=e.getBoundsInParent();
				if (line.getStartX() <= b.getMaxX() && line.getStartX() >= b.getMinX() && line.getStartY() <= b.getMaxY() && line.getStartY() >= b.getMinY()){
					result.add(e);
				}else if(line.getEndX() <= b.getMaxX() && line.getEndX() >= b.getMinX() && line.getEndY() <= b.getMaxY() && line.getEndY() >= b.getMinY()){
		            result.add(e);
				}
			}
        }
		return result;
    }
	private int[] calcDecalage(Node n, Summit s){
		int[] tab = new int[2];
		if (n instanceof Rectangle) {
			tab[0]=((int) ((Rectangle) n).getX() - s.getX() + s.getSize()/2);
        	tab[1]=((int) ((Rectangle) n).getY() - s.getY() + s.getSize()/2);
        }
		if (n instanceof Circle) {
        	tab[0]=((int) ((Circle) n).getCenterX() - s.getX());
        	tab[1]=((int) ((Circle) n).getCenterY() - s.getY());
        }
        if (n instanceof Polygon) {
        	tab[0]=((int) (n.getLayoutX() - s.getX() + s.getSize()/2));
        	tab[1]=((int) (n.getLayoutY() - s.getY() + s.getSize()/2));
        }
		return tab;
	}
	
	@Override
	public void update(List<Summit> lSummit, List<Edge> lEdge){
		List<Node> l = new ArrayList();
		for (Summit s : lSummit) {
			for (Node n : nodeList) {
				if (s.equals(n)){
					l=getEdgeList(n,nodeList);
					for (Node li : l) {
						for (Edge e : lEdge) {
							if (e.equals(li,1)){
								s.getListEdge().add(e);
							}else if (e.equals(li,1)){
								s.getListEdge().add(e);
							}
							
				        }
			        }
				}
	        }
        }
		
		for (Edge e : lEdge) {
			for (Node n : nodeList) {
				if (e.equals(n,1)){
					l=getNodeList(n,nodeList);
					for (Node li : l) {
						for (Summit s : lSummit) {
							if (s.equals(li)){
								e.getSummitList().add(s);
							}
				        }
			        }
				} else if (e.equals(n,2)){
					l=getNodeList(n,nodeList);
					for (Node li : l) {
						for (Summit s : lSummit) {
							if (s.equals(li)){
								e.getSummitList().add(s);
							}
				        }
			        }
				}
	        }
        }
	}
}
