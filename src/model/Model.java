package project.model;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.entity.Edge;
import project.entity.Parcours;
import project.entity.Summit;
import project.entity.AlgoProfondeur;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    public Model(Stage s) {
        super(s);
    }

    @Override
    public void putSummit(String s, int x, int y, String name, int size, Color color) {
        Summit summit = new Summit(s, x, y);
        summit.setSize(size);
        summit.setColor(color);
        if (name != null) {
        	summit.setName(name);
        	Text t = summit.getName();
        	putText(t, (int)(t.getX()-t.getLayoutBounds().getWidth()/2), (int)(t.getY()-(size/2+2)));
        }
        listSummit.add(summit);
        notifyObserver(summit, false);
    }

    @Override
    public void putText(Text t, int x, int y){
    	t.setX(x);
    	t.setY(y);
    	listText.add(t);
    	notifyObserver(t);
    }

    @Override
    public void dragSummit(Summit s, int oldX, int oldY, int x, int y) {
        s.setOldX(oldX);
        s.setOldY(oldY);
        s.setX(x);
        s.setY(y);
        if (s.getName() != null) {
        	dragText(s.getName(),(int) s.getName().getX(), (int) s.getName().getY(), (int) (x-s.getName().getLayoutBounds().getWidth()/2), y-(s.getSize()/2+2));
        }
        notifyObserver(s, false);
    }
    
    @Override
    public void dragText(Text t, int oldX, int oldY, int x, int y) {
    	t.setX(oldX);
        t.setY(oldY);
        this.x = x;
        this.y = y;
        notifyObserver(t);
    }

    @Override
    public void drawEdge(Edge edge,int x, int y, int m){
		for(Edge e : listEdge) {
            if (m==2){
            	if (e.getEndX() >= edge.getEndX()-5 && e.getEndX() <= edge.getEndX()+5 && e.getEndY() >= edge.getEndY()-5 && e.getEndY() <= edge.getEndY()+5) {
                	e.setStartX(x);
                    e.setStartY(y);
                    mode=2;
                    notifyObserver(e);
                    mode=1;
                    break;
                }
            }else{
            	if (e.getStartX() >= edge.getStartX()-5 && e.getStartX() <= edge.getStartX()+5 && e.getStartY() >= edge.getStartY()-5 && e.getStartY() <= edge.getStartY()+5) {
                	e.setEndX(x);
                    e.setEndY(y);
                    notifyObserver(e);
                    break;
                }
            }
			 
        } 
    }

    @Override
    public void setEdge(Edge e){
    	Edge edge = new Edge("n", e.getStartX(), e.getStartY(),e.getEndX(),e.getEndY());
        //summit.setSize(size);
        //summit.setColor(color);
        if (e.getPoids()!=null) {
        	edge.setPoids(e.getPoids().getText());
        	Text t = edge.getPoids();
        	putText(t,(e.getStartX()+e.getEndX())/2+10,(e.getStartY()+e.getEndY())/2+10);
        }
        listEdge.add(edge);
    }

    @Override
    public void setSize(int v) {
        notifyObserver(v);
    }

    @Override
    public void setSize(Summit s, int v) {
        for (Summit summit : listSummit) {
            if (summit.equals(s)) {
                summit.setSize(v);
                notifyObserver(summit, false);
            }
        }
    }

    @Override
    public void selectSummit(Summit s) {
        for (Summit summit : listSummit) {
            if (summit.equals(s)) {
                if (s.getName() != null) {
                    notifyObserver(s.getName().getText(), s.getSize(), s.getColor().toString());
                }
                else {
                    notifyObserver("", s.getSize(), s.getColor().toString());
                }
            }
        }
    }

    @Override
    public void openGraph() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Graph");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                List<Summit> tmpSummit = new ArrayList<Summit>();
                List<Edge> tmpEdge = new ArrayList<Edge>();
                while ((line = br.readLine()) != null) {
                    String[] summit = line.split(" ");
                    if (summit[0].equals("r") || summit[0].equals("c") || summit[0].equals("t")) {
                        tmpSummit.add(new Summit(summit[0], Integer.parseInt(summit[1]), Integer.parseInt(summit[2]), summit[3], Integer.parseInt(summit[4]), Color.web(summit[5])));
                    }
                    else if (summit[0].equals("n")) {
                        tmpEdge.add(new Edge(summit[0], Integer.parseInt(summit[1]), Integer.parseInt(summit[2]), Integer.parseInt(summit[3]), Integer.parseInt(summit[4]), summit[5]));
                    }
                }
                listSummit = tmpSummit;
                listEdge = tmpEdge;
                for (Summit s : listSummit) {
                    notifyObserver(s, false);
                }
                for (Edge e : listEdge) {
                    notifyObserver(e);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveGraph() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Graph");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (Summit s : listSummit) {
                    bw.write(s.toString());
                    bw.newLine();
                }
                for (Edge e : listEdge) {
                    bw.write(e.toString());
                    bw.newLine();
                }
                bw.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void constructGraph(){
    	int i=0,j=0;
    	for (Summit s : listSummit) {
            s.setId(i);
            i++;
        }
        for (Edge e : listEdge) {
        	e.setId(j);
        	j++;
        }
        notifyObserver(listSummit, listEdge);
        
        for (Summit s:listSummit){
        	for(Edge e:s.getListEdge()){
        		for (Summit sm :e.getSummitList()){
        			if (sm != s){
        				s.getNeighbours().add(sm);
        			}
        		}
        	}
        }
    }
    
    @Override
    public void removeSummit(Summit s) {
        listSummit.remove(s);
        notifyObserver(s, true);
    }

	@Override
	public void launchPep(Summit s) {
		constructGraph();
		pep = new AlgoProfondeur(listSummit, listEdge,s);
		pep.launch(s);
		steps=pep.execute();
		oldSteps=pep.oldExecute();
	}

	@Override
	public void updateParcours() {
		p = new Parcours(steps,oldSteps,this);
		p.execute();				
	}
	@Override
	public void pause(){
		p.pause();
	}
	@Override
	public void stepPlus(){
		p.stepPlus();
	}
	@Override
	public void stepMinus(){
		p.stepMinus();
	}

}
