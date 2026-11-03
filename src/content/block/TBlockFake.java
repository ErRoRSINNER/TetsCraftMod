package content.block;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.SolarGenerator;

public class TBlockFake {
    // Fake 2 by 2
    public static Block fakeCopperWallLarge, fakeTitaniumWallLarge, fakeThoriumWallLarge, fakeGraphitePress, fakeKiln, fakeMechanicalDrill, fakePneumaticDrill,
            fakePlastaniumCompressor, fakeSiliconSmelter, fakeSteamGenerator, fakeThermalGenerator, fakeLancer, fakeScatter;

    // Fake 3 by 3
    public static Block fakeCoreShard, fakeBatteryLarge, fakeMultiPress, fakeSiliconCrucible, fakeRipple, fakeFuse, fakeThoriumReactor, fakeSolarPanelLarge;

    protected static void loadFake() {
        fakeCopperWallLarge = new Wall("fake_copper_wall_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeTitaniumWallLarge = new Wall("fake_titanium_wall_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeThoriumWallLarge = new Wall("fake_thorium_wall_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeMechanicalDrill = new Wall("fake_mechanical_drill") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakePneumaticDrill = new Wall("fake_pneumatic_drill") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeGraphitePress = new Wall("fake_graphite_press") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            consumePower(0.05F);
        }};
        fakeKiln = new Wall("fake_kiln") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            consumePower(0.05F);
        }};
        fakePlastaniumCompressor = new Wall("fake_plastanium_compressor") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            consumePower(0.05F);
        }};
        fakeSiliconSmelter = new Wall("fake_silicon_smelter") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            consumePower(0.05F);
        }};
        fakeSteamGenerator = new Wall("fake_steam_generator") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            consumePower(0.05F);
        }};
        fakeThermalGenerator = new Wall("fake_thermal_generator") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            consumePower(0.05F);
        }};
        fakeLancer = new PowerTurret("fake_lancer") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            range = 165.0F;
            shoot.firstShotDelay = 999999.0F;
            recoil = 0.0F;
            reload = 999999.0F;
            shake = 2.0F;
            targetAir = false;
            moveWhileCharging = false;
            consumePower(0.05F);
            shootEffect = Fx.none;
            smokeEffect = Fx.none;
            shootType = new LaserBulletType(0.0F) {{
                shootEffect = Fx.none;
                smokeEffect = Fx.none;
                hitEffect = Fx.none;
                chargeEffect = Fx.none;
            }};
        }};
        fakeScatter = new PowerTurret("fake_scatter") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            size = 2;
            alwaysUnlocked = true;
            range = 220.0F;
            shoot.firstShotDelay = 999999.0F;
            recoil = 0.0F;
            reload = 999999.0F;
            targetAir = true;
            targetGround = false;
            consumePower(0.05F);
            shootEffect = Fx.none;
            smokeEffect = Fx.none;
            shootType = new LaserBulletType(0.0F) {{
                shootEffect = Fx.none;
                smokeEffect = Fx.none;
                hitEffect = Fx.none;
                chargeEffect = Fx.none;
            }};
        }};
        //

        fakeBatteryLarge = new Battery("fake_battery_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            size = 3;
            consumePowerBuffered(0.0F);
            baseExplosiveness = 0.0F;
        }};
        fakeSolarPanelLarge = new SolarGenerator("fake_solar_panel_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            size = 3;
            consumePowerBuffered(0.0F);
            baseExplosiveness = 0.0F;
        }};
        fakeCoreShard = new Wall("fake_core_shard") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            itemCapacity = 0;
        }};
        fakeMultiPress = new Wall("fake_multi_press") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            consumePower(0.05F);
        }};
        fakeSiliconCrucible = new Wall("fake_silicon_crucible") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            consumePower(0.05F);
        }};
        fakeThoriumReactor = new Wall("fake_thorium_reactor") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            consumePower(0.05F);
        }};
        fakeFuse = new PowerTurret("fake_fuse") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            size = 3;
            alwaysUnlocked = true;
            range = 90.0F;
            shoot.firstShotDelay = 999999.0F;
            recoil = 0.0F;
            reload = 999999.0F;
            targetAir = true;
            targetGround = true;
            consumePower(0.05F);
            shootEffect = Fx.none;
            smokeEffect = Fx.none;
            shootType = new LaserBulletType(0.0F) {{
                shootEffect = Fx.none;
                smokeEffect = Fx.none;
                hitEffect = Fx.none;
                chargeEffect = Fx.none;
            }};
        }};
        fakeRipple = new PowerTurret("fake_ripple") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            size = 3;
            alwaysUnlocked = true;
            range = 290.0F;
            shoot.firstShotDelay = 999999.0F;
            recoil = 0.0F;
            reload = 999999.0F;
            targetAir = false;
            targetGround = true;
            consumePower(0.05F);
            shootEffect = Fx.none;
            smokeEffect = Fx.none;
            shootType = new LaserBulletType(0.0F) {{
                shootEffect = Fx.none;
                smokeEffect = Fx.none;
                hitEffect = Fx.none;
                chargeEffect = Fx.none;
            }};
        }};
    }
}
