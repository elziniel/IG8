package project.entity;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import project.model.Model;

public class Parcours extends Thread{
List<Object> lObject = new ArrayList<Object>();
Model model=null;
public Parcours(List<Object> list, Model m){
	lObject = list;
	model=m;
}
public synchronized void run(){
for (final Object e:lObject){
	if (e instanceof Summit){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				model.notifyObserver(((Summit) e),false);
			}
		});		
		try {
			wait(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
}
}
