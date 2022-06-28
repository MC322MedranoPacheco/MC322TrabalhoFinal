package control.montador;

import control.gameControl.SalaChanger;
import model.nivel.Nivel;

public interface IFazerNivel {
	public Nivel constroiNivel(String path, String arquivo, SalaChanger changer);
}
