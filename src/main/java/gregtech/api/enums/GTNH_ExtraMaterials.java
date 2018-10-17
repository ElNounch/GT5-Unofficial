package gregtech.api.enums;

import gregtech.api.interfaces.IMaterialHandler;
import static gregtech.GT_Mod.GT_FML_LOGGER;

public class GTNH_ExtraMaterials implements IMaterialHandler {

    public GTNH_ExtraMaterials(){
        GT_FML_LOGGER.info("Registering GTNH-Materials (post Java 64kb limit)");
        Materials.add(this);
    }

    /**
     * This Class is for adding new Materials since Java has a Limiation of 64kb per Method / Class header
     */

    public static Materials Signalum                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "Signalum"                ,   "Signalum"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Lumium                  = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "Lumium"                  ,   "Lumium"                        ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials EnrichedCopper          = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "EnrichedCopper"          ,   "Enriched Copper"               ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials DiamondCopper           = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "DiamondCopper"           ,   "Diamond Copper"                ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials TarPitch                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2, 1|2                       , 255, 255, 255,   0,   "TarPitch"                ,   "Tar Pitch"                     ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials LimePure                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  0, 0                         , 255, 255, 255,   0,   "LimePure"                ,   "Pure Lime"                     ,    0,       0,         -1,    0, false, false,   1,   1,   1, Dyes.dyeLime        );
    public static Materials Wimalite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2,       8                   , 255, 255, 255,   0,   "Wimalite"                ,   "Wimalite"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeYellow      );
    public static Materials Yellorite               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  2,       8                   , 255, 255, 255,   0,   "Yellorite"               ,   "Yellorite"                     ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeYellow      );
    public static Materials Quantum                 = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  0, 0                         , 255, 255, 255,   0,   "Quantum"                 ,   "Quantum"                       ,    0,       0,         -1,    0, false, false,   1,   1,   1, Dyes.dyeWhite       );
    public static Materials Turquoise               = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1                         , 255, 255, 255,   0,   "Turquoise"               ,   "Turquoise"                     ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tapazite                = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1                         , 255, 255, 255,   0,   "Tapazite"                ,   "Tapazite"                      ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes.dyeGreen       );
    public static Materials Thyrium                 = new Materials(  -1, TextureSet.SET_NONE              ,   1.0F,      0,  1, 1|2  |8                   , 255, 255, 255,   0,   "Thyrium"                 ,   "Thyrium"                       ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );
    public static Materials Tourmaline              = new Materials(  -1, TextureSet.SET_RUBY              ,   1.0F,      0,  1, 1                         , 255, 255, 255,   0,   "Tourmaline"              ,   "Tourmaline"                    ,    0,       0,         -1,    0, false, false,   3,   1,   1, Dyes._NULL          );

    private static void initSubTags() {
        SubTag.METAL.addTo(Signalum, Lumium, EnrichedCopper, DiamondCopper);
        SubTag.NO_SMASHING.addTo(TarPitch);
    }

    @Override
    public void onMaterialsInit() {
        initSubTags();
    }

    @Override
    public void onComponentInit() {
    }

    @Override
    public void onComponentIteration(Materials aMaterial) {

    }
}