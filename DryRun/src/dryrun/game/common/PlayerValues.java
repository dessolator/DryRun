package dryrun.game.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dryrun.game.objects.GameObject;

public class PlayerValues extends GameObjectValues {
	private String name;
	protected List<GameObject> myObjects=Collections.synchronizedList(new ArrayList<GameObject>());
	
	public List<GameObject> getObjList(){return myObjects;}
}
