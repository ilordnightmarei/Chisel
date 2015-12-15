package team.chisel.api.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.chisel.api.render.ChiselFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data about a chisel block, contains all the data from the json
 */
public class ChiselBlockData {

    /**
     * Name of this block
     */
    public String name;
    /**
     * A List of all the variations of this block
     */
    public VariationData[] variations;

    /**
     * The Mod or map that owns this block, if its a mod this should be the modid, for ones added by chisel this is just "chisel"
     */
    public String owner;

    /**
     * The Material for this block
     */
    public Material material;

    /**
     * The Blocks Hardness
     */
    public float hardness;

    /**
     * Whether the block is an opaque cube
     */
    public boolean isOpaqueCube;

    /**
     * Whether this block can be used to construct a beacon base
     */
    public boolean beaconBase;

    @SideOnly(Side.CLIENT)
    private List<EnumWorldBlockLayer> layers;




    protected ChiselBlockData(String name, VariationData[] variations, String owner, Material material, float hardness,
                              boolean opaqueCube, boolean beaconBase){
        this.name = name;
        this.variations = variations;
        this.owner = owner;
        this.material = material;
        this.hardness = hardness;
        this.isOpaqueCube = opaqueCube;
        this.beaconBase = beaconBase;
    }

    public VariationData getVariation(int meta){
        return this.variations[MathHelper.clamp_int(meta,
                0, variations.length)];
    }

    @SideOnly(Side.CLIENT)
    public boolean isValid(EnumWorldBlockLayer layer){
        if (this.layers == null){
            this.layers = new ArrayList<EnumWorldBlockLayer>();
            for (VariationData data : this.variations){
                if (data instanceof ClientVariationData){
                    ClientVariationData cData = (ClientVariationData) data;
                    for (ChiselFace face : cData.getAllFaces()){
                        if (!this.layers.contains(face.getLayer())){
                            this.layers.add(face.getLayer());
                        }
                    }
                }
            }
        }
        return this.layers.contains(layer);
    }




}