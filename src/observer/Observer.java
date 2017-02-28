package project.observer;

import project.entity.Edge;
import project.entity.Summit;

import java.util.List;

import javafx.scene.text.Text;

public interface Observer {

    void update(Summit s, List<Edge> lEdge, boolean r);
    void update(Text t,int x, int y);
    void update(Edge e, int mode);
    void update(List<Summit> lSummit, List<Edge> lEdge);
    void update(int v);
    void update(String n, int s, String c);
    void update(Summit s, int v);
}
