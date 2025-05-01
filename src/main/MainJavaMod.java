package main;

import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import content.blocks.Blocks;
import content.items.Items;
import content.liquids.Liquids;
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
                dialog.cont.add("Стой!").row();
                dialog.cont.add("Это великий и могучий ТетсКрафт").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("tets-craft-mod-items-poop")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Liquids.load();
        Items.load();
        Blocks.load();

        Log.info("Loading complete;");
    }



}
