package content.block;

import content.item.TItem;
import content.liquids.TLiquids;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;

import static content.util.Util.setHealth;
import static mindustry.type.ItemStack.with;

public class TBlockDrill {
    public static Block homoDrill, miniDrill, nihonDrill, tetsDrill, steamVentCondenser;

    protected static void loadDrills() {
        steamVentCondenser = new ThermalGenerator("steam_vent_condenser"){{
            requirements(Category.liquid, with(Items.beryllium, 100));
            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            displayEfficiencyScale = 1f / 9f;
            minEfficiency = 9f - 0.0001f;
            displayEfficiency = false;
            generateEffect = Fx.turbinegenerate;
            effectChance = 0.04f;
            size = 3;
            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.06f;
            hasLiquids = true;
            outputLiquid = new LiquidStack(TLiquids.steam, 15f / 60f / 9f);
            liquidCapacity = 60f;
            fogRadius = 3;

            alwaysUnlocked = true;
        }};
        homoDrill = new Drill("homo-drill") {{
            requirements(Category.production, ItemStack.with(Items.copper, 20, Items.lead, 5));
            tier = 2;
            drillTime = 400.0F;
            size = 1;
            //researchCost = ItemStack.with(new Object[]{Items.copper, 100});
            consumeLiquid(Liquids.water, 0.06F).boost();
            health = 100;
            alwaysUnlocked = true;
        }};
        miniDrill = new Drill("mini-drill") {{
            requirements(Category.production, ItemStack.with(TItem.concrete, 12, TItem.tantalium, 10));
            tier = 4;
            drillTime = 130.0F;
            size = 1;
            //researchCost = ItemStack.with(new Object[]{TItem.concrete, 100, TItem.tantalium, 60});
            consumeLiquid(Liquids.water, 0.08F).boost();
            consumePower(0.15f);
            health = 120;
            alwaysUnlocked = true;
        }};
        nihonDrill = new Drill("nihon-drill") {{
            requirements(Category.production, ItemStack.with(TItem.nihonium, 240, TItem.tets_ingot, 1000));
            setHealth(this);
            tier = 6;
            drillTime = 20.0F;
            size = 5;
            //researchCost = ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60});
            consumeLiquid(Liquids.cryofluid, 0.2F).boost();
            consumePower(5.3f);
            alwaysUnlocked = true;
        }};
        tetsDrill = new Drill("tets-drill") {{
            requirements(Category.production, ItemStack.with(TItem.concrete, 320, TItem.tets_ingot, 69, TItem.battery, 50, TItem.tantalium, 120));
            setHealth(this);
            tier = 5;
            size = 3;
            drillTime = 50;
            consumePower(3.3f);
            consumeLiquid(Liquids.cryofluid, 0.1F).boost();
            alwaysUnlocked = true;
        }};
    }
}
