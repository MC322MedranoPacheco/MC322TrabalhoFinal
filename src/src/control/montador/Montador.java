package control.montador;

import control.gameControl.SalaChanger;
import exceptions.MontadorException;
import model.autor.Actor;
import model.autor.interactiveObjects.Caixa;
import model.autor.interactiveObjects.Computador;
import model.autor.interactiveObjects.ConnectionKeyPorta;
import model.autor.interactiveObjects.KeyPorta;
import model.autor.interactiveObjects.LaserMaquina;
import model.autor.interactiveObjects.ObserverPorta;
import model.autor.interactiveObjects.Parede;
import model.autor.personagens.Garoto;
import model.item.Chave;
import model.item.Gema;
import model.item.Item;
import model.nivel.IBuildNivel;
import model.nivel.Nivel;
import model.nivel.Sala;
import model.terreno.Gelo;
import model.terreno.Pedra;
import model.terreno.PlacaDePressao;
import model.terreno.Terra;
import model.terreno.Terreno;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class Montador implements IMontador{
	IBuildNivel buildnivel;
	public void connect(IBuildNivel connect) {
		this.buildnivel = connect;
	}

	public Nivel constroiNivel(String path, String arquivo, SalaChanger changer) {
		Nivel nivel = new Nivel();
		ToolKit tk = ToolKit.start(path,arquivo);
		String modelo[][] = tk.retrieveNivel();
		
		
		int linha = 0; // Conta em que linha estamos
		int numSalas = Integer.parseInt(modelo[0][0]);
		nivel.salas = new Sala[numSalas];
		
		linha++;
		for(int i = 0; i < numSalas; i++) {
			int tamanhoX = Integer.parseInt(modelo[linha][0]);
			int tamanhoY = Integer.parseInt(modelo[linha][1]);
			nivel.salas[i] = new Sala(tamanhoX, tamanhoY);
			linha++;
			
			try {
			for (int k = 0; k < tamanhoY; k++) {
				for (int j = 0; j < tamanhoX; j++) {
					Terreno terreno;
					switch (modelo[linha][j]) {
					case "T":
						terreno = new Terra(j, k, nivel);
						terreno.setSala(i);
						nivel.salas[i].adicionaTerreno(j, k, terreno);
						terreno.connect(nivel);
						break;
					case "G":
						terreno = new Gelo(j,k, nivel);
						terreno.setSala(i);
						nivel.salas[i].adicionaTerreno(j, k, terreno);
						terreno.connect(nivel);
						break;
					case "p":
						terreno = new Pedra(j, k, nivel);
						terreno.setSala(i);
						nivel.salas[i].adicionaTerreno(j, k, terreno);
						terreno.connect(nivel);
						break;
					case "Placa":
						terreno = new PlacaDePressao(j,k,nivel.salas[i].getCelula(new Posicao(j,k)), nivel);
						nivel.salas[i].adicionaTerreno(j, k, terreno);
						terreno.connect(nivel);
						break;
					default:
						throw new MontadorException("Terreno Invalido:");
					}	
				}
				linha++;
			}
			}
			catch(MontadorException erro) {
				System.out.println(erro.getMessage());
			}
			int numActors = Integer.parseInt(modelo[linha][0]);
			linha++;
			for (int k = 0; k < numActors; k++) {
				int posX = Integer.parseInt(modelo[linha][0]);
				int posY = Integer.parseInt(modelo[linha][1]);
				String stringAtor = modelo[linha][2];
				switch (stringAtor) {
					case "P":
						Actor ator = new Garoto(posX -1 , posY -1, nivel);
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator); // Assumindo que a posicao 1,1 seja a posicao da matriz 0,0
						break;
					case "C":
						Actor ator1 = new Caixa(posX -1 , posY -1, nivel);
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator1);
						break;
					case "p":
						Actor ator2 = new Parede(posX - 1, posY - 1, nivel);
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator2);
						break;
					case "L":
				
                       Actor ator3 = new LaserMaquina(posX - 1, posY - 1, Integer.parseInt(modelo[linha][3]), nivel);
                       nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator3);
                       int coluna = 5;            
                       
	                  if(  Integer.parseInt( modelo[linha][4] ) != 0) {
							for(int j = 0; j < Integer.parseInt(modelo[linha][4]); j++) {
								int posOX = Integer.parseInt(modelo[linha][coluna]);
								 System.out.println(posOX);
								coluna++;
								int posOY = Integer.parseInt(modelo[linha][coluna]);
								 System.out.println(posOY);
								 coluna++;
								 
								try { 
								if(nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getTerreno().getObservavel()) {	
										 ((LaserMaquina) ator3).getSubjects().add((Subject)nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getTerreno());
										 ((Subject)nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getTerreno()).registrar((Observer) ator3);
										 nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).setChanged();
										 nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).notificarObservadores();
									}
								else {
									throw new MontadorException(" terreno nao observavel");
								}
								}catch(MontadorException e) {
									e.getMessage();
								}
								}
							}
                       break;
					case "Porta":
						Actor ator4 = new KeyPorta(posX - 1, posY - 1, nivel, modelo[linha][4], Integer.parseInt(modelo[linha][3]));
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator4);
						linha++;
						posX = Integer.parseInt(modelo[linha][0]);
						posY = Integer.parseInt(modelo[linha][1]);
						String item  = modelo[linha][2];
						Item Chave = new Chave(posX-1,posY-1, item);
						nivel.salas[i].getCelula(new Posicao(posX-1,posY-1)).addItem(Chave);
						break;
					case "PortaDePressao":
						ObserverPorta porta = new ObserverPorta(posX - 1, posY - 1, nivel, Integer.parseInt(modelo[linha][3]));
						int coluna3 = 5;
						for(int j = 0; j < Integer.parseInt(modelo[linha][4]); j++) {
							int posOX = Integer.parseInt(modelo[linha][coluna3]);
							 System.out.println(posOX);
							coluna3++;
							int posOY = Integer.parseInt(modelo[linha][coluna3]);
							 System.out.println(posOY);
							 coluna3++;
							 
							try { 
							if(nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getTerreno().getObservavel()) {	
									 porta.getSubjects().add((Subject)nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getTerreno());
									 ((Subject)nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getTerreno()).registrar(porta);
									 System.out.println("UHULLLLLLL");
									 nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).setChanged();
									 nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).notificarObservadores();
								}
							else {
								throw new MontadorException(" terreno nao observavel");
							}
							}catch(MontadorException e) {
								e.getMessage();
							}
						
						}
					
						Actor ator5 = porta;
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator5);
						linha++;
						break;
					case "PortaChaveSaida":
						ConnectionKeyPorta ator6 = new ConnectionKeyPorta(posX - 1, posY - 1, nivel, modelo[linha][4],
								Integer.parseInt(modelo[linha][3]),Integer.parseInt(modelo[linha][5]) , Integer.parseInt(modelo[linha][6]), changer);
						nivel.salas[i].adicionaActor(posX - 1, posY - 1, ator6);
						linha++;
						posX = Integer.parseInt(modelo[linha][0]);
						posY = Integer.parseInt(modelo[linha][1]);
						String itemSaida  = modelo[linha][2];
						Item chaveSaida = new Chave(posX-1,posY-1, itemSaida);
						nivel.salas[i].getCelula(new Posicao(posX-1,posY-1)).addItem(chaveSaida);
						break;
						
						case "Computador":
						
							int temKey = Integer.parseInt(modelo[linha][3]);              ;
							posX = Integer.parseInt(modelo[linha][0]);
							posY = Integer.parseInt(modelo[linha][1]);
							Computador ator7;
							if(temKey == 0)
								ator7 = new Computador(posX - 1, posY - 1,nivel, modelo[linha][4]);
							else
								ator7 = new Computador(posX - 1, posY - 1, nivel);
							nivel.salas[i].adicionaActor(posX-1, posY-1, ator7);
							int coluna1 = 6;
							for(int j = 0; j < Integer.parseInt(modelo[linha][5]); j++) {
								int posOX = Integer.parseInt(modelo[linha][coluna1]);
								 System.out.println(posOX);
								coluna1++;
								int posOY = Integer.parseInt(modelo[linha][coluna1]);
								 System.out.println(posOY);
								 coluna1++;
							try { 
								ator7.registrar( ((Observer)nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getActor()));
								Subject[] sub = new Subject[1];
								sub[0] = ator7;
								((Observer)nivel.salas[i].getCelula(new Posicao(posOX-1, posOY-1)).getActor()).setSubejects(sub);
							}
							catch(Exception e) {
								e.getStackTrace();
							}
							}	
							break;
						case "Gema":
							Item gema = new Gema(posX - 1, posY - 1, "Gema");
							nivel.salas[i].getCelula(new Posicao(posX-1,posY-1)).addItem(gema);
							break;
						default:
						
				}
				linha++;
			}
		}
		return nivel;
	}

}