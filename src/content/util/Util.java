package content.util;

import mindustry.type.ItemStack;
import mindustry.world.Block;

public class Util {
    public static void setHealth(Block block) {
        setHealth(block, 1);
    }

    public static void setHealth(Block block, double mult) {
        float health = 69;
        if (block.requirements.length > 0) {
            for (ItemStack stack : block.requirements) {
                health += (float) ((stack.item.cost * stack.item.cost + 1) * stack.amount * mult);
            }
            block.health = (int) health;
            return;
        }
        block.health = 150;
    }
}
