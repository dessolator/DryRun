package dryrun.game.mechanics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import dryrun.game.common.GameObjectValues;
import dryrun.game.objects.bonus.Bonus;

@SuppressWarnings("serial")
public class ServerLevel extends Level {
        World w=new World(new Vec2(0, 0));

        public ServerLevel() {
                super();
                // TODO Auto-generated constructor stub
        }

        @Override
        public void update() {
//                parseAndUpdate(net.receive());
//                myPlayer.update();
                for(Wall w: walls){
                        w.update();
                }
                for(Bonus b:bonuses){
                        b.update();
                }
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