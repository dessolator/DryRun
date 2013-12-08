package dryrun.game.gui.misc.buttons;

//import dryrun.game.RaceGame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class ApplyButton extends Button {

	public ApplyButton(float coordX, float coordY) {
		super(coordX, coordY, "Apply");
	}

	@Override
	public void pressed() {
		try {
			FileWriter fstreamWrite;
			fstreamWrite = new FileWriter("Settings.ini");
			BufferedWriter out = new BufferedWriter(fstreamWrite);
		//	out.write("Resolution= "+ResEntry.getSetRes().getWidth()+" x "+ResEntry.getSetRes().getHeight()+" ;\n");
			//out.write("Color= "+ColorsBox.isColorChecked()+" ;\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//RaceGame.updateInGameSettings();
		//RaceGame.setCurrentGameState(GameState.MainMenu);

	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}




}
