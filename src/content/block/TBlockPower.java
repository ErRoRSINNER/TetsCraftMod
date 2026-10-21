package content.block;

import arc.struct.EnumSet;
import content.block.custom.CustomNuclearReactor;
import content.item.TItem;
import content.liquids.TLiquids;
import dev.jojofr.multicrafter.MultiCrafterBlock;
import dev.jojofr.multicrafter.type.Recipe;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.consumers.ConsumePower;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import mindustry.world.draw.DrawWarmupRegion;
import mindustry.world.meta.BlockFlag;

import static content.util.Util.setHealth;

public class TBlockPower {
    public static Block batteryArray, tetsPowerNode, solpanel, tets_battery, crystal_powerblock,
            steamTurbine, custom_thorium_reactor, customSteamGenerator;

    protected static void loadPower() {
        custom_thorium_reactor = new CustomNuclearReactor("custom_thorium_reactor"){
            {
                this.requirements(Category.power, ItemStack.with(new Object[]{Items.lead, 300, Items.silicon, 200, Items.graphite, 150, Items.thorium, 150, Items.metaglass, 50}));
                this.ambientSound = Sounds.loopHum;
                this.ambientSoundVolume = 0.24F;
                this.size = 3;
                this.health = 700;
                this.itemDuration = 360.0F;
                this.heating = 0.02F;
                this.explodeOnFull = true;
                this.consumeItem(Items.thorium);
                this.consumeLiquid(Liquids.cryofluid, this.heating / this.coolantPower).update(false);
                this.outputLiquid = new LiquidStack(TLiquids.radioactive_waste, 0.05f);
            }
        } ;
        customSteamGenerator = new MultiCrafterBlock("custom_steam_generator") {
            {
                requirements(Category.power, ItemStack.with(Items.copper, 60, Items.graphite, 30, Items.lead, 40, Items.silicon, 40));
                size = 2;
                ambientSound = Sounds.loopSmelter;
                ambientSoundVolume = 0.06F;
                flags = EnumSet.of(BlockFlag.reactor, BlockFlag.generator, BlockFlag.factory);
                alwaysUnlocked = true;
                consPower = new ConsumePower(0, 0, true);
                drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion(), new DrawRegion("-turbine") {
                    {
                        rotateSpeed = 2.0F;
                    }
                }, new DrawRegion("-turbine") {
                    {
                        rotateSpeed = -2.0F;
                        rotation = 45.0F;

                    }
                }, new DrawRegion("-cap"));

                addRecipe(
                        new Recipe("custom_sg_energy_recipe",
                                entry -> entry
                                        .withItems(Items.coal, 1)
                                        .withLiquids(Liquids.water, 0.1),
                                entry -> entry
                                        .withPower(5.5f),
                                90
                        ).withCraftEffect(Fx.generatespark),
                        new Recipe("custom_sg_steam_recipe",
                                entry -> entry
                                        .withItems(Items.coal, 1)
                                        .withLiquids(Liquids.water, 0.1),
                                entry -> entry
                                        .withLiquids(TLiquids.steam, 0.08f),
                                60
                        ).withCraftEffect(Fx.generatespark)
                );
            }
        };
        steamTurbine = new ConsumeGenerator("steam_turbine") {{
            requirements(Category.power, ItemStack.with(TItem.battery, 160, Items.silicon, 60, Items.lead, 100, Items.metaglass, 50));
            health = 400;
            size = 4;
            baseExplosiveness = 4.5f;
            liquidCapacity = 40;
            consumeLiquid(TLiquids.compressed_steam, 0.05f);
            powerProduction = 30f;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawWarmupRegion(),
                    new DrawRegion("-turbine") {{
                        rotateSpeed = 2f;
                    }},
                    new DrawDefault()
            );
        }};
        batteryArray = new Battery("battery_array") {{
            requirements(Category.power, ItemStack.with(TItem.battery, 100, Items.silicon, 30, Items.lead, 50));
            setHealth(this);
            size = 2;
            consumePowerBuffered(100000);
            baseExplosiveness = 7;
            alwaysUnlocked = true;
        }};
        solpanel = new SolarGenerator("solpanel") {{
            requirements(Category.power, ItemStack.with(TItem.tets_ingot, 25, TItem.tantalium, 15, TItem.battery, 10, Items.silicon, 55));
            setHealth(this);
            size = 2;
            powerProduction = 1.15F;
            hasPower = true;
            baseExplosiveness = 6;
            alwaysUnlocked = true;
        }};
        tets_battery = new Battery("tets_battery") {{
            requirements(Category.power, ItemStack.with(TItem.tets_ingot, 15, TItem.tantalium, 15, TItem.battery, 50, Items.silicon, 30));
            setHealth(this);
            size = 2;
            consumePowerBuffered(300000);
            baseExplosiveness = 8;
            alwaysUnlocked = true;
        }};
        crystal_powerblock = new Battery("crystal_powerblock") {{
            requirements(Category.power, ItemStack.with(TItem.tets_ingot, 55, TItem.tantalium, 30, TItem.battery, 10, TItem.crystal, 110, Items.silicon, 40, TItem.superconductor, 10));
            setHealth(this, 0.33f);
            size = 2;
            consumePowerBuffered(707000);
            baseExplosiveness = 8;
            alwaysUnlocked = true;
        }};
    }
}
