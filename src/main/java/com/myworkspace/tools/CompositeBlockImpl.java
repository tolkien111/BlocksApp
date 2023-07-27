package com.myworkspace.tools;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CompositeBlockImpl extends BlockImpl implements CompositeBlock {

    private List<Block> blocks = new ArrayList<>();

    public CompositeBlockImpl(String color, String material) {
        super(color, material);
    }


    @Override
    public List<Block> getBlocks() {
        return blocks;
    }


}
