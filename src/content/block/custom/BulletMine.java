package content.block.custom;

import arc.math.Mathf;
import mindustry.world.blocks.defense.ShockMine;

public class BulletMine extends ShockMine {
    public int bee_inaccuracy = 15;
    public BulletMine(String name) {
        super(name);
        buildType = BulletMineBuild::new;
    }

    public class BulletMineBuild extends ShockMineBuild {

        @Override
        public void triggered() {
            if (bullet != null) {
                double angle = 360d / shots;
                for (int i = 0; i < shots; i++) {
                    double rad = Math.toRadians(angle * i);
                    bullet.create(this, (float) (x + Math.sin(rad) * bee_inaccuracy), (float) (y + Math.cos(rad) * bee_inaccuracy), (float) angle * i + Mathf.random(inaccuracy));
                }
            }
        }
    }
}
