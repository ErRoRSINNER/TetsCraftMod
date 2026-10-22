package content.block;

import arc.struct.EnumSet;
import content.block.custom.LiquidAccurateSolarCrafter;
import content.block.custom.SolarHeatProducer;
import content.item.TItem;
import content.liquids.TLiquids;
import dev.jojofr.multicrafter.MultiCrafterBlock;
import dev.jojofr.multicrafter.type.Recipe;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.blocks.production.Separator;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockFlag;

import static content.util.Util.setHealth;
import static mindustry.type.ItemStack.with;

public class TBlockCrafting {
    public static Block solarHeater, solarBoiler, boiler, electric_boiler, battery_factory, bee_plant, concrete_mixer, crystalizer, shit_mixer,
            tantalium_factory, mica_press, mercury_purificator, tetsonator, superconductor_plant, absolute_zero, bingQiLingMixer, pravoslaviumMixer, erekinator,
            serpulinator, bardovovizator, apiary, composter, copperPulverizer, fusion_reactor, atmosphericCondenser, steamCompressor, centrifuge;

    protected static void loadCrafting() {
        centrifuge = new Separator("centrifuge") {{
            requirements(Category.crafting, ItemStack.with(Items.lead, 200, Items.titanium, 120, Items.graphite, 40, Items.metaglass, 40));
            size = 2;
            health = 200;
            baseExplosiveness = 3f;
            liquidCapacity = 300;
            ambientSoundVolume = 0.333f;

            craftTime = 180;
            consumePower(300/60f);
            consumeLiquid(TLiquids.radioactive_waste, 2.5f);
            results = ItemStack.with(TItem.uranium_pellet, 5, TItem.plutonium_pellet, 1);
            alwaysUnlocked = true;
        }};
        steamCompressor = new GenericCrafter("steam_compressor") {{
            requirements(Category.crafting, ItemStack.with(Items.lead, 160, Items.titanium, 80, Items.graphite, 40, Items.metaglass, 40));
            size = 3;
            health = 520;
            craftTime = 60;
            liquidCapacity = 120;
            ambientSoundVolume = 0.333f;

            consumeLiquid(TLiquids.steam, 1f);
            outputLiquid = new LiquidStack(TLiquids.compressed_steam, 0.1F);
            alwaysUnlocked = true;
        }};
        atmosphericCondenser = new GenericCrafter("atmospheric_condenser") {{
            requirements(Category.liquid, ItemStack.with(TItem.concrete, 400, Items.titanium, 80));
            health = 2200;
            size = 5;
            outputLiquid = new LiquidStack(Liquids.water, 2 / 60f);
            liquidCapacity = 120;
            ambientSound = Sounds.none;
            alwaysUnlocked = true;
        }};
        solarHeater = new SolarHeatProducer("solar_heater") {{
            requirements(Category.crafting, with(Items.lead, 80, Items.tungsten, 10));
            size = 2;
            heatOutput = 0.5f;
            itemCapacity = 0;
            health = 250;
            ambientSound = Sounds.none;
            alwaysUnlocked = true;
        }};
        solarBoiler = new LiquidAccurateSolarCrafter("solar_boiler") {{
            requirements(Category.crafting, ItemStack.with(Items.lead, 160, Items.graphite, 30));
            size = 3;
            health = 320;
            craftTime = 240;
            liquidCapacity = 30;
            ambientSound = Sounds.loopElectricHum;
            ambientSoundVolume = 0.333f;

            consumeLiquid(Liquids.water, 0.1f);
            outputLiquid = new LiquidStack(TLiquids.steam, 0.1F);
            alwaysUnlocked = true;
        }};
        boiler = new HeatCrafter("boiler") {
            {
                size = 2;
                requirements(Category.crafting, ItemStack.with(Items.lead, 100));
                health = 180;
                craftTime = 60.0F;
                heatRequirement = 10.0F;
                liquidCapacity = 20;
                ambientSound = Sounds.loopSteam;
                ambientSoundVolume = 0.333f;

                consumeLiquid(Liquids.water, 0.15f);
                outputLiquid = new LiquidStack(TLiquids.steam, 0.2F);
                alwaysUnlocked = true;
            }
        };
        electric_boiler = new GenericCrafter("electric_boiler") {
            {
                size = 2;
                requirements(Category.crafting, ItemStack.with(Items.lead, 80, Items.graphite, 30, Items.silicon, 30, TItem.battery, 20));
                health = 200;
                outputLiquid = new LiquidStack(TLiquids.steam, 0.08F);
                craftTime = 30.0F;
                consumePower(100 / 60f);
                consumeLiquid(Liquids.water, 0.1f);
                ambientSound = Sounds.loopElectricHum;
                ambientSoundVolume = 0.333f;
                liquidCapacity = 20;
                alwaysUnlocked = true;
            }
        };
        bardovovizator = new GenericCrafter("bardovovizator") {{
            requirements(Category.crafting, ItemStack.with(TItem.battery, 140, TItem.tantalium, 30, TItem.concrete, 160, TItem.tets_ingot, 30, TItem.crystal, 50));
            setHealth(this, 0.3f);
            outputLiquid = new LiquidStack(TLiquids.red_mercury, 0.03f);
            craftTime = 40.0F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeLiquids(LiquidStack.with(TLiquids.mercury, 0.4f, Liquids.oil, 0.1f));
            consumeItems(ItemStack.with(TItem.vermillion, 5, TItem.bee, 1));
            consumePower(7.7F);
            alwaysUnlocked = true;
        }};
        erekinator = new Separator("erekinator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItem.tantalium, 40, TItem.concrete, 120, Items.metaglass, 20));
            setHealth(this);
            craftTime = 40F;
            results = ItemStack.with(Items.copper, 3, Items.lead, 2, Items.titanium, 1);
            size = 3;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.beryllium, 1));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.04444f));
            alwaysUnlocked = true;
        }};
        serpulinator = new Separator("serpulinator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItem.tantalium, 40, TItem.concrete, 120, Items.metaglass, 20));
            setHealth(this);
            craftTime = 40F;
            results = ItemStack.with(Items.beryllium, 3, Items.tungsten, 1);
            size = 3;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.titanium, 1));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.04444f));
            alwaysUnlocked = true;
        }};
        copperPulverizer = new GenericCrafter("copper_pulverizer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 20, Items.lead, 30));
            setHealth(this, 0.666f);
            outputItem = new ItemStack(Items.scrap, 2);
            craftEffect = Fx.pulverize;
            craftTime = 40.0F;
            updateEffect = Fx.pulverizeSmall;
            hasItems = hasPower = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 3, true), new DrawRegion("-top"));
            ambientSound = Sounds.loopGrind;
            ambientSoundVolume = 0.025F;
            consumeItem(Items.copper, 3);
            consumePower(0.45F);
            alwaysUnlocked = true;
        }};
        pravoslaviumMixer = new MultiCrafterBlock("pravoslavium_mixer") {{
            requirements(Category.crafting, ItemStack.with(TItem.tantalium, 100, Items.thorium, 25, TItem.concrete, 50));
            setHealth(this);
            addRecipe(
                    new Recipe(
                            "money_to_goddamningot",
                            entry -> entry
                                    .withItems(TItem.tets_coin, 8, TItem.tantalium, 1)
                                    .withLiquids(Liquids.water, 2f)
                                    .withPower(3 * 60),
                            entry -> entry
                                    .withItems(TItem.goddamm_ingot, 1),
                            120
                    ),
                    new Recipe(
                            "money_to_beeq",
                            entry -> entry
                                    .withItems(TItem.tets_coin, 2, TItem.bee, 3)
                                    .withLiquids(TLiquids.honey, 0.05f)
                                    .withPower(3 * 60),
                            entry -> entry
                                    .withItems(TItem.beeq, 2),
                            50
                    ).withCraftEffect(Fx.lightningCharge)
            );

            size = 3;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 0.4f, true));
            alwaysUnlocked = true;
        }};
        fusion_reactor = new MultiCrafterBlock("fusion_reactor") {{
            requirements(Category.crafting, ItemStack.with(
                    TItem.tantalium, 1000, Items.thorium, 900, TItem.concrete, 1500,
                    TItem.tets_ingot, 300, TItem.superconductor, 2000, Items.surgeAlloy, 700,
                    Items.graphite, 4000));
            setHealth(this, 0.333f);
            flags = EnumSet.of(BlockFlag.reactor, BlockFlag.generator, BlockFlag.factory);

            addRecipe(
                    new Recipe(
                            "hyperalloy_recipe",
                            entry -> entry
                                    .withItems(Items.surgeAlloy, 6, Items.phaseFabric, 16, TItem.tets_ingot, 7)
                                    .withLiquids(TLiquids.super_cryofluid, 0.5f, TLiquids.red_mercury, 0.3f, TLiquids.honey, 1.4f),
                            entry -> entry
                                    .withItems(TItem.hyperalloy, 1),
                            400
                    ).withCraftEffect(new MultiEffect(Fx.mineImpactWave, Fx.neoplasiaSmoke, Fx.regenParticle))
                            .withMaxEfficiency(9.99f),
                    new Recipe(
                            "surge_alloy_from_cmt_recipe",
                            entry -> entry
                                    .withItems(Items.copper, 18, TItem.mica, 24, TItem.tantalium, 14)
                                    .withLiquids(TLiquids.mercury, 0.5f)
                                    .withPower(5 * 60),
                            entry -> entry
                                    .withItems(Items.surgeAlloy, 42)
                                    .withLiquids(Liquids.slag, 1.5f),
                            40
                    ).withCraftEffect(new MultiEffect(Fx.mineImpactWave, Fx.neoplasiaSmoke, Fx.regenParticle))
                            .withMaxEfficiency(9.99f),
                    new Recipe(
                            "nihonium_recipe",
                            entry -> entry
                                    .withItems(Items.thorium, 18, TItem.tets_ingot, 24)
                                    .withLiquids(TLiquids.super_cryofluid, 0.3333f)
                                    .withPower(58 * 60),
                            entry -> entry
                                    .withItems(TItem.nihonium, 15)
                                    .withLiquids(Liquids.neoplasm, 1f),
                            320
                    ).withCraftEffect(new MultiEffect(Fx.mineImpactWave, Fx.neoplasiaSmoke, Fx.regenParticle))
                            .withMaxEfficiency(9.99f),
                    new Recipe(
                            "nihonium_to_energy_recipe",
                            entry -> entry
                                    .withItems(TItem.nihonium, 3, Items.thorium, 13)
                                    .withLiquids(TLiquids.super_cryofluid, 0.35f)
                                    .withPower(11 * 60),
                            entry -> entry
                                    .withLiquids(Liquids.neoplasm, 0.441f)
                                    .withPower(49 * 60),
                            320 / 2.5f
                    ).withCraftEffect(new MultiEffect(Fx.mineImpactWave, Fx.neoplasiaSmoke, Fx.regenParticle))
                            .withMaxEfficiency(9.99f),
                    new Recipe(
                            "phase_thorium_to_energy_recipe",
                            entry -> entry
                                    .withItems(Items.phaseFabric, 3, Items.thorium, 5)
                                    .withLiquids(TLiquids.super_cryofluid, 0.05f)
                                    .withPower(11 * 32),
                            entry -> entry
                                    .withLiquids(Liquids.neoplasm, 0.1f)
                                    .withPower(35 * 12),
                            60
                    ).withCraftEffect(new MultiEffect(Fx.mineImpactWave, Fx.neoplasiaSmoke, Fx.regenParticle))
                            .withMaxEfficiency(9.99f)
            );

            ambientSound = Sounds.loopPulse;
            canOverdrive = false;

            itemCapacity = 100;
            liquidCapacity = 300;

            size = 6;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade(), new DrawHeatOutput());
            alwaysUnlocked = true;
        }};
        bingQiLingMixer = new GenericCrafter("bing_qi_ling_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 20, Items.lead, 40, Items.graphite, 20, Items.copper, 40));
            setHealth(this);
            craftTime = 120.0F;
            outputItem = new ItemStack(TItem.bing_qi_ling, 1);
            size = 2;
            craftEffect = Fx.colorSparkBig;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(0.4F);
            consumeItems(ItemStack.with(Items.sporePod, 2));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.15f));
            alwaysUnlocked = true;
        }};
        absolute_zero = new GenericCrafter("absolute_zero") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 30, TItem.tantalium, 70, TItem.concrete, 160, TItem.tets_ingot, 30, Items.metaglass, 130));
            setHealth(this, 0.3f);
            outputLiquid = new LiquidStack(TLiquids.super_cryofluid, 0.05f);
            craftTime = 40.0F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.05f, TLiquids.mercury, 0.05f));
            consumeItems(ItemStack.with(Items.phaseFabric, 1));
            consumePower(2.31F);
            alwaysUnlocked = true;
        }};
        superconductor_plant = new GenericCrafter("superconductor_plant") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 50, TItem.tantalium, 80, TItem.concrete, 60, TItem.tets_ingot, 70, TItem.battery, 130));
            setHealth(this, 0.6f);
            craftTime = 96.0F;
            outputItem = new ItemStack(TItem.superconductor, 2);
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeLiquid(TLiquids.super_cryofluid, 0.1f);
            consumePower(5.2F);
            consumeItems(ItemStack.with(TItem.tets_ingot, 2, TItem.mica, 2, Items.copper, 3, Items.metaglass, 1));
            alwaysUnlocked = true;
        }};
        shit_mixer = new GenericCrafter("shit_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            setHealth(this, 0.3f);
            craftTime = 190.0F;
            outputItem = new ItemStack(TItem.poop, 3);
            size = 3;
            craftEffect = Fx.vapor;
            ambientSound = Sounds.loopBio;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 1.1f, true));
            consumeLiquid(Liquids.water, 0.1f);
            consumePower(0.07F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.sporePod, 3, Items.lead, 1, Items.copper, 1));
            alwaysUnlocked = true;
        }};
        battery_factory = new GenericCrafter("battery_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            setHealth(this);
            craftTime = 90.0F;
            outputItem = new ItemStack(TItem.battery, 1);
            size = 2;
            craftEffect = Fx.lightningCharge;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.silicon, 2, Items.lead, 2, Items.copper, 1));
            consumeLiquid(Liquids.water, 0.066f);
            alwaysUnlocked = true;
        }};
        tetsonator = new GenericCrafter("tetsonator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItem.tantalium, 80, TItem.concrete, 125, Items.metaglass, 40, TItem.battery, 40));
            setHealth(this, 0.5f);
            craftTime = 144.44444F;
            outputItem = new ItemStack(TItem.tets_ingot, 2);
            size = 3;
            craftEffect = Fx.freezing;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.45F);
            consumeItems(ItemStack.with(Items.titanium, 1, TItem.tantalium, 1));
            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.2f, TLiquids.mercury, 0.1f));
            alwaysUnlocked = true;
        }};
        tantalium_factory = new Separator("tantalium_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, Items.titanium, 30, Items.graphite, 40, Items.copper, 120));
            setHealth(this);
            craftTime = 100.0F;
            results = ItemStack.with(TItem.tantalium, 18, Items.scrap, 4);
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.2F);
            consumeItems(ItemStack.with(Items.titanium, 1, Items.graphite, 1));
            consumeLiquid(Liquids.water, 0.1f);
            alwaysUnlocked = true;
        }};
        crystalizer = new GenericCrafter("crystalizer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.silicon, 14, TItem.tantalium, 15, Items.metaglass, 10));
            setHealth(this);
            craftTime = 180.0F;
            size = 2;
            outputItem = new ItemStack(TItem.crystal, 1);
            craftEffect = Fx.healWave;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.0F);
            consumeItems(ItemStack.with(TItem.mica, 1, Items.silicon, 1));
            consumeLiquid(TLiquids.mercury, 0.033f);
            alwaysUnlocked = true;
        }};
        mercury_purificator = new HeatCrafter("mercury_purificator") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.graphite, 15, Items.metaglass, 10));
            heatRequirement = 1.0F;
            setHealth(this);
            craftTime = 30.0F;
            size = 2;
            outputLiquid = new LiquidStack(TLiquids.mercury, 0.045f);
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeItems(ItemStack.with(TItem.vermillion, 3));
            alwaysUnlocked = true;
        }};
        bee_plant = new Separator("bee-plant") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 100, Items.graphite, 50, Items.silicon, 5));
            setHealth(this);
            results = ItemStack.with(TItem.bee, 7, TItem.beeq, 1);
            craftTime = 180.0F;
            size = 3;
            itemCapacity = 20;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(0.2F);
            consumeItem(TItem.bing_qi_ling, 2);
            consumeLiquid(Liquids.water, 0.04F);
            alwaysUnlocked = true;
        }};
        apiary = new GenericCrafter("apiary") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 60, Items.copper, 100, TItem.bing_qi_ling, 50));
            setHealth(this);
            craftTime = 500.0F;
            liquidOutputDirections = new int[]{1, 3};
            outputLiquids = LiquidStack.with(TLiquids.honey, 0.025f, Liquids.oil, 0.01f);
            drawer = new DrawMulti(new DrawDefault());
            consumeItems(ItemStack.with(TItem.bee, 9, TItem.beeq, 3, TItem.bing_qi_ling, 4));
            consumeLiquid(Liquids.water, 0.035f);
            size = 2;
            alwaysUnlocked = true;
        }};
        composter = new GenericCrafter("composter") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 40, TItem.bing_qi_ling, 25, TItem.poop, 300));
            setHealth(this);
            craftTime = 100.0F;
            outputLiquid = new LiquidStack(Liquids.arkycite, 0.3f);
            drawer = new DrawMulti(new DrawDefault());
            consumeItems(ItemStack.with(TItem.bee, 3, TItem.poop, 10));
            consumeLiquid(TLiquids.honey, 0.3f);
            size = 2;
            alwaysUnlocked = true;
        }};
        mica_press = new GenericCrafter("mica-press") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            setHealth(this);
            craftTime = 290.0F;
            outputItem = new ItemStack(TItem.mica, 1);
            size = 2;
            craftEffect = Fx.smokeCloud;
            drawer = new DrawMulti(new DrawDefault());
            consumePower(0.333F);
            consumeItems(ItemStack.with(Items.graphite, 1, Items.silicon, 1, Items.metaglass, 1));
            alwaysUnlocked = true;
        }};
        concrete_mixer = new GenericCrafter("concrete_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 60, Items.silicon, 12, Items.lead, 35));
            setHealth(this);
            outputItems = ItemStack.with(TItem.concrete, 2, Items.scrap, 1);
            craftTime = 77.7F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotor", 3, true));
            consumePower(0.4F);
            consumeItems(ItemStack.with(Items.sand, 4, Items.lead, 1, Items.copper, 1));
            consumeLiquid(Liquids.water, 0.1F);
            alwaysUnlocked = true;
        }};
    }
}
