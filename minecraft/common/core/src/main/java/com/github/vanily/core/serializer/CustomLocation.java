package com.github.vanily.core.serializer;

import javax.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

@Data
@Builder
public final class CustomLocation {

    private final String worldName;
    private final double x, y, z;
    private float yaw, pitch;

    public static CustomLocation of(Location location) {
        return of(location, false);
    }

    public static CustomLocation of(Block block) {
        final Location location = block.getLocation();
        return of(location, false);
    }

    public String toString() {
        return this.worldName + "@" + this.x + "@" + this.y + "@" + this.z + "@" + this.yaw + "@" + this.pitch;
    }

    public static CustomLocation of(String input) {
        String[] parts = input.split("@");
        return new CustomLocation(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Float.parseFloat(parts[4]), Float.parseFloat(parts[5]));
    }

    public static CustomLocation of(Player player) {
        final Location location = player.getLocation();
        return of(location, false);
    }

    public static CustomLocation of(Location location, boolean complex) {
        final CustomLocation customLocation = CustomLocation.builder()
                .worldName(location.getWorld().getName())
                .x(location.getX())
                .y(location.getY())
                .z(location.getZ())
                .build();

        if (complex) {
            customLocation.setYaw(location.getYaw());
            customLocation.setPitch(location.getPitch());
        }

        return customLocation;
    }

    public static CustomLocation of(String worldName, double x, double y, double z) {
        return CustomLocation.builder().worldName(worldName).x(x).y(y).z(z).build();
    }

    public @Nullable Location getLocation() {
        final World world = Bukkit.getWorld(worldName);
        if (world == null) return null;

        return new Location(world, x, y, z);
    }

    public @Nullable Location getComplexLocation() {
        final World world = Bukkit.getWorld(worldName);
        if (world == null) return null;

        return new Location(world, x, y, z, yaw, pitch);
    }
}