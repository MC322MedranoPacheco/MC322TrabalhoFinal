import control.gameControl.GameControl;
import control.leitor.Leitor;
import control.montador.Montador;
import model.nivel.Nivel;
import utilidades.Posicao;
import view.mainView.MainView;
import view.menuView.MenuView;
import view.nivelView.NivelView;

public class AppJogo {

	public static void main(String[] args) {
		Montador montador = new Montador();
		Nivel nivel = montador.constroiNivel(null, "Nivel0");
		Leitor leitor = new Leitor();
		
		MenuView menuV = new MenuView();
		NivelView nivelV = new NivelView();
		MainView mainV = new MainView();
		nivelV.geraJFrame(14, 14, nivel.salas[0], leitor);
		
		GameControl teste = GameControl.getInstance();
	
		mainV.setContentPane(menuV.getContentPane(), null);
		menuV.connect(teste);
		
		try {Thread.sleep(10);}catch(Exception exc) {}
		
		mainV.setContentPane(nivelV.getContentPane(), nivelV.getJFrame().getKeyListeners()[0]);
		
		teste.connect(mainV);
		teste.connect(nivelV);
		teste.connect(montador);
		teste.connect(nivel.salas[0].getCelula(new Posicao(0,0)).getActor());
		nivel.connect(teste);
		leitor.connect(teste);
		teste.addKeyListener(leitor);
		leitor.connect(nivelV.getPersonagem());
		
		nivel.salas[0].getCelula(new Posicao(6,3));
				
	}
}