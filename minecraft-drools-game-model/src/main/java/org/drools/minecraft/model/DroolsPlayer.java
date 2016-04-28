package org.drools.minecraft.model;


/**
 * Stub class used to wrap the player.
 * 
 * //TODO: research viability of replacing EntityPlayer with EntityPlayerMP
 * @author Samuel
 *
 */
public class DroolsPlayer
{
	int xloc;
        int yloc;
        int zloc;
	
	public DroolsPlayer()
	{
		
	}

    public int getXloc() {
        return xloc;
    }

    public void setXloc(int xloc) {
        this.xloc = xloc;
    }

    public int getYloc() {
        return yloc;
    }

    public void setYloc(int yloc) {
        this.yloc = yloc;
    }

    public int getZloc() {
        return zloc;
    }

    public void setZloc(int zloc) {
        this.zloc = zloc;
    }

	
}
