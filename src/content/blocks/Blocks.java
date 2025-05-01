package content.blocks;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.Drill;

public class Blocks{
    public static Block gomodrill, tetsDrill;


    public static void load() {
        gomodrill = new Block("gomodrill") {
            {
                rotate = false;
                size = 1;
                health = 200;
            }
        };

        tetsDrill = new Drill("tets-drill") {{
            requirements(Category.production, ItemStack.with(Items.thorium, 12));
            tier = 5;
            size = 3;
            drillTime = 50;
        }};

    }
}
