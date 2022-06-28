package view.nivelView;

import model.autor.ActorSubjectView;

public interface ObserverActor {
	public void update(String direcao);
	public void setSubject(ActorSubjectView sub);
	public void setBounds(int i, int j, int k, int l);
}