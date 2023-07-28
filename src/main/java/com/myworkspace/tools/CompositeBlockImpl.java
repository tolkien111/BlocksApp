package com.myworkspace.tools;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CompositeBlockImpl extends BlockImpl implements CompositeBlock {

    private List<Block> blocks = new ArrayList<>();

    public CompositeBlockImpl(String color, String material) {
        super(color, material);
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public Stream<Block> toStream() {
        return Stream.concat(
                super.toStream(),
                blocks.stream().flatMap(block -> block.toStream())
        );
    }

    @Override
    public String toString() {
        return "CompositeBlockImpl{" +
                "blocks=" + blocks +
                "} " + super.toString();
    }
}
