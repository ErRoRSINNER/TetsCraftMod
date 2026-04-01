package util;

import multicraft.IOEntry;
import multicraft.Recipe;

public class RecipeWrapper {

    public static Recipe of(IOEntry in, IOEntry out, float time){
        return new Recipe(){
            {
                this.input = in;
                this.output = out;
                this.craftTime = time;
            }
        };
    }
}
