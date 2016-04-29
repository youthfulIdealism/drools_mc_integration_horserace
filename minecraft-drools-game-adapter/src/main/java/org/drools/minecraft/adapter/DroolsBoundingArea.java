package org.drools.minecraft.adapter;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
/**
 * 
 * Probably depricate this. We'll see.
 * @author Samuel
 */
public class DroolsBoundingArea {

    AxisAlignedBB bounds;
    List<Entity> creatures;
    String name;

    public DroolsBoundingArea(String name, int startx, int starty, int startz, int endx, int endy, int endz) {
        //NOT IMPLEMENTED!
        /*bounds = AxisAlignedBB.fromBounds(startx, starty, startz, endx, endy, endz);
		creatures = RulesDriver.world.getEntitiesWithinAABB(Entity.class, bounds);
		*/
        this.name = name;
    }

    public boolean containsEntityOfType(Class<?> classtype) {
        //NOT IMPLEMENTED!
        /*for(Entity Entity : creatures)
		{
			if(classtype.isInstance(Entity))
			{
				return true;
			}
		}
         */
        return false;
    }

    public List<Entity> getEntitiesOfType(Class<?> classtype) {
        //NOT IMPLEMENTED!
        /*ArrayList<Entity> returnable = new ArrayList<Entity>();
        for (Entity entity : creatures) {
            if (classtype.isInstance(entity)) {
                returnable.add(entity);
            }
        }
        return returnable;*/
        return null;
    }

    public void reset() {
        //creatures = RulesDriver.world.getEntitiesWithinAABB(Entity.class, bounds);
    }

    public AxisAlignedBB getBounds() {
        return null;
        //return bounds;
    }

    public void setBounds(AxisAlignedBB bounds) {
        //this.bounds = bounds;
    }

    public List<Entity> getCreatures() {
        return null;
        //return creatures;
    }

    public void setCreatures(List<Entity> creatures) {
        //this.creatures = creatures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
