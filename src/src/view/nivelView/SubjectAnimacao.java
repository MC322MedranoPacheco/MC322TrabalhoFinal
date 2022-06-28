package view.nivelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class SubjectAnimacao {
	 private ArrayList<ActionListener> listaListener = new ArrayList<ActionListener>(); 

	   public void addActionListener(ActionListener listener) {
	      listaListener.add(listener);
	   }

	   public void removeActionListener(ActionListener listener) {
	      listaListener.remove(listener);
	   }

	   public void notify(ActionEvent event) {
	      for (ActionListener al: listaListener)
	         al.actionPerformed(event);
	   }
}