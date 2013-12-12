package dryrun.game.objects.bonus;

import org.jbox2d.common.Vec2;

import dryrun.game.common.Player;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Tex;
import dryrun.game.engine.TextureHolder;
import static dryrun.game.engine.LoadTex.machineGun;


public class MachineGunBonus extends Bonus {
	public static TextureHolder texture; 
	
	//ukoliko je nepotrebno izbaciti pickedUp
	public final int bId=1;
	
	public MachineGunBonus(float x, float y, float dimx, float dimy, int myInd,
			Vec2 position) {
		super(x, y, dimx, dimy, myInd, position);	
	}

	public void applyBonus(Player p){
		p.incMachinegunAmmo(50);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void undo(){}


	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void render(){
		if(!this.isAmIspawned()){
			texture=new TextureHolder(machineGun,new Tex(0f,0f,1f,1f));
			DrawObject.draw(this);
			}
		}
	}

