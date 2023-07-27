package com.myworkspace.tools;

import com.myworkspace.exception.ColorNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private final static Block BLOCK01 = new BlockImpl("color3", "material1");
    private final static Block BLOCK02 = new BlockImpl("color2", "material2");
    private final static CompositeBlock COMPOSITE_BLOCK03 = new CompositeBlockImpl("color3", "material3");
    private final static Block BLOCK04 = new BlockImpl("color4", "material3");
    private final static CompositeBlock COMPOSITE_BLOCK05 = new CompositeBlockImpl("color1", "material4");
    private final static Block BLOCK06 = new BlockImpl("color2", "material4");

    private Structure filledWall;


    @BeforeAll
    static void setUpClass() {
        COMPOSITE_BLOCK05.getBlocks().add(BLOCK06);

        COMPOSITE_BLOCK03.getBlocks().add(COMPOSITE_BLOCK05);
        COMPOSITE_BLOCK03.getBlocks().add(BLOCK04);

    }

    @BeforeEach
    void setUp() {
        filledWall = new Wall(BLOCK01, BLOCK02, COMPOSITE_BLOCK03);

    }

    @Test
    void shouldThrowColorNotFoundException(){

        //THEN
        assertThrows(ColorNotFoundException.class, () -> filledWall.findBlockByColor("color6"));
    }

    @Test
    void shouldFindBlockAnyBlockWithWrightColor(){
        //GIVEN
        String colorExpected = "color3";
        //WHEN
       Optional<Block> result =  filledWall.findBlockByColor(colorExpected);
        //THEN
        assertEquals(colorExpected, result.get().getColor());

        System.out.println(result);
    }


}

