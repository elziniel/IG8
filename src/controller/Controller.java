package project.controller;

import project.entity.Summit;
import project.entity.Edge;
import project.model.AbstractModel;

public class Controller extends AbstractController {

    public Controller(AbstractModel m) {
        super(m);
    }

    @Override
    public void setPressAction(int x, int y) {
        this.x = x;
        this.y = y;
        if (action.equals("e")){
        	for(Edge e : model.listEdge) {
                if (e.getStartX() >= x-5 && e.getStartX() <= x+5 && e.getStartY() >= y-5 && e.getStartY() <= y+5) {
                	sens = "debut";
                	edge = e;
                	old = true;
                	break;
                } else if (e.getEndX() >= x-5 && e.getEndX() <= x+5 && e.getEndY() >= y-5 && e.getEndY() <= y+5) {
                	sens = "fin";
                	edge = e;
                	old = true;
                	break;
                }
            }
        	if (!old){
        		edge = new Edge("n", x, y, x, y);
        		if (poids!=null)
        			edge.setPoids(poids);
        		model.setEdge(edge);
        	}
        	old=false;
        }
        else {
            for(Summit s : model.listSummit) {
	            if (s.getX() >= x-s.getSize()/2 && s.getX() <= x+s.getSize()/2 && s.getY() >= y-s.getSize()/2 && s.getY() <= y+s.getSize()/2) {
	                summit = s;
	                this.x = summit.getX();
	                this.y = summit.getY();
	                break;
	            }
	        }
        }
    }

    @Override
    public void setReleaseAction(int x, int y) {
        if (!drag) {
            if (action != null) {
                if (action.equals("r") || action.equals("c") || action.equals("t")) {
                    if (summit != null) {
                        action = summit.getType();
                        model.selectSummit(summit);
                    }
                    else {
                        model.putSummit(action, x, y, name, val, color);
                    }
                }
            }
        }
        else {
            drag = false;
        }
    }

    @Override
    public void setDragAction(int x, int y) {
    	if (action.equals("e")){
    		if (sens.equals("debut")){
    			model.drawEdge(edge, x, y, 1);
    		} else {
    			model.drawEdge(edge, x, y, 2);
    		}
    		
    	}
    	else {
    		if (summit != null) {
	            drag = true;
	            model.dragSummit(summit, this.x, this.y, x, y);
	            this.x = x;
	            this.y = y;
	        }
    	}
    }

	@Override
	public void setOpenAction() {
        model.openGraph();
	}

	@Override
	public void setSaveAction() {
        model.saveGraph();
	}

	@Override
	public void setLaunchAction() {
		model.constructGraph();
		model.launchPep(this.summit);
		model.updateParcours();
	}

	@Override
	public void removeAction() {
        if (!drag) {
            if (summit != null) {
                model.removeSummit(summit);
                summit = null;
            }
        }
        else {
            drag = false;
        }
	}

	@Override
	public void setSlider(int v) {
		val = v;
        if (summit != null) {
            model.setSize(summit, v);
        }
        model.setSize(v);
	}

	@Override
	public void setParcours(String a) {
		if (a.equals("pause")){
			model.pause();
		} else if (a.equals("retour")){
			model.stepMinus();
		} else if (a.equals("avance")){
			model.stepPlus();
		}
			
		 
		
	}

}
