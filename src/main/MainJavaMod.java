package main;

import arc.*;
import arc.util.*;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;
import mindustry.ui.dialogs.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawLiquidTile;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;

import static mindustry.world.meta.Env.*;

public class MainJavaMod extends Mod{

    public static Block testBlock;

    public MainJavaMod(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("tets-craft-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    public static void ini() {
        testBlock = new GenericCrafter("oxygen-liquifier") {{
            hasPower = hasItems = hasLiquids = solid =  outputsLiquid = true;
            rotate = false;

            size = 2;
            liquidCapacity = 30f;
            craftTime = 240;
            envEnabled = any;
            outputLiquid = new LiquidStack(Liquids.water, 15f / 60f);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.water) {{
                drawLiquidLight = true;
            }}, new DrawDefault(), new DrawRegion("-top"));

            consumePower(2.5f);
            consumeItem(Items.coal);
            consumeLiquid(Liquids.water, 10f / 60f);
            requirements(Category.crafting, ItemStack.with(Items.lead, 110, Items.silicon, 65, Items.graphite, 35, Items.titanium, 25));
        }};
    }

    @Override
    public void loadContent(){

        Log.info("Loading some example content.");
    }



}
