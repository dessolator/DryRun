package dryrun.game.mechanics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import dryrun.game.common.*;
import dryrun.game.common.BonusCreator;
import dryrun.game.common.GameObjectValues;

@SuppressWarnings("serial")
public class ServerLevel extends Level {
        World w=new World(new Vec2(0, 0));

        public ServerLevel(Player p) {
            //super takes player as a parameter    
        	super(p);
                // TODO Auto-generated constructor stub
        }

        @Override
        public void update() {
//        	(Bonus)(BonusCreator.getBonusCreator().getMyObjects().get(0)).update();
//                parseAndUpdate(net.receive());
//                myPlayer.update();
                for(Wall w: walls){
                        w.update();
                        
//                        (ArrayList<Bonus>)(BonusCreator.getBonusCreator().getMyObjects());
                }
//                for(Bonus b:(Bonus)(BonusCreator.getBonusCreator().getMyObjects())){
               //         b.update();
               // }
                for(Checkpoint c:checkpoints){
                        c.update();
                }
                
                GameObjectValues p[]=new GameObjectValues[5];
                for(int i=0;i<5;i++){
                        p[i]=players.get(i).getMyValues();
                }
//TODO                 net.send(p);
        }
}
