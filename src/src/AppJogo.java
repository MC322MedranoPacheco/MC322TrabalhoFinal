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
		Leitor leitor = new Leitor();
		MenuView menuV = new MenuView();
		NivelView nivelV = new NivelView();
		MainView mainV = new MainView();
		GameControl teste = GameControl.getInstance();
		mainV.setContentPane(menuV.getContentPane(), null);
		
		menuV.connect(teste);
		
		teste.connect(menuV);
		teste.connect(mainV);
		teste.connect(nivelV);
		teste.connect(montador);
		leitor.connect(teste);
		teste.addKeyListener(leitor);
		teste.connect(leitor);

		
	}
}