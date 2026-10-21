package content.block;

import arc.graphics.Color;
import content.item.TItem;
import content.liquids.TLiquids;
import content.units.TUnits;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.RegionPart;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.logic.LogicDisplay;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.BuildVisibility;

import static content.util.Util.setHealth;
import static mindustry.type.ItemStack.with;

public class TBlock {

    // Other
    public static Block tets_display, tets_processor, vermillion, tetsOre;

    //Torque

    public static Block test1, test2;

    public static void load() {
        TBlockCrafting.loadCrafting();
        TBlockDrill.loadDrills();
        TBlockWall.loadWalls();
        TBlockUtility.loadUtil();
        TBlockPower.loadPower();
        TBlockTurret.loadTurrets();
        TBlockDistributors.loadDistributors();
        TBlockUnit.loadUnits();
        TBlockFake.loadFake();

        loadOres();
        loadOther();
        loadMixins();
    }

    private static void loadOres() {
        vermillion = new OreBlock("vermillion", TItem.vermillion) {{
            oreDefault = true;
            oreThreshold = 0.86F;
            oreScale = 16.3F;
            alwaysUnlocked = true;
        }};
        tetsOre = new OreBlock("tetsore", TItem.tets_coin) {{
            oreDefault = true;
            oreThreshold = 0.95F;
            oreScale = 23.3F;
            alwaysUnlocked = true;
        }};
    }

    private static void loadOther() {
        tets_display = new LogicDisplay("tets_display") {{
            requirements(Category.logic, with(TItem.tantalium, 300, TItem.tets_ingot, 150, TItem.battery, 200, Items.metaglass, 150, Items.phaseFabric, 80));
            setHealth(this, 0.12f);

            displaySize = 7 * 32 - 8 - 8;

            size = 7;
        }};

        tets_processor = new LogicBlock("tets_processor") {{
            requirements(Category.logic, with(TItem.tantalium, 400, TItem.tets_ingot, 250, TItem.battery, 350, TItem.superconductor, 175, Items.surgeAlloy, 100));
            setHealth(this, 0.1f);

            consumeLiquid(TLiquids.super_cryofluid, 0.02f);
            consumePower(0.1f);
            liquidCapacity = 100;

            instructionsPerTick = 48;
            range = 9 * 64;
            size = 4;
        }};

        test1 = new LaserTurret("cirno_gunv") {
            {
                firingMoveFract = 1.5F;
                shootDuration = 230.0F;
                shootSound = Sounds.shootLaser;
                loopSound = Sounds.beamPlasma;
                loopSoundVolume = 1.0F;
                envEnabled |= 2;

                requirements(Category.turret, BuildVisibility.sandboxOnly, with(Items.titanium, 999, TItem.battery, 999, TItem.bing_qi_ling, 999, Items.graphite, 999));
                setHealth(this);

                size = 3;
                range = 300;
                reload = 4.0F;
                velocityRnd = 0.0F;
                inaccuracy = 5.0F;
                recoil = 1.0F;
                shootCone = 60.0F;
                liquidCapacity = 150.0F;
                shootEffect = Fx.shootLiquid;
                coolant = new ConsumeLiquid(Liquids.water, 0.1f);
                linearWarmup = true;
                shootType = new ContinuousLaserBulletType(78.0F) {
                    {
                        length = 300.0F;
                        hitEffect = Fx.hitLaser;
                        hitColor = Pal.techBlue;
                        status = StatusEffects.freezing;
                        drawSize = 420.0F;
                        incendChance = 0.4F;
                        incendSpread = 5.0F;
                        incendAmount = 1;
                        ammoMultiplier = 1.0F;
                        colors = new Color[]{Liquids.cryofluid.color, Liquids.water.color, Liquids.cryofluid.gasColor};
                        width = 3;
                    }
                };
                scaledHealth = 200.0F;
                consumePower(17.0F);
                drawer = new DrawTurret("cirno_gun") {{
                    parts.add(new RegionPart("-barrel") {
                        {
                            progress = PartProgress.constant(1);
                            mirror = false;
                            under = false;
                            moveX = 0.0F;
                            moveY = 12.0F;
                        }
                    }, new RegionPart("-wing") {
                        {
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = false;
                            moveX = -17.0F;
                            moveY = 0.0F;
                            xScl = 1.8f;
                            yScl = 1.8f;
                        }
                    }, new RegionPart("-wing") {
                        {
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = false;
                            moveRot = -45f; //Upper Pair
                            moveX = -17.0F;
                            moveY = 10.0F;
                            xScl = 1.8f;
                            yScl = 1.8f;
                        }
                    }, new RegionPart("-wing") {
                        {
                            progress = PartProgress.warmup;
                            mirror = true;
                            under = false;
                            moveRot = 45f; //Lower pair
                            moveX = -17.0F;
                            moveY = -10.0F;
                            xScl = 1.8f;
                            yScl = 1.8f;
                        }
                    });
                }};
            }
        };
        test2 = new HeatConductor("conductor") {{
            requirements(Category.crafting, BuildVisibility.sandboxOnly, ItemStack.with(Items.tungsten, 16, TItem.tantalium, 16, Items.graphite, 20));
            setHealth(this, 0.33f);
            size = 1;
            regionRotated1 = 1;
            visualMaxHeat = 25;
        }};
    }

    private static void loadMixins() {
        ((UnitFactory) Blocks.groundFactory).plans.add(new UnitFactory.UnitPlan(TUnits.walkingBaseUnit, 2100.0F, ItemStack.with(Items.silicon, 30, Items.lead, 30, Items.copper, 30)));
        ((UnitFactory) Blocks.airFactory).plans.add(new UnitFactory.UnitPlan(TUnits.UFO, 230.0F, ItemStack.with(Items.silicon, 5, TItem.battery, 2)));

        ((ItemTurret) Blocks.duo).ammoTypes.put(TItem.bee, new BasicBulletType(1.3f, 10) {{
            lifetime = ((ItemTurret) Blocks.duo).range / speed + 10;
            pierce = true;
            weaveMag = 10;
            weaveScale = 0.1f;
            range = 100f;
        }});
        ((ItemTurret) Blocks.duo).ammoTypes.put(TItem.beeq, new BasicBulletType(2.3f, 0) {{
            lifetime = ((ItemTurret) Blocks.duo).range / speed + 10;
            healAmount = 1;
            healPercent = 100;
            homingPower = 1;
            homingRange = 100;
            collidesTiles = collidesTeam = true;
        }});

        ((HeaterGenerator)Blocks.neoplasiaReactor).outputLiquid = new LiquidStack(TLiquids.radioactive_waste, 0.33333334F);

        //DISABLED
        ((NuclearReactor)Blocks.thoriumReactor).powerProduction = 0;

        //Blocks.steamGenerator.buildVisibility = BuildVisibility.editorOnly;
    }
}