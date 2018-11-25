package me.bristermitten.tribal.world;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class TribalWorldGenerator extends ChunkGenerator {
    private int currentHeight;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        generator.setScale(0.005D);

        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                currentHeight = (int) ((generator.noise((chunkX << 4) + X, (chunkZ << 4) + Z, 0.5D, 0.5D, true) + 1) * 15D + 50D);
                if (random.nextBoolean())
                    chunk.setBlock(X, currentHeight, Z, Material.DIAMOND_BLOCK);
                else chunk.setBlock(X, currentHeight, Z, Material.AIR);
                chunk.setBlock(X, currentHeight - 1, Z, Material.DIRT);
                for (int i = currentHeight - 2; i > 0; i--)
                    chunk.setBlock(X, i, Z, Material.STONE);
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        return chunk;
    }
}
