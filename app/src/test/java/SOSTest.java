import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SOSTest {


    @Test
    public void testGetExample(){
        Entry[][] example = SOS.getExample();

        assertNotNull(
            "getExample must not return null", example);

        assertEquals("example must have exactly 6 rows",
        6, example.length);

        for (Entry[] row : example){
            assertNotNull("row must not be null", row);
            assertEquals(
                "row must have exactly 6 columns", 6, row.length);
        }

        assertEquals( "example at 0, 0 must be null", example[0][0], null);
        assertEquals( "example at 0, 1 must be null", example[0][1], null);
        assertEquals( "example at 0, 2 must be null", example[0][2], null);
        assertEquals( "example at 0, 3 must be null", example[0][3], null);
        assertEquals( "example at 0, 4 must be null", example[0][4], null);
        assertEquals( "example at 0, 5 must be Entry.S_UNSCORED", example[0][5], Entry.S_UNSCORED );

        assertEquals( "example at 1, 0 must be null", example[1][0], null);
        assertEquals( "example at 1, 1 must be null", example[1][1], null);
        assertEquals( "example at 1, 2 must be Entry.S_UNSCORED", example[1][2], Entry.S_UNSCORED);
        assertEquals( "example at 1, 3 must be null", example[1][3], null);
        assertEquals( "example at 1, 4 must be Entry.S_SCORED", example[1][4], Entry.S_SCORED);
        assertEquals( "example at 1, 5 must be null", example[1][5], null );

        assertEquals( "example at 2, 0 must be null", example[2][0], null);
        assertEquals( "example at 2, 1 must be Entry.O_UNSCORED", example[2][1], Entry.O_UNSCORED);
        assertEquals( "example at 2, 2 must be null", example[2][2], null);
        assertEquals( "example at 2, 3 must be Entry.S_UNSCORED", example[2][3], Entry.S_UNSCORED);
        assertEquals( "example at 2, 4 must be Entry.O_SCORED", example[2][4], Entry.O_SCORED);
        assertEquals( "example at 2, 5 must be null", example[2][5], null );

        assertEquals( "example at 3, 0 must be null", example[3][0], null);
        assertEquals( "example at 3, 1 must be Entry.S_UNSCORED", example[3][1], Entry.S_UNSCORED);
        assertEquals( "example at 3, 2 must be null", example[3][2], null);
        assertEquals( "example at 3, 3 must be null", example[3][3], null);
        assertEquals( "example at 3, 4 must be Entry.S_SCORED", example[3][4], Entry.S_SCORED);
        assertEquals( "example at 3, 5 must be null", example[3][5], null );

        assertEquals( "example at 4, 0 must be null", example[4][0], null);
        assertEquals( "example at 4, 1 must be null", example[4][1], null);
        assertEquals( "example at 4, 2 must be null", example[4][2], null);
        assertEquals( "example at 4, 3 must be null", example[4][3], null);
        assertEquals( "example at 4, 4 must be null", example[4][4], null);
        assertEquals( "example at 4, 5 must be null", example[4][5], null);

        assertEquals( "example at 5, 0 must be null", example[5][0], null);
        assertEquals( "example at 5, 1 must be null", example[5][1], null);
        assertEquals( "example at 5, 2 must be null", example[5][2], null);
        assertEquals( "example at 5, 3 must be Entry.S_UNSCORED", example[5][3], Entry.S_UNSCORED);
        assertEquals( "example at 5, 4 must be null", example[5][4], null);
        assertEquals( "example at 5, 5 must be Entry.O_UNSCORED", example[5][5], Entry.O_UNSCORED );

    }

    @Test
    public void testCheckBoard(){
        assertThrows("must throw IllegalArgumentException on null",
                IllegalArgumentException.class,
                () -> SOS.checkBoard(null));
        assertThrows("must throw IllegalArgumentException on board with less than 3 columns",
                IllegalArgumentException.class,
                () -> SOS.checkBoard(new Entry[2][2]));
        assertThrows("must throw IllegalArgumentException on board with less than 3 columns",
                IllegalArgumentException.class,
                () -> SOS.checkBoard(new Entry[0][0]));
        /*assertThrows("must not throw IllegalArgumentException on board with less than 3 columns and rows, all entries are null",
                IllegalArgumentException.class,
                () -> SOS.checkBoard(new Entry[3][3])); */
        assertThrows("must throw IllegalArgumentException on not square board",
                IllegalArgumentException.class,
                () -> SOS.checkBoard(new Entry[3][2]));
        assertThrows("must throw IllegalArgumentException on not square board",
                IllegalArgumentException.class,
                () -> SOS.checkBoard(new Entry[][] {{null,null,null}, {null,null}, {null,null,null}}));
        assertThrows("must throw IllegalArgumentException board with null rows",
                IllegalArgumentException.class,
                () -> SOS.checkBoard(new Entry[][] {{null,null,null,null}, {null,null,null,null}, null, {null,null,null,null}}));
    }


    @Test
    public void testBoardFull(){
        Entry[][] board = new Entry[4][4];
        assertFalse("must return false when empty", SOS.boardFull(board));

        board[3][2] = Entry.O_SCORED;
        assertFalse("must return false when empty fields are available", SOS.boardFull(board));

        Arrays.fill(board, new Entry[] {Entry.S_UNSCORED, Entry.S_UNSCORED, Entry.S_UNSCORED, Entry.S_UNSCORED});
        assertTrue("must return true when full", SOS.boardFull(board));
    }

    @Test
    public void testMoveInvalidArgument() {
            Entry[][] board = new Entry[6][6];


            assertThrows("move must throw IllegalArgumentExcpetion when entry is null ",
                    IllegalArgumentException.class,
                    () -> SOS.move(board, null, 0, 0));
            assertThrows("move must throw IllegalArgumentExcpetion when entry is O_SCORED",
                    IllegalArgumentException.class,
                    () -> SOS.move(board, Entry.O_SCORED, 0, 0));
            assertThrows("move must throw IllegalArgumentExcpetion when entry is S_SCORED",
                    IllegalArgumentException.class,
                    () -> SOS.move(board, Entry.S_SCORED, 0, 0));

            assertThrows("move must throw IllegalArgumentExcpetion when row is less than 0",
                    IllegalArgumentException.class,
                    () -> SOS.move(board, Entry.S_UNSCORED, -1, 2));

            assertThrows("move must throw IllegalArgumentExcpetion when row is greater than amount of rows-1",
                    IllegalArgumentException.class,
                    () -> SOS.move(board, Entry.S_UNSCORED, 6, 2));

            assertThrows("move must throw IllegalArgumentExcpetion when column is less than 0",
                    IllegalArgumentException.class,
                    () -> SOS.move(board, Entry.S_UNSCORED, 1, -1));

            assertThrows("move must throw IllegalArgumentExcpetion when row is greater than amount of rows-1",
                    IllegalArgumentException.class,
                    () -> SOS.move(board, Entry.S_UNSCORED, 1, 6));
    }



    @Test
    public void testMoveScoreO() {
        Entry[][] board = new Entry[6][6];
        Entry[][] expectedBoard = new Entry[6][6];
        int[][] positions = new int[][] { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 2, 3 },
                { 1, 3 } };
        for (int[] move : positions) {
            int row = move[0];
            int col = move[1];

            assertEquals(String.format("must return 0 when S_UNSCORED is set at %d, %d", row, col),
                    0, SOS.move(board, Entry.S_UNSCORED, row, col));

            expectedBoard[row][col] = Entry.S_UNSCORED;
            assertArrayEquals(String.format("move must set S_UNSCORED at %d, %d", row, col),
                    expectedBoard, board);

        }

        assertThrows("must throw IllegalArgumentException when trying to move on an occupied field",
                IllegalArgumentException.class,
                () -> SOS.move(board, Entry.S_UNSCORED, 3, 2));

        // 0 1 2 3 4 5
        // 0 . . . . . .
        // 1 . S S S . .
        // 2 . S O S . .
        // 3 . S S S . .
        // 4 . . . . . .
        // 5 . . . . . .
        assertEquals(String.format("must return 4 when O_UNSCORED is set at %d, %d", 2, 2),
                4, SOS.move(board, Entry.O_UNSCORED, 2, 2));

        expectedBoard[2][2] = Entry.O_SCORED;

        for (int[] move : positions) {
            int row = move[0];
            int col = move[1];
            expectedBoard[row][col] = Entry.S_SCORED;
        }

        assertArrayEquals("move must set O_SCORED at 2,2 and replace S_UNSCORED with S_SCORED around it",
                expectedBoard, board);

        assertThrows("must throw IllegalArgumentException when trying to move on an occupied field",
                IllegalArgumentException.class,
                () -> SOS.move(board, Entry.O_UNSCORED, 2, 2));

    }




    @Test
    public void testMoveScoreS() {
        Entry[][] board = new Entry[6][6];
        Entry[][] expectedBoard = new Entry[6][6];

        // 0 1 2 3 4 5
        // 0 . . . . . .
        // 1 . O O O . .
        // 2 . O . O . .
        // 3 . O O O . .
        // 4 . . . . . .
        // 5 . . . . . .
        int[][] positions = new int[][] { { -1, -1 }, { -1, 1 }, { 1, 1 },{ 1, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 },  { 0, -1 } };
        for (int[] pos : positions) {
            int row = 2 + pos[0];
            int col = 2 + pos[1];

            assertEquals(String.format("must return 0 when O_UNSCORED is set at %d, %d", row, col),
                    0, SOS.move(board, Entry.O_UNSCORED, row, col));

            expectedBoard[row][col] = Entry.O_UNSCORED;
            assertArrayEquals(String.format("move must set O_UNSCORED at %d, %d", row, col),
                    expectedBoard, board);
        }

        // 0 1 2 3 4 5
        // 0 S S S S S .
        // 1 S O O O S .
        // 2 . O . O . .
        // 3 S O O O S .
        // 4 S S S S S .
        // 5 . . . . . .
        for (int index = 0; index < positions.length -2; index++) {
            int[] pos = positions[index];
            int row = 2 + 2 * pos[0];
            int col = 2 + 2 * pos[1];

            assertEquals(String.format("must return 0 when S_UNSCORED is set at %d, %d", row, col),
                    0, SOS.move(board, Entry.S_UNSCORED, row, col));

            expectedBoard[row][col] = Entry.S_UNSCORED;
            assertArrayEquals(String.format("move must set S_UNSCORED at %d, %d", row, col),
                    expectedBoard, board);
        }

        assertEquals(String.format("must return 2 when S_UNSCORED is set at %d, %d", 2, 0),
                2, SOS.move(board, Entry.S_UNSCORED, 2, 0));

        //   0 1 2 3 4 5
        // 0 S S $ S S .
        // 1 S 0 O O S .
        // 2 $ O . O . .
        // 3 S 0 O O S .
        // 4 S S $ S S .
        // 5 . . . . . .
        expectedBoard[2][0] = Entry.S_SCORED;
        expectedBoard[1][1] = Entry.O_SCORED;
        expectedBoard[0][2] = Entry.S_SCORED;
        expectedBoard[3][1] = Entry.O_SCORED;
        expectedBoard[4][2] = Entry.S_SCORED;

        assertArrayEquals(
                "move must set S_SCORED at 2,0 and replace S_UNSCORED and O_UNSCORED with S_SCORED and O_SCORED around it",
                expectedBoard, board);


        assertEquals(String.format("must return 2 when S_UNSCORED is set at %d, %d", 2, 4),
                2, SOS.move(board, Entry.S_UNSCORED, 2, 4));

        //   0 1 2 3 4 5
        // 0 S S $ S S .
        // 1 S 0 O 0 S .
        // 2 $ O . O $ .
        // 3 S 0 O 0 S .
        // 4 S S $ S S .
        // 5 . . . . . .
        expectedBoard[2][4] = Entry.S_SCORED;
        expectedBoard[1][3] = Entry.O_SCORED;
        expectedBoard[3][3] = Entry.O_SCORED;

        assertArrayEquals(
                "move must set S_SCORED at 2,4 and replace S_UNSCORED and O_UNSCORED with S_SCORED and O_SCORED around it",
                expectedBoard, board);

        assertEquals(String.format("must return 8 when S_UNSCORED is set at %d, %d", 2, 2),
                8, SOS.move(board, Entry.S_UNSCORED, 2, 2));

        //   0 1 2 3 4 5
        // 0 S S $ S S .
        // 1 S 0 O 0 S .
        // 2 $ O . O $ .
        // 3 S 0 O 0 S .
        // 4 S S $ S S .
        // 5 . . . . . .
        expectedBoard[2][2] = Entry.S_SCORED;
        for (int row = 0; row < 6; row++){
            for (int col = 0; col < 6; col++){
                expectedBoard[row][col] = expectedBoard[row][col] != null ?  expectedBoard[row][col].toScored() : null;
            }
        }

        assertArrayEquals(
                "move must set S_SCORED at 2,2 and replace S_UNSCORED and O_UNSCORED with S_SCORED and O_SCORED around it",
                expectedBoard, board);

    }



}
