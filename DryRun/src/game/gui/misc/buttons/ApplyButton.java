package game.gui.misc.buttons;

import game.GameState;
import game.RaceGame;
import game.gui.menus.misc.checkboxes.ColorsBox;
import game.gui.menus.misc.dropdown.ResEntry;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ApplyButton extends Button {

	public ApplyButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Apply");
	}

	@Override
	public void pressed() {
		try {
			FileWriter fstreamWrite;
			fstreamWrite = new FileWriter("Settings.ini");
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write("Resolution= "+ResEntry.getSetRes().getWidth()+" x "+ResEntry.getSetRes().getHeight()+" ;\n");
			out.write("Color= "+ColorsBox.isColorChecked()+" ;\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RaceGame.updateInGameSettings();
		RaceGame.setCurrentGameState(GameState.MainMenu);

	}

}
