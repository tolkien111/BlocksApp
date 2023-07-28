package com.myworkspace.tools;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Predicate;
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
        String colorToLowerCase = color.toLowerCase();
        return findByPredicate(x -> x.getColor().equals(colorToLowerCase)).findAny();
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null || material.isEmpty() || material.isBlank() || !material.matches("[a-zA-Z0-9]{3,}")){
            return null;
        }
        String materialToLowerCase = material.toLowerCase();
        return findByPredicate(y -> y.getMaterial().equals(materialToLowerCase)).toList();
    }
    private Stream<Block> findByPredicate(Predicate<Block> predicate) {
        return blocks.stream()
                .flatMap(block -> block.toStream())
                .filter(predicate);
    }


    @Override
    public int count() {
        return (int) blocks.stream().flatMap(block -> block.toStream()).count();
    }

}
