package src.control.montador;

import src.model.autor.Actor;
import src.model.autor.Personagem;
import src.model.nivel.IBuildNivel;
import src.model.nivel.Nivel;
import src.model.nivel.Sala;
import src.model.nivel.Terra;
import src.model.nivel.Terreno;

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
		Sala[] salas = new Sala[numSalas];
		
		for(int i = 0; i < numSalas; i++) {
			linha++;
			int tamanhoX = Integer.parseInt(modelo[linha][0]);
			int tamanhoY = Integer.parseInt(modelo[linha][1]);
			salas[i] = new Sala(tamanhoX, tamanhoY);
			linha++;
			
			for (int k = 0; k < tamanhoY; k++) {
				for (int j = 0; j < tamanhoX; j++) {
					switch (modelo[linha][j]) {
					case "T":
						Terreno terreno = new Terra();
						salas[i].adicionaTerreno(j, k, terreno);
					default:
						// Se nn passar, dar erro
					}	
				}
				linha++;
			}
			int numActors = Integer.parseInt(modelo[linha][0]);
			linha++;
			for (int k = 0; k < numActors; k++) {
				int posX = Integer.parseInt(modelo[linha][0]);
				int posY = Integer.parseInt(modelo[linha][1]);
				switch (modelo[linha][2]) {
				case "P":
					System.out.println("Passou aqui");
					Actor ator = new Personagem();
					salas[i].adicionaActor(posX - 1, posY - 1, ator); // Assumindo que a posicao 1,1 seja a posicao da matriz 0,0
				default:
					// Se nn passar, dar erro
				}	
				linha++;
			}
		}
		
		
		return salas;
	}

}
