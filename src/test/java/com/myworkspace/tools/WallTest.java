package com.myworkspace.tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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
    private Structure emptyWall;


    @BeforeAll
    static void setUpClass() {
        COMPOSITE_BLOCK05.getBlocks().add(BLOCK06);

        COMPOSITE_BLOCK03.getBlocks().add(COMPOSITE_BLOCK05);
        COMPOSITE_BLOCK03.getBlocks().add(BLOCK04);

    }

    @BeforeEach
    void setUp() {
        filledWall = new Wall(BLOCK01, BLOCK02, COMPOSITE_BLOCK03);
        emptyWall = new Wall();
    }

    @Test
    void shouldFindAnyBlockOfColor() {
        //GIVEN
        String colorExpected = "color6";
        //WHEN & THEN
        Optional<Block> result = filledWall.findBlockByColor(colorExpected);
        assertTrue(result.isPresent());
        assertEquals(colorExpected, result.get().getColor());
    }

    @Test
    void shouldNotFindBlockOfColor() {
        //GIVEN
        String colorNotExpected = "color";
        Optional<Block> emptyStructure = Optional.empty();
        //WHEN & THEN
        Optional<Block> result = filledWall.findBlockByColor(colorNotExpected);
        assertEquals(emptyStructure, result);
    }

    @Test
    void shouldBeTheSameBlock(){
        //GIVEN
        Block BLOCK08 = new BlockImpl("color1", "material3");
        String colorExpected = "color1";
        //WHEN & THEN
        Optional<Block> result = filledWall.findBlockByColor(colorExpected);
        assertEquals(Optional.of(BLOCK08), result);
    }

    @Test
    void shouldFindAllBlockOfMaterial() {
        //GIVEN
        String materialExpected = "material6";
        //WHEN & THEN
        List<Block> result = filledWall.findBlocksByMaterial(materialExpected);
        assertEquals(materialExpected, result.get(0).getMaterial());
        assertEquals(2, result.size());
    }

    @Test
    void shouldNotFindBlockOfMaterial() {
        //GIVEN
        String materialNotExpected1 = "material";
        String materialNotExpected2 = "";
        String materialNotExpected3 = "   ";
        String materialNotExpected4 = "material";


        //WHEN & THEN
        List<Block> result1 = filledWall.findBlocksByMaterial(materialNotExpected1);
        List<Block> result2 = filledWall.findBlocksByMaterial(materialNotExpected2);
        List<Block> result3 = filledWall.findBlocksByMaterial(materialNotExpected3);
        List<Block> result4 = filledWall.findBlocksByMaterial(materialNotExpected4);
        assertTrue(result1.isEmpty());
        assertTrue(result2.isEmpty());
        assertTrue(result3.isEmpty());
        assertTrue(result4.isEmpty());
    }

    @Test
    void shouldReturnEmptyListOfBlockOfMaterial() {
        //GIVEN
        List <Block> listExpected = new ArrayList<>();
        String materialNotExpected = "material";
        //WHEN & THEN
        List<Block> result = emptyWall.findBlocksByMaterial(materialNotExpected);
        assertEquals(listExpected, result);
    }

    @Test
    void shouldBeTheSameWall(){
        //GIVEN
        final var expectedWall = new Wall(BLOCK01, BLOCK02, COMPOSITE_BLOCK03);
        //WHEN & THEN
        assertEquals(expectedWall,filledWall);
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

