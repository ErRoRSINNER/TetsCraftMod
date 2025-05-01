package content.blocks;

import mindustry.world.Block;

public class Blocks{
    public static Block gomodrill
            ;

    public static void load(){
        gomodrill = new Block("gomodrill") {
            {
                rotate = false;
                size = 2;
            }
        };
    }
}
