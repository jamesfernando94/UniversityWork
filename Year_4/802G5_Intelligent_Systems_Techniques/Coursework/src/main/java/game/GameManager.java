package game;

import moves.Move;

import java.util.ArrayList;

public class GameManager {

	private PlayerType darkPlayer;
	private PlayerType lightPlayer;
	private Game game;

	/**
	 *
	 * @param darkPlayer enum for the type of dark player
	 * @param lightPlayer enum for the type of light player
	 */
	public GameManager(PlayerType darkPlayer, PlayerType lightPlayer){

		this.darkPlayer = darkPlayer;
		this.lightPlayer = lightPlayer;
	}

	public Game getGame(){return game;}

	public Colour getCurrentTurn(){return game.currentTurn;}

	public ArrayList<Move> getPossibleMoves(){
		return game.findAllPossibleMoves();
	}

	public void selectMove(Move move){game.selectMove(move);}


}
