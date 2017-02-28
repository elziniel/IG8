package project.model;

import javafx.stage.Stage;
import project.entity.Edge;
import project.entity.Parcours;
import project.entity.Summit;
import project.observer.Observable;
import project.observer.Observer;
import project.entity.AlgoProfondeur;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class AbstractModel implements Observable {

    private List<Observer> listObserver = new ArrayList<Observer>();
    public List<Summit> listSummit = new ArrayList<Summit>();
    public List<Object> steps = new ArrayList<Object>();
    public List<Object> oldSteps = new ArrayList<Object>();
    Parcours p;
    List<Text> listText = new ArrayList<Text>();
    public List<Edge> listEdge = new ArrayList<Edge>();
    public AlgoProfondeur pep;
    int x, y, mode = 1;
    Stage stage;

    AbstractModel(Stage s) {
        stage = s;
    }

    public void addObserver(Observer o) {
        listObserver.add(o);
    }

    public void removeObserver() {
        listObserver = new ArrayList<Observer>();
    }

    public void notifyObserver(Summit s, boolean r) {
        for (Observer o : listObserver) {
        	o.update(s, listEdge,r);
        }
    }

    public void notifyObserver(Text t) {
    	
        for (Observer o : listObserver) {
            o.update(t, x, y);
        }
    }

    public void notifyObserver(Edge e) {
    	
        for (Observer o : listObserver) {
            o.update(e, mode);
        }
    }

    public void notifyObserver(List<Summit> lSummit, List<Edge> lEdge){
        for (Observer o : listObserver) {
            o.update(lSummit,lEdge);
        }
    }

    public void notifyObserver(int v) {

        for (Observer o : listObserver) {
            o.update(v);
        }
    }

    public void notifyObserver(String n, int s, String c) {
        for (Observer o : listObserver) {
            o.update(n, s, c);
        }
    }

    public void notifyObserver(Summit s, int v) {
        for (Observer o : listObserver) {
            o.update(s, v);
        }
    }

    public abstract void putText(Text t, int x,int y);
    public abstract void putSummit(String s, int x, int y, String name, int size, Color color);

    public abstract void dragSummit(Summit s, int oldX, int oldY, int x, int y);
    public abstract void dragText(Text t, int oldX, int oldY,int x, int y);
    public abstract void drawEdge(Edge e,int x, int y, int mode);
    public abstract void setEdge(Edge e);
    public abstract void constructGraph();
    public abstract void launchPep(Summit s);
    public abstract void updateParcours();
    public abstract void setSize(int v);
    public abstract void setSize(Summit s, int v);
    public abstract void selectSummit(Summit s);
    public abstract void pause();
	public abstract void stepPlus();
	public abstract void stepMinus();
    public abstract void openGraph();
    public abstract void saveGraph();

    public abstract void removeSummit(Summit s);
}
