package content.blocks;

import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.Drill;

public class Blocks{
    public static Block homoDrill, miniDrill, tetsDrill;


    public static void load() {
        homoDrill = new Drill("homo-drill") {
            {
                requirements(Category.production, ItemStack.with(Items.copper, 24));
                tier = 2;
                this.drillTime = 500.0F;
                size = 1;
                this.researchCost = ItemStack.with(new Object[]{Items.copper, 100});
                this.consumeLiquid(Liquids.water, 0.06F).boost();

                health = 100;
            }
        };
        miniDrill = new Drill("mini-drill") {
            {
                requirements(Category.production, ItemStack.with(Items.copper, 24, Items.lead, 24));
                tier = 2;
                this.drillTime = 400.0F;
                size = 1;
                this.researchCost = ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60});
                this.consumeLiquid(Liquids.water, 0.06F).boost();

                health = 120;
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
