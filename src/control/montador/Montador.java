package control.montador;

import model.nivel.IBuildNivel;
import model.nivel.Nivel;
import model.nivel.Sala;

public class Montador implements IMontador{
	IBuildNivel buildnivel;
	public void connect(IBuildNivel connect) {
		this.buildnivel = connect;
	}
	
	public Sala[] constroiNivel(String path, String arquivo) {
		ToolKit tk = ToolKit.start(path,arquivo);
		String modelo[][] = tk.retrieveNivel();
		
		int linha = 0; // Conta em que linha estamos
		for (int i = 0; i < modelo.length; i++) {
			for (int k = 0; k < modelo[i].length; k++)
				System.out.print(modelo[i][k]);
			System.out.println("");
		}
		int numSalas = Integer.parseInt(modelo[0][0]);
		for(int i = 0; i < numSalas; i++) {
			linha++;
			int tamanhoX = Integer.parseInt(modelo[linha][0]);
			int tamanhoY = Integer.parseInt(modelo[linha][1]);
			for (int k = 0; k < tamanhoY; k++) {
				for (int j = 0; j < tamanhoX; j++) {
				// Criamos o terreno		
				}
				linha++;
			}
			linha++;
			int numActors = Integer.parseInt(modelo[linha][0]);
			for (int k = 0; k < numActors; k++) {
				//Cria o actor
				linha++;
			}
		}
		
		
		return null;
	}

}
