package com.myworkspace.tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private final static Block BLOCK01 = new BlockImpl("color1", "material3");
    private final static Block BLOCK02 = new BlockImpl("color2", "material2");
    private final static CompositeBlock COMPOSITE_BLOCK03 = new CompositeBlockImpl("color3", "material3");
    private final static Block BLOCK04 = new BlockImpl("color4", "material4");
    private final static CompositeBlock COMPOSITE_BLOCK05 = new CompositeBlockImpl("color5", "material6");
    private final static Block BLOCK06 = new BlockImpl("color6", "material6");

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

//    @Test
//    void shouldThrowColorNotFoundException(){
//
//        //THEN
//        assertThrows(ColorNotFoundException.class, () -> filledWall.findBlockByColor("color7"));
//
//    }

    @Test
    void shouldFindBlockAnyBlockWithColor() throws NoSuchElementException {
        //GIVEN
        String colorExpected = "color6";
        //WHEN & THEN
        Optional<Block> result = filledWall.findBlockByColor(colorExpected);
        assertTrue(result.isPresent());
    }

    @Test
    void shouldAllBlockWithMaterial() {
        //GIVEN
        String materialExpected = "material6";
        //WHEN & THEN
        List <Block> result = filledWall.findBlocksByMaterial(materialExpected);
        assertEquals(materialExpected, result.get(0).getMaterial());
        assertEquals(2,result.size());
    }

    @Test
    void shouldCountAllBlocksInTheWall() {
        //GIVEN
        int amountExpected = 6;
        //WHEN & THEN
        int result = filledWall.count();
        assertEquals(amountExpected, result);
    }


}

