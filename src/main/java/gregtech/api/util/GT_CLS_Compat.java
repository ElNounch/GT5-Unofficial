package gregtech.api.util;

import cpw.mods.fml.common.ProgressManager;
import gregtech.GT_Mod;
import gregtech.api.enums.Materials;
import gregtech.common.GT_Proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("rawtypes, unchecked, deprecation")
public class GT_CLS_Compat {

    private static Class alexiilMinecraftDisplayer;
    private static Class alexiilProgressDisplayer;

    private static Method getLastPercent;
    private static Method displayProgressObject;
    private static Method displayProgressString;

    private static Field isReplacingVanillaMaterials;
    private static Field isRegisteringGTmaterials;

    static {
        //CLS
        try {
            alexiilMinecraftDisplayer = Class.forName("alexiil.mods.load.MinecraftDisplayer");
            alexiilProgressDisplayer = Class.forName("alexiil.mods.load.ProgressDisplayer");
        } catch (ClassNotFoundException ex) {
            GT_Mod.GT_FML_LOGGER.catching(ex);
        }

        Optional.ofNullable(alexiilMinecraftDisplayer).ifPresent(e -> {
            try {
                getLastPercent = e.getMethod("getLastPercent");
                isReplacingVanillaMaterials = e.getField("isReplacingVanillaMaterials");
                isRegisteringGTmaterials = e.getField("isRegisteringGTmaterials");
            } catch (NoSuchMethodException | NoSuchFieldException ex) {
                GT_Mod.GT_FML_LOGGER.catching(ex);
            }

        });

        Optional.ofNullable(alexiilProgressDisplayer).ifPresent(e -> {
            try {
                displayProgressObject = e.getMethod("displayProgress", Object.class, float.class);
                displayProgressString = e.getMethod("displayProgress", String.class, float.class);
            } catch (NoSuchMethodException ex) {
                GT_Mod.GT_FML_LOGGER.catching(ex);
            }
        });
    }

    private static void _displayProgress(Object what, float pct) throws IllegalAccessException, InvocationTargetException {
        if (displayProgressObject != null) {
            displayProgressObject.invoke(null, what, pct);
        } else if (displayProgressString != null) {
            displayProgressString.invoke(null, what.toString(), pct);
        }
    }

    public static void stepMaterialsCLS(Collection<GT_Proxy.OreDictEventContainer> mEvents) throws IllegalAccessException, InvocationTargetException {
        int sizeStep = GT_CLS_Compat.setStepSize(mEvents);
        int size = 0;
        for (GT_Proxy.OreDictEventContainer tEvent : mEvents) {
            sizeStep--;

            _displayProgress(tEvent.mMaterial, ((float) size) / 100);

            if (sizeStep == 0) {
                if (size % 5 == 0)
                    GT_Mod.GT_FML_LOGGER.info("Baking: " + size + "%");
                sizeStep = mEvents.size() / 100 - 1;
                size++;
            }

            GT_Proxy.registerRecipes(tEvent);
        }
        isRegisteringGTmaterials.set(null, false);
    }


    public static int setStepSize(Collection mEvents) {
        try {
            isRegisteringGTmaterials.set(null, true);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            GT_Mod.GT_FML_LOGGER.catching(e);
        }

        return mEvents.size() / 100 - 1;
    }

    private GT_CLS_Compat() {
    }

    private static int[] setSizeSteps(Set<Materials> replaceVanillaItemsSet){
        int sizeStep;
        int sizeStep2;
        if (replaceVanillaItemsSet.size() >= 100) {
            sizeStep = replaceVanillaItemsSet.size() / 100 - 1;
            sizeStep2 = 1;
        } else {
            sizeStep = 100 / replaceVanillaItemsSet.size();
            sizeStep2 = sizeStep;
        }
        return new int[]{sizeStep, sizeStep2};
    }

    private static void displayMethodAdapter(int counter, String mDefaultLocalName, int size) throws InvocationTargetException, IllegalAccessException {
        if (counter == 1) {
            _displayProgress(mDefaultLocalName, ((float) 95) / 100);
        } else if (counter == 0) {
            _displayProgress(mDefaultLocalName, (float) 1);
        } else {
            _displayProgress(mDefaultLocalName, ((float) size) / 100);
        }
    }

    public static void doActualRegistrationCLS(ProgressManager.ProgressBar progressBar, Set<Materials> replaceVanillaItemsSet) throws InvocationTargetException, IllegalAccessException {
        int size = 0;
        int counter = replaceVanillaItemsSet.size();
        try {
            isReplacingVanillaMaterials.set(null, true);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            GT_Mod.GT_FML_LOGGER.catching(e);
        }

        int[] sizeSteps = setSizeSteps(replaceVanillaItemsSet);

        for (Materials m : replaceVanillaItemsSet) {
            counter--;
            sizeSteps[0]--;

            displayMethodAdapter(counter,m.mDefaultLocalName,size);
            GT_Mod.doActualRegistration(m);

            size += sizeSteps[1];
            progressBar.step(m.mDefaultLocalName);
        }
    }

    public static void pushToDisplayProgress() throws InvocationTargetException, IllegalAccessException {
        isReplacingVanillaMaterials.set(null, false);
        _displayProgress("Post Initialization: loading GregTech", (float) getLastPercent.invoke(null));
    }

}
