package control.montador;

import model.autor.Actor;
import model.autor.interactiveObjects.Caixa;
import model.autor.personagens.Garoto;
import model.nivel.IBuildNivel;
import model.nivel.Nivel;
import model.nivel.Sala;
import model.terreno.Gelo;
import model.terreno.Terra;
import model.terreno.Terreno;

public class Montador implements IMontador{
	IBuildNivel buildnivel;
	public void connect(IBuildNivel connect) {
		this.buildnivel = connect;
	}
	
	public Nivel constroiNivel(String path, String arquivo) {
		Nivel nivel = new Nivel();
		ToolKit tk = ToolKit.start(path,arquivo);
		String modelo[][] = tk.retrieveNivel();
		
		int linha = 0; // Conta em que linha estamos
		for (int i = 0; i < modelo.length; i++) {
			for (int k = 0; k < modelo[i].length; k++)
				System.out.print(modelo[i][k]);
			System.out.println("");
		}
		int numSalas = Integer.parseInt(modelo[0][0]);
		nivel.salas = new Sala[numSalas];
		
		for(int i = 0; i < numSalas; i++) {
			linha++;
			int tamanhoX = Integer.parseInt(modelo[linha][0]);
			int tamanhoY = Integer.parseInt(modelo[linha][1]);
			nivel.salas[i] = new Sala(tamanhoX, tamanhoY);
			linha++;
			
			for (int k = 0; k < tamanhoY; k++) {
				for (int j = 0; j < tamanhoX; j++) {
					Terreno terreno;
					switch (modelo[linha][j]) {
					case "T":
						terreno = new Terra(j, k);
						terreno.setSala(i);
						nivel.salas[i].adicionaTerreno(j, k, terreno);
						terreno.connect(nivel);
						break;
					case "G":
						terreno = new Gelo(j,k);
						terreno.setSala(i);
						nivel.salas[i].adicionaTerreno(j, k, terreno);
						terreno.connect(nivel);
						break;
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
				String stringAtor = modelo[linha][2];
				switch (stringAtor) {
					case "P":
						System.out.println("Criou garoto");
						Actor ator = new Garoto(posX -1 , posY -1);
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator); // Assumindo que a posicao 1,1 seja a posicao da matriz 0,0
						break;
					case "C":
						System.out.println("Criou caixa");
						Actor ator1 = new Caixa(posX -1 , posY -1);
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator1);
						break;
					default:
						// Se nn passar, dar erro
				}	
				linha++;
			}
			
			// falta adicionar objetos n atores: chaves , itens etc
		}
		
		
		return nivel;
	}

}
