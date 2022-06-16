package model.nivel;

import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class Nivel implements INivel, Observer{
	private Sala salas[];
	private Subject topico;

	public void mover(int sala, Posicao posicaoOrigem, Posicao posicaoFinal, String actor) { //que coisa feia
		salas[sala].mover(posicaoOrigem, posicaoFinal, actor);
	}

	@Override
	public void update() {
		 Posicao[] movimentos = (Posicao[]) topico.getUpdate(this); 
		 // implementaremos aqui as consequencias de se mover para aqui
		
	}

	@Override
	public void setSubejct(Subject sub) {
		this.topico = sub;
		
	}

	
	
	
	
	
}
