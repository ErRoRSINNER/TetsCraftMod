package content.block;

import content.item.TItem;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ShieldWall;
import mindustry.world.blocks.defense.Wall;

public class TBlockWall {
    public static Block daew, poop_wall, concrete_wall, concrete_wall_large, prav_wall, prav_wall_large;

    protected static void loadWalls(){
        concrete_wall = new Wall("concrete_wall") {{
            requirements(Category.defense, ItemStack.with(TItem.concrete, 50));
            health = 322 * 3;
            alwaysUnlocked = true;
        }};
        concrete_wall_large = new Wall("concrete_wall-large") {{
            requirements(Category.defense, ItemStack.mult(concrete_wall.requirements, 4));
            health = 322 * 3 * 4;
            size = 2;
            alwaysUnlocked = true;
        }};
        prav_wall = new Wall("prav_wall") {{
            requirements(Category.defense, ItemStack.with(TItem.goddamm_ingot, 33, TItem.beeq, 3));
            health = (int) (333 * 4.5f);
            chanceDeflect = 0.33f;
            lightningChance = 0.33f;
            deflectSound = Sounds.chargeLancer;
            alwaysUnlocked = true;
        }};
        prav_wall_large = new Wall("prav_wall-large") {{
            requirements(Category.defense, ItemStack.mult(prav_wall.requirements, 5));
            health = (int) (333 * 4 * 4.5f);
            chanceDeflect = 0.33f;
            lightningChance = 0.33f;
            deflectSound = Sounds.chargeLancer;
            size = 2;
            alwaysUnlocked = true;
        }};
        daew = new ShieldWall("DAEW") {{
            requirements(Category.defense, ItemStack.with(TItem.crystal, 5, TItem.tets_ingot, 15, TItem.tantalium, 110, TItem.superconductor, 12, TItem.nihonium, 5));
            size = 2;
            health = 2500;
            shieldHealth = 2280;
            regenSpeed = 5;
            outputsPower = false;
            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            glowColor = TItem.nihonium.color.cpy().a(0.666f);
            consumePower(0.05f);
            alwaysUnlocked = true;
        }};
        poop_wall = new Wall("poop_wall") {{
            requirements(Category.defense, ItemStack.with(TItem.poop, 2));
            size = 2;
            health = 10;
            alwaysUnlocked = true;
        }};
    }
}
