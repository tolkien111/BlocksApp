package com.myworkspace.tools;

import com.myworkspace.exception.ColorNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Stream;

@NoArgsConstructor
@EqualsAndHashCode
public class Wall implements Structure {

    private List<Block> blocks = new ArrayList<>();

    public Wall(Block... allBlocks) {
        Arrays.stream(allBlocks).forEach(block -> blocks.add(block));
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) return Optional.empty();

        return Optional.ofNullable(blocks.stream().flatMap(block -> Stream.of(block)).filter(x -> x.getColor().equals(color)).findAny()
                .orElseThrow(() -> new ColorNotFoundException(color + " not found")));
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) return null;
        return blocks.stream().flatMap(block -> Stream.of(block)).filter(y -> y.getMaterial().equals(material)).toList();
    }

    @Override
    public int count() {
        return 0;
    }

}
