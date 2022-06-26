package testando;

import control.gameControl.GameControl;
import control.montador.Montador;
import model.nivel.INivel;
import model.nivel.Nivel;
import model.nivel.Sala;
import utilidades.Posicao;

public class teste {

	public static void main(String[] args) {
		Montador montador = new Montador();
		Nivel nivel = montador.constroiNivel(null, "NivelTeste");
		GameControl teste = GameControl.getInstance();
		teste.connect(nivel.salas[0].getCelula(new Posicao(0,0)).getActor());
		nivel.connect(teste);
		nivel.salas[0].getCelula(new Posicao(0,0)).getActor().connect(nivel);
		nivel.salas[0].getCelula(new Posicao(1,0)).getActor().connect(nivel);
		teste.acao("s");
		teste.acao("d");
		teste.acao("w");
	}

}
