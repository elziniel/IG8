package project.observer;

import javafx.scene.text.Text;
import project.entity.Edge;
import project.entity.Summit;

public interface Observable {

    void addObserver(Observer o);
    void notifyObserver(Summit s, boolean r);
    void notifyObserver(Text t);
    void notifyObserver(Edge e);
    void notifyObserver(int v);
    void notifyObserver(String n, int s, String c);
    void notifyObserver(Summit s, int v);

}
