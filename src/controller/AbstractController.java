package project.controller;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import project.entity.Summit;
import project.entity.Edge;
import project.model.AbstractModel;

public abstract class AbstractController {

    AbstractModel model;
    String action = "r", name, poids, sens = "debut";
    public int val = 10;
    Color color = Color.BLACK;
    int x, y;
    Summit summit;
    Edge edge;
    boolean drag = false, old=false;
    
    public AbstractController(AbstractModel m) {
        model = m;
    }		

    public void setAction(String a) {
        action = a;
    }
    public abstract void setParcours(String a);
    
    public void setAction(String a, String s){
    	if (a.equals("n"))
    		name = s;
    	if (a.equals("p"))
    		poids = s;
        if (a.equals("c")) {
            color = Color.web(s);
        }
    }
    public abstract void setPressAction(int x, int y);

    public abstract void setReleaseAction(int x, int y);

    public abstract void setDragAction(int x, int y);

    public abstract void setOpenAction();

    public abstract void setSaveAction();

    public abstract void setLaunchAction();

    public abstract void removeAction();

    public abstract void setSlider(int v);

}
