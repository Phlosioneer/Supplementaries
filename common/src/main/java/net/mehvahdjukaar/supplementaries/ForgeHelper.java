package net.mehvahdjukaar.supplementaries;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.mehvahdjukaar.supplementaries.common.world.explosion.GunpowderExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ForgeHelper {

    @ExpectPlatform
    public static boolean canEntityDestroy(Level level, BlockPos blockPos, Animal animal) {
        throw new AssertionError();
    }
    //TODO: fabric
    @ExpectPlatform
    public static void openContainerScreen(ServerPlayer player, MenuProvider menuProvider, BlockPos pos) {
        throw new AssertionError();
    }

    // TODO: fabric
    @ExpectPlatform
    public static boolean onExplosionStart(Level level, Explosion explosion) {
        throw new AssertionError();
    }
    @ExpectPlatform
    public static void onLivingConvert(LivingEntity from, LivingEntity to) {
        throw new AssertionError();
    }
    @ExpectPlatform
    public static boolean canLivingConvert(LivingEntity entity, EntityType<? extends LivingEntity> outcome, Consumer<Integer> timer) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void onExplosionDetonate(Level level, Explosion explosion, List<Entity> entities, double diameter) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static double getReachDistance(LivingEntity entity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static float getExplosionResistance(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void onBlockExploded(BlockState blockstate, Level level, BlockPos blockpos, Explosion explosion) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean areStacksEqual(ItemStack stack, ItemStack other, boolean sameNbt) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isFireSource(BlockState blockState, Level level, BlockPos pos, Direction up) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean canDropFromExplosion(BlockState blockstate, Level level, BlockPos blockpos, Explosion explosion) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isDye(ItemStack itemstack) {
        throw new AssertionError();
    }
    @Nullable
    @ExpectPlatform
    public static DyeColor getColor(ItemStack stack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static EntityType<?> getFishType(MobBucketItem bucketItem) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static BlockState rotateBlock(BlockState state, Level world, BlockPos targetPos, Rotation rot) {
        throw new AssertionError();
    }
}
