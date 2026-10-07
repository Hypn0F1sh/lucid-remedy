package com.fish.lucidremedy.block.custom;

import com.fish.lucidremedy.tags.ModItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class AttributeDependentUntintedParticleLeavesBlock extends UntintedParticleLeavesBlock {

    final public Holder<Attribute> attribute;

    public AttributeDependentUntintedParticleLeavesBlock(float leafParticleChance, ParticleOptions leafParticle, Properties properties, Holder<Attribute> attribute) {
        super(leafParticleChance, leafParticle, properties);
        this.attribute = attribute;
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            if (entityContext.getEntity() instanceof LivingEntity living) {
                var instance = living.getAttribute(attribute);
                if (instance != null && instance.getValue() > 0.0) {
                    return super.getCollisionShape(state, level, pos, context);
                }
            } else if (entityContext.getEntity() instanceof ItemEntity item) {
                if (item.getItem().is(ModItemTags.GHOST_ITEM)) {
                    return super.getCollisionShape(state, level, pos, context);
                }
            }
        }
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext && entityContext.getEntity() instanceof LivingEntity living) {
            var instance = living.getAttribute(attribute);
            if (instance != null && instance.getValue() > 0.0) {
                return super.getShape(state, level, pos, context);
            }
            return Shapes.empty();
        }
        return super.getShape(state, level, pos, context);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext && entityContext.getEntity() instanceof LivingEntity living) {
            var instance = living.getAttribute(attribute);
            if (instance != null && instance.getValue() > 0.0) {
                return super.getVisualShape(state, level, pos, context);
            }
            return Shapes.empty();
        }
        return super.getVisualShape(state, level, pos, context);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathType) {
        return true;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if (context.getPlayer() instanceof LivingEntity living) {
            var instance = living.getAttribute(attribute);

            if (instance != null && instance.getValue() > 0.0) {
                return super.canBeReplaced(state, context);
            }
        }

        return true;
    }

    @Override
    protected @NotNull VoxelShape getOcclusionShape(@NotNull BlockState state) {
        var client = Minecraft.getInstance();
        if (client != null) {
            var player = client.player;
            if (player != null) {
                var instance = player.getAttribute(attribute);
                if (instance != null && instance.getValue() > 0.0) {
                    return super.getOcclusionShape(state);
                }
            }
        }
        return Shapes.empty();
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
        var client = Minecraft.getInstance();
        if (client != null) {
            var player = client.player;
            if (player != null) {
                var instance = player.getAttribute(attribute);
                if (instance != null && instance.getValue() > 0.0) {
                    super.spawnFallingLeavesParticle(level, pos, random);
                }
            }
        }
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        var client = Minecraft.getInstance();
        if (client != null) {
            var player = client.player;
            if (player != null) {
                var instance = player.getAttribute(attribute);
                if (instance != null && instance.getValue() > 0.0) {
                    return super.propagatesSkylightDown(state);
                }
            }
        }
        return true;
    }

    @Override
    protected int getLightDampening(BlockState state) {
        var client = Minecraft.getInstance();
        if (client != null) {
            var player = client.player;
            if (player != null) {
                var instance = player.getAttribute(attribute);
                if (instance != null && instance.getValue() > 0.0) {
                    return super.getLightDampening(state);
                }
            }
        }
        return 0;
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected boolean isAir(BlockState state) {
        var client = Minecraft.getInstance();
        if (client != null) {
            var player = client.player;
            if (player != null) {
                var instance = player.getAttribute(attribute);
                if (instance != null && instance.getValue() > 0.0) {
                    return super.isAir(state);
                }
            }
        }
        return true;
    }

    public Holder<Attribute> getAttribute() {
        return attribute;
    }
}
