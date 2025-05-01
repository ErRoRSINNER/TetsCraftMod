package content.blocks;

import mindustry.world.Block;

public class Blocks{
    public static Block testBlock
            ;


    public static void load(){
        testBlock = new Block("test_block") {
            {
                rotate = false;
                size = 2;
            }
        };
    }
}
