import org.junit.*;
import static org.junit.Assert.*;

public class EntryTest{


    @Test
    public void testValues(){
        assertEquals(
            "S_UNSCORED.isScored must return false",
            false, Entry.S_UNSCORED.isScored());
        assertEquals(
            "S_SCORED.isScored must return true",
            true, Entry.S_SCORED.isScored());
        assertEquals(
            "O_UNSCORED.isScored must return false",
            false, Entry.O_UNSCORED.isScored());
        assertEquals(
            "O_SCORED.isScored must return true",
            true, Entry.O_SCORED.isScored());
        assertEquals(
            "S_UNSCORED.getDisplay must return 'S'",
            'S', Entry.S_UNSCORED.getDisplay());
        assertEquals(
            "S_SCORED.getDisplay must return '$'",
            '$', Entry.S_SCORED.getDisplay());
        assertEquals(
            "S_UNSCORED.getDisplay must return 'O'",
            'O', Entry.O_UNSCORED.getDisplay());
        assertEquals(
            "S_SCORED.getDisplay must return '0'",
            '0', Entry.O_SCORED.getDisplay());
        assertEquals("enum Entry must contain exactly 4 entries",
        4, Entry.values().length);

    }



    @Test
    public void testFromDisplay(){
        assertEquals("fromDisplay('O') must return O_UNSCORED",
                Entry.O_UNSCORED, Entry.fromDisplay('O'));
        assertEquals("fromDisplay('S') must return S_UNSCORED",
                Entry.S_UNSCORED, Entry.fromDisplay('S'));
        assertEquals("fromDisplay('0') must return O_SCORED",
                Entry.O_SCORED, Entry.fromDisplay('0'));
        assertEquals("fromDisplay('$') must return S_SCORED",
                Entry.S_SCORED, Entry.fromDisplay('$'));
        assertNull("fromDisplay on anything else must return null",
                Entry.fromDisplay('o'));
        assertNull("fromDisplay on anything else must return null",
                Entry.fromDisplay('s'));
        assertNull("fromDisplay on anything else must return null",
                Entry.fromDisplay(' '));
    }



    @Test
    public void testToUnscored(){
        assertEquals("O_UNSCORED.toUnscored() must return O_UNSCORED",
                Entry.O_UNSCORED, Entry.O_UNSCORED.toUnscored());
        assertEquals("O_SCORED.toUnscored() must return O_UNSCORED",
                Entry.O_UNSCORED, Entry.O_SCORED.toUnscored());
        assertEquals("S_UNSCORED.toUnscored() must return S_UNSCORED",
                Entry.S_UNSCORED, Entry.S_UNSCORED.toUnscored());
        assertEquals("S_SCORED.toUnscored() must return S_UNSCORED",
                Entry.S_UNSCORED, Entry.S_SCORED.toUnscored());
    }



    @Test
    public void testToScored(){
        assertEquals("O_SCORED.toScored() must return O_SCORED",
                Entry.O_SCORED, Entry.O_SCORED.toScored());
        assertEquals("O_SCORED.toScored() must return O_SCORED",
                Entry.O_SCORED, Entry.O_SCORED.toScored());
        assertEquals("S_SCORED.toScored() must return S_SCORED",
                Entry.S_SCORED, Entry.S_SCORED.toScored());
        assertEquals("S_SCORED.toScored() must return S_SCORED",
                Entry.S_SCORED, Entry.S_SCORED.toScored());


    }



}
