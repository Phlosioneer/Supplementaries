package net.mehvahdjukaar.supplementaries.mixins.forge;

import net.mehvahdjukaar.supplementaries.configs.CommonConfigs;
import net.mehvahdjukaar.supplementaries.integration.CompatHandler;
import net.mehvahdjukaar.supplementaries.integration.QuarkCompat;
import net.mehvahdjukaar.supplementaries.integration.forge.quark.CartographersQuillItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExplorationMapFunction.class)
public abstract class ExplorationMapFunctionMixin {

    @Shadow @Final
    TagKey<Structure> destination;

    @Shadow @Final
    byte zoom;

    @Shadow @Final
    MapDecoration.Type mapDecoration;

    @Shadow @Final
    int searchRadius;

    @Shadow @Final
    boolean skipKnownStructures;

    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    public void turnToQuill(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir){
        if(CompatHandler.QUARK && CommonConfigs.Tweaks.REPLACE_VANILLA_MAPS.get()){
            cir.setReturnValue(QuarkCompat.makeAdventurerQuill(context.getLevel(), this.destination,
                    Math.max(CommonConfigs.Tweaks.QUILL_MIN_SEARCH_RADIUS.get(), this.searchRadius),
                    this.skipKnownStructures, this.zoom, this.mapDecoration, null, 0));
        }
    }
}