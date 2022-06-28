package utilidades;

public interface Subject {
	public void registrar(Observer obj);
	public void excluirRegistro(Observer obj);
	
	public void notificarObservadores();
	public Object getUpdate(Observer obj);
}
