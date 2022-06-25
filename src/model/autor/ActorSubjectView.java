package model.autor;

import view.nivelView.ObserverActor;

public interface ActorSubjectView {
	public void registrarView(ObserverActor obj);
	public void excluirRegistroView(ObserverActor obj);
	public void notificarObservadoresView(String string);
}