package com.fish.lucidremedy.block.custom;

import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AttributeDependentBlock extends Block {

    final public Holder<Attribute> attribute;

    public AttributeDependentBlock(Properties properties, Holder<Attribute> attribute) {
        super(properties);
        this.attribute = attribute;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext && entityContext.getEntity() instanceof LivingEntity living) {
            var instance = living.getAttribute(attribute);
            if (instance != null && instance.getValue() > 0.0) {
                return Shapes.block();
            }
        }
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext && entityContext.getEntity() instanceof LivingEntity living) {
            var instance = living.getAttribute(attribute);
            if (instance != null && instance.getValue() > 0.0) {
                return Shapes.block();
            }
            return Shapes.empty();
        }
        return Shapes.block();
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
                return Shapes.block();
            }
            return Shapes.empty();
        }
        return Shapes.block();
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
}
