/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import org.drools.minecraft.model.Mob;
import org.drools.minecraft.model.Mob.MobTypes;

/**
 *
 * @author Samuel
 */
public class MobFactory {

    public static final Map<MobTypes, Class<? extends EntityLiving>> mobClasses = new HashMap<Mob.MobTypes, Class<? extends EntityLiving>>();
    public static final Map<Class<? extends EntityLiving>, MobTypes> classMobses = new HashMap<Class<? extends EntityLiving>, MobTypes>();
    
    static {
        {
            mobClasses.put(MobTypes.BAT, EntityBat.class);
            mobClasses.put(MobTypes.CHICKEN, EntityChicken.class);
            mobClasses.put(MobTypes.COW, EntityCow.class);
            mobClasses.put(MobTypes.MOOSHROOM, EntityMooshroom.class);
            mobClasses.put(MobTypes.PIG, EntityPig.class);
            mobClasses.put(MobTypes.RABBIT, EntityRabbit.class);
            mobClasses.put(MobTypes.SHEEP, EntitySheep.class);
            mobClasses.put(MobTypes.SQUID, EntitySquid.class);
            mobClasses.put(MobTypes.VILLAGER, EntityVillager.class);
            mobClasses.put(MobTypes.POISON_SPIDER, EntityCaveSpider.class);
            mobClasses.put(MobTypes.ENDERMAN, EntityEnderman.class);
            mobClasses.put(MobTypes.SPIDER, EntitySpider.class);
            mobClasses.put(MobTypes.ZOMBIE_PIGMAN, EntityPigZombie.class);
            mobClasses.put(MobTypes.BLAZE, EntityBlaze.class);
            mobClasses.put(MobTypes.CREEPER, EntityCreeper.class);
            mobClasses.put(MobTypes.ELDER_GUARDIAN, EntityGuardian.class);
            mobClasses.put(MobTypes.ENDERMITE, EntityEndermite.class);
            mobClasses.put(MobTypes.GHAST, EntityGhast.class);
            mobClasses.put(MobTypes.GUARDIAN, EntityGuardian.class);
            mobClasses.put(MobTypes.MAGMA_CUBE, EntityMagmaCube.class);
            mobClasses.put(MobTypes.SILVERFISH, EntitySilverfish.class);
            mobClasses.put(MobTypes.SKELETON, EntitySkeleton.class);
            mobClasses.put(MobTypes.SLIME, EntitySlime.class);
            mobClasses.put(MobTypes.WITCH, EntityWitch.class);
            mobClasses.put(MobTypes.WITHER_SKELETON, EntitySkeleton.class);
            mobClasses.put(MobTypes.ZOMBIE, EntityZombie.class);
            mobClasses.put(MobTypes.ZOMBIE_VILLAGER, EntityZombie.class);
            mobClasses.put(MobTypes.DONKEY, EntityHorse.class);
            mobClasses.put(MobTypes.HORSE, EntityHorse.class);
            mobClasses.put(MobTypes.MULE, EntityHorse.class);
            mobClasses.put(MobTypes.OCELOT, EntityOcelot.class);
            mobClasses.put(MobTypes.WOLF, EntityWolf.class);
            mobClasses.put(MobTypes.IRON_GOLEM, EntityGolem.class);
            mobClasses.put(MobTypes.SNOW_GOLEM, EntitySnowman.class);
            mobClasses.put(MobTypes.ENDER_DRAGON, EntityDragon.class);
            mobClasses.put(MobTypes.WITHER, EntityWither.class);
        }
    }
    
