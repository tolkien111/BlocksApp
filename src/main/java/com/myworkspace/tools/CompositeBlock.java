package com.myworkspace.tools;

import java.util.List;

interface CompositeBlock extends Block {

    List<Block> getBlocks();
}
