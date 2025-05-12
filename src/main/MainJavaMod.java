package main;

import arc.Core;
import arc.Events;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.graphics.TextureData;
import arc.graphics.g2d.TextureRegion;
import arc.scene.style.BaseDrawable;
import arc.scene.style.Drawable;
import arc.scene.style.TextureRegionDrawable;
import arc.util.Log;
import arc.util.Time;
import content.blocks.LiquidAccurateSolarCrafter;
import content.blocks.SolarHeatProducer;
import content.blocks.TBlocks;
import content.items.TItems;
import content.liquids.TLiquids;
import content.units.TUnits;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.gen.Icon;
import mindustry.logic.LCategory;
import mindustry.mod.Mod;
import mindustry.type.Category;
import mindustry.ui.Fonts;
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
        //TextureRegionDrawable steamIcon = Fonts.getGlyph(Fonts.icon, '☁');
        //Icon.icons.put("steam", steamIcon);

        LiquidAccurateSolarCrafter.LiquidAccurateSolarCrafterBuild.create();
        SolarHeatProducer.SolarHeatProducerBuild.create();

        TLiquids.load();
        TItems.load();
        TUnits.init();
        TBlocks.load();

        Log.info("Loading complete;");
    }



}
