package com.myworkspace.tools;

import lombok.EqualsAndHashCode;

import java.util.stream.Stream;

@EqualsAndHashCode
public class BlockImpl implements Block{

    private String color;
    private String material;

    public BlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public Stream<Block> toStream() {
        return Stream.of(this);
    }

    @Override
    public String toString() {
        return "BlockImpl{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
