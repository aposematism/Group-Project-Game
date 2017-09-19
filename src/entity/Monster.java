package entity;

/**
 * Created by metcalmark on 19/09/17.
 */
public class Monster extends Mob {
    MonsterStrategy strategy = new MonsterStrategy() {
        @Override
        public void move() {

        }

        @Override
        public void attack() {

        }

        @Override
        public void die() {

        }
    };
}
