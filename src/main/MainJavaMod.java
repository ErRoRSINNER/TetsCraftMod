package main;

import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import content.blocks.LiquidAccurateSolarCrafter;
import content.blocks.TBlocks;
import content.items.TItems;
import content.liquids.TLiquids;
import content.units.TUnits;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;

public class MainJavaMod extends Mod{

    public MainJavaMod(){
        Log.info("Loaded ExampleJavaMod constructor.");
        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("tets");
                dialog.cont.add("Stop!").row();
                dialog.cont.add("Its great, great, great, great TetsCraft2!!!").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("tets-craft-mod-poop")).pad(20f).row();
                dialog.cont.button("Yes Sir!", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        LiquidAccurateSolarCrafter.CustomSolarCrafterBuild.create();

        TLiquids.load();
        TItems.load();
        TUnits.init();
        TBlocks.load();

        Log.info("Loading complete;");
    }



}