        static {
        {
            classMobses.put(EntityBat.class, MobTypes.BAT);
            classMobses.put(EntityChicken.class, MobTypes.CHICKEN);
            classMobses.put(EntityCow.class, MobTypes.COW);
            classMobses.put(EntityMooshroom.class, MobTypes.MOOSHROOM);
            classMobses.put(EntityPig.class, MobTypes.PIG);
            classMobses.put(EntityRabbit.class, MobTypes.RABBIT);
            classMobses.put(EntitySheep.class, MobTypes.SHEEP);
            classMobses.put(EntitySquid.class, MobTypes.SQUID);
            classMobses.put(EntityVillager.class, MobTypes.VILLAGER);
            classMobses.put(EntityEnderman.class, MobTypes.ENDERMAN);
            classMobses.put(EntitySpider.class, MobTypes.SPIDER);
            classMobses.put(EntityCaveSpider.class, MobTypes.POISON_SPIDER);
            classMobses.put(EntityPigZombie.class, MobTypes.ZOMBIE_PIGMAN);
            classMobses.put(EntityBlaze.class, MobTypes.BLAZE);
            classMobses.put(EntityCreeper.class, MobTypes.CREEPER);
            classMobses.put(EntityGuardian.class, MobTypes.ELDER_GUARDIAN);
            classMobses.put(EntityEndermite.class, MobTypes.ENDERMITE);
            classMobses.put(EntityGhast.class, MobTypes.GHAST);
            classMobses.put(EntityGuardian.class, MobTypes.GUARDIAN);
            classMobses.put(EntityMagmaCube.class, MobTypes.MAGMA_CUBE);
            classMobses.put(EntitySilverfish.class, MobTypes.SILVERFISH);
            classMobses.put(EntitySkeleton.class, MobTypes.SKELETON);
            classMobses.put(EntitySlime.class, MobTypes.SLIME);
            classMobses.put(EntityWitch.class, MobTypes.WITCH);
            classMobses.put(EntityZombie.class, MobTypes.ZOMBIE);
            classMobses.put(EntityHorse.class, MobTypes.HORSE);
            classMobses.put(EntityOcelot.class, MobTypes.OCELOT);
            classMobses.put(EntityWolf.class, MobTypes.WOLF);
            classMobses.put(EntityGolem.class, MobTypes.IRON_GOLEM);
            classMobses.put(EntitySnowman.class, MobTypes.SNOW_GOLEM);
            classMobses.put(EntityDragon.class, MobTypes.ENDER_DRAGON);
            classMobses.put(EntityWither.class, MobTypes.WITHER);
        }
    }

        
    //TODO: Add cases for variant mobs, IE skeleton and wither skeleton
    /**
     * Generates a new creature given a mobtype. If the appropriate creature is not
     * found, returns null.
     * @param type
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public static EntityLiving newCreature(MobTypes type, World world) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException
    {
        Class referencedClass = mobClasses.get(type);
        EntityLiving newCreature;
        
        if(referencedClass == null)
        {
            System.out.println("ERROR: You tried to acess an unknown mob from the MobFactory. Are you using modded entities?");
            return null;
        }else
        {
            newCreature = (EntityLiving) referencedClass.getDeclaredConstructor(World.class).newInstance(world);
            
            if(type == MobTypes.DONKEY){
                ((EntityHorse)newCreature).setHorseType(1);
            }else if(type == MobTypes.MULE){
                ((EntityHorse)newCreature).setHorseType(2);
            }else if(type == MobTypes.ELDER_GUARDIAN){
                ((EntityGuardian)newCreature).setElder();
            }else if(type == MobTypes.WITHER_SKELETON){
                ((EntitySkeleton)newCreature).setSkeletonType(1);
            }
            
        }
        
        return newCreature;
    }
    
    //TODO: Add cases for variant mobs, IE skeleton and wither skeleton
    /**
     * Generates a new mobtype given a creature, if none is found, returns UNKNOWN.
     * @param type
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public static MobTypes newMobType(EntityLiving entity) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        MobTypes referencedType = classMobses.get(entity.getClass());
        
        if(referencedType == null)
        {
            System.out.println("ERROR: You tried to acess an unknown mob from the MobFactory. Are you using modded entities?");
            return null;
        }
        
        if(entity instanceof EntityHorse){
            if(((EntityHorse)entity).getHorseType() == 1){
                referencedType = MobTypes.DONKEY;
            }else if(((EntityHorse)entity).getHorseType() == 2){
                referencedType = MobTypes.MULE;
            }
        }else if(entity instanceof EntityGuardian){
            if(((EntityGuardian)entity).isElder()){
                referencedType = MobTypes.ELDER_GUARDIAN;
            }
        }else if(entity instanceof EntitySkeleton){
            if(((EntitySkeleton)entity).getSkeletonType() == 1)
            {
                referencedType = MobTypes.WITHER_SKELETON;
            }
        }
        
        return referencedType;
    }
}
