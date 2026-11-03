package content.block;

import content.item.TItem;
import mindustry.content.Fx;
import mindustry.entities.bullet.MassDriverBolt;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.distribution.Router;

import static content.util.Util.setHealth;

public class TBlockDistributors {
    public static Block teleporter, tets_conveyor, tantal_router, tetsBridge;

    protected static void loadDistributors() {
        tantal_router = new Router("tantal_router") {{
            requirements(Category.distribution, ItemStack.with(TItem.tantalium, 4, TItem.concrete, 8));
            setHealth(this);
            speed = 12;
            buildCostMultiplier = 4f;
            size = 3;
        }};
        tetsBridge = new BufferedItemBridge("tets_bridge-conveyor") {{
            requirements(Category.distribution, ItemStack.with(TItem.battery, 4, TItem.tets_ingot, 4, TItem.concrete, 8));
            setHealth(this);
            fadeIn = moveArrows = false;
            range = 16;
            speed = 30.0F;
            arrowSpacing = 7.0F;
            bufferCapacity = 28;
        }};
        tets_conveyor = new Conveyor("tets-conveyor") {{
            requirements(Category.distribution, ItemStack.with(TItem.battery, 1, TItem.tets_ingot, 1, TItem.concrete, 1));
            setHealth(this);
            separateItemCapacity = true;
            speed = 0.55f;
            displayedSpeed = 9.0F;
            health = 260;
            alwaysUnlocked = true;
            bridgeReplacement = tetsBridge;

        }};
        teleporter = new MassDriver("teleporter") {{
            requirements(Category.distribution, ItemStack.with(TItem.tets_ingot, 300, TItem.battery, 130, TItem.hyperalloy, 6));
            setHealth(this);
            size = 1;
            range = 12800;
            reload = 3;
            shake = 0;
            bulletSpeed = 220;
            shootEffect = Fx.lightningShoot;
            smokeEffect = Fx.colorSpark;
            receiveEffect = Fx.mineBig;
            shootSound = Sounds.shootLaser;
            consumePower(3f);
            bullet = new MassDriverBolt() {{
                collidesTiles = false;
                lifetime = 100f;
                despawnEffect = Fx.dropItem;
                hitEffect = Fx.hitLaser;
            }};
            alwaysUnlocked = true;
        }};
    }
}
