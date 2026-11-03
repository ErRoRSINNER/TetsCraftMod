package content.block;

import content.item.TItem;
import content.units.TUnits;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.units.Reconstructor;

public class TBlockUnit {
    public static Block tetsBasicReconstructorEnergy, tetsBasicReconstructorAttack, tetsAdditiveReconstructorAttack,
            tetsAdditiveReconstructorEnergy, tetsMultiplicativeReconstructorEnergy;

    protected static void loadUnits() {
        //T1
        tetsBasicReconstructorAttack = new Reconstructor("tets_basic_reconstructor_attack") {
            {
                requirements(Category.units, ItemStack.with(Items.copper, 100, Items.lead, 60));
                size = 3;
                health = 300;
                consumePower(1.0F);
                consumeItems(ItemStack.with(Items.pyratite, 10, Items.silicon, 10));
                constructTime = 300.0F;
                upgrades.addAll(new UnitType[][]{{TUnits.walkingBaseUnit, TUnits.smallArtillery}});
                alwaysUnlocked = true;
            }
        };
        tetsBasicReconstructorEnergy = new Reconstructor("tets_basic_reconstructor_energy") {
            {
                requirements(Category.units, ItemStack.with(Items.copper, 100, Items.lead, 60));
                size = 3;
                health = 300;
                consumePower(1.0F);
                consumeItems(ItemStack.with(TItem.battery, 5, Items.graphite, 10));
                constructTime = 300.0F;
                upgrades.addAll(new UnitType[][]{{TUnits.walkingBaseUnit, TUnits.miniMiner}});
                alwaysUnlocked = true;
            }
        };


        //T2
        tetsAdditiveReconstructorAttack = new Reconstructor("tets_additive_reconstructor_attack") {
            {
                requirements(Category.units, ItemStack.with(Items.copper, 200, Items.lead, 120, Items.titanium, 80, TItem.concrete, 100));
                size = 3;
                health = 600;
                consumePower(3.0F);
                consumeItems(ItemStack.with(Items.titanium, 10, Items.silicon, 20, Items.blastCompound, 20));
                constructTime = 1200.0F;
                upgrades.addAll(new UnitType[][]{{TUnits.smallArtillery, TUnits.mediumArtillery}});
                alwaysUnlocked = true;
            }
        };
        tetsAdditiveReconstructorEnergy = new Reconstructor("tets_additive_reconstructor_energy") {
            {
                requirements(Category.units, ItemStack.with(Items.copper, 200, Items.lead, 120, Items.titanium, 80, TItem.battery, 160, TItem.tantalium, 100));
                size = 3;
                health = 600;
                consumePower(3.0F);
                consumeItems(ItemStack.with(Items.beryllium, 10, Items.graphite, 20, TItem.battery, 80));
                constructTime = 1200.0F;
                upgrades.addAll(new UnitType[][]{{TUnits.UFO, TUnits.UF1_Energy}, {TUnits.miniMiner, TUnits.mediMiner}});
                alwaysUnlocked = true;
            }
        };

        //T3
        tetsMultiplicativeReconstructorEnergy = new Reconstructor("tets_multiplicative_reconstructor_energy") {
            {
                requirements(Category.units, ItemStack.with(Items.tungsten, 400, Items.lead, 240, Items.titanium, 200, TItem.battery, 250, TItem.tantalium, 200));
                size = 5;
                health = 1000;
                consumePower(4.0F);
                consumeItems(ItemStack.with(Items.beryllium, 80, Items.tungsten, 60, TItem.battery, 120, TItem.tantalium, 40));
                //consumeLiquid(Liquids.cryofluid, 1f);
                constructTime = 2400.0F;
                upgrades.addAll(new UnitType[][]{{TUnits.UF1_Energy, TUnits.UF2_Energy}});
                alwaysUnlocked = true;
            }
        };
    }
}
