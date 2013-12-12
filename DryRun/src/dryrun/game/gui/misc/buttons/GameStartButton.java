package dryrun.game.gui.misc.buttons;




import dryrun.game.common.GameState;
import dryrun.game.mechanics.Game;
import dryrun.game.engine.network.GameStatePacket;
import dryrun.game.engine.network.server.Server;

public class GameStartButton extends Button {

	public GameStartButton(float coordX, float coordY) {
		super(coordX, coordY, "Start game");
	}

	@Override
	public void pressed() {
		super.pressed();
		System.out.println("Stisnut start game");
		if (Server.getServer().getMyThreads().size()!=0) {
			Server.getServer().startGame();
			Game.setCurrentGameState(GameState.Game);
		}
		
	}

}
