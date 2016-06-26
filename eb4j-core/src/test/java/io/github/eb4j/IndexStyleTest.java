package io.github.eb4j;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by miurahr on 16/06/26.
 */
public class IndexStyleTest {
    IndexStyle indexStyle;

    @Test(groups = {"init"})
    public void testIndexStyle() throws Exception {
        indexStyle = new IndexStyle();
    }

    @Test(groups = {"setter"}, dependsOnGroups = {"init"})
    public void testSetGetIndexID() throws Exception {
        // IndexID would be 0x01, 0x71, 0x91, or 0xa1
        int id = 1;
        indexStyle.setIndexID(id);
        assertEquals(indexStyle.getIndexID(), id);
    }

    @Test(groups = {"setter"}, dependsOnGroups = {"init"})
    public void testSetGetStartPage() throws Exception {
        long startPage = 1L;
        indexStyle.setStartPage(startPage);
        assertEquals(indexStyle.getStartPage(), startPage);
    }

    @Test(groups = {"setter"}, dependsOnGroups = {"init"})
    public void testSetGetEndPage() throws Exception {
        long endPage = 10L;
        indexStyle.setEndPage(endPage);
        assertEquals(indexStyle.getEndPage(), endPage);
    }

    @Test(groups = {"setter"}, dependsOnGroups = {"init"})
    public void testSetGetCandidatePage() throws Exception {
        long candidatePage = 1L;
        indexStyle.setCandidatePage(candidatePage);
        assertEquals(indexStyle.getCandidatePage(), candidatePage);
    }

    @Test(groups = {"setter"}, dependsOnGroups = {"init"})
    public void testSetGetLabel() throws Exception {
        String label = "label";
        indexStyle.setLabel(label);
        assertEquals(indexStyle.getLabel(), label);
    }


    // fixWord tests

    @Test(groups = {"style"})
    public void testSpaceStyle_LatinDelete() throws Exception {
        byte[] b = {'a', 'b', 'c'};
        byte[] expected = {'a', 'b', 'c'};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setSpaceStyle(IndexStyle.DELETE);// ASIS|DELETE
        style.fixWordLatin(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testLowerStyle_LatinConvert() throws Exception {
        byte[] b = {'a', 'b', 'c'};
        byte[] expected = {'a', 'b', 'c'};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setLowerStyle(IndexStyle.CONVERT);// ASIS|CONVERT
        style.fixWordLatin(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testSpaceStyle_delete() throws Exception {
        byte[] b = {'a', 'b', 'c'};
        byte[] expected = {'a', 'b', 'c'};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setSpaceStyle(IndexStyle.DELETE);// ASIS|DELETE
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testKanaStyle_convert() throws Exception {
        byte[] b = {0x24, 0x22, 0x24, 0x24, 0x24, 0x26, (byte) 0xef}; // a, i, u in Hiragana
        byte[] expected = {0x25, 0x22, 0x25, 0x24, 0x25, 0x26, 0x00}; // a, i, u in Katanaka
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setKatakanaStyle(IndexStyle.CONVERT);// ASIS|REVERSE|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testKanaStyle_reverse() throws Exception {
        byte[] b = {0x25, 0x22, 0x25, 0x24, 0x25, 0x26, 0x00}; // a, i, u in Katanaka
        byte[] expected = {0x24, 0x22, 0x24, 0x24, 0x24, 0x26, (byte) 0xef}; // a, i, u in Hiragana
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setKatakanaStyle(IndexStyle.REVERSE);// ASIS|REVERSE|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testLowerStyle_convert() throws Exception {
        byte[] b = {'a', 'b', 'c'};
        byte[] expected = {'A', 'B', 'C'};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setLowerStyle(IndexStyle.CONVERT);// ASIS|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testMarkStyle_delete() throws Exception {
        byte[] b = {0x23, 0x30, 0x21, 0x26, 0x21, 0x2a, 0x25, 0x22, 0x21, 0x3e, 0x25, 0x22, 0x00, 0x00, (byte)0xff};
        byte[] expected = {0x23, 0x30, 0x21, 0x2a, 0x25, 0x22, 0x25, 0x22, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setMarkStyle(IndexStyle.DELETE);// ASIS|DELETE
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testLongVowelStyle_convert() throws Exception {
        byte[] b = {'a', 'b', 'c'};
        byte[] expected = {'a', 'b', 'c'};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setLongVowelStyle(IndexStyle.CONVERT);// ASIS|CONVERT|DELETE
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testLongVowelStyle_delete() throws Exception {
        byte[] b = {'a', 'b', 'c'};
        byte[] expected = {'a', 'b', 'c'};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setLongVowelStyle(IndexStyle.DELETE);// ASIS|CONVERT|DELETE
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testDoubleConsonantStyle_convert() throws Exception {
        byte[] b = {'a', 'b', 'c'};
        byte[] expected = {'a', 'b', 'c'};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setDoubleConsonantStyle(IndexStyle.CONVERT);// ASIS|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testContractedSoundStyle_convert() throws Exception {
        byte[] b = {};
        byte[] expected = {};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setContractedSoundStyle(IndexStyle.CONVERT);// ASIS|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testSmallVowlelStyle_convert() throws Exception {
        byte[] b = {};
        byte[] expected = {};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setSmallVowelStyle(IndexStyle.CONVERT);// ASIS|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testVoicedConsonantStyle_convert() throws Exception {
        byte[] b = {};
        byte[] expected = {};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setVoicedConsonantStyle(IndexStyle.CONVERT);// ASIS|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    @Test(groups = {"style"})
    public void testPSoundStyle_convert() throws Exception {
        byte[] b = {};
        byte[] expected = {};
        IndexStyle style = new IndexStyle();
        resetToAsis(style);
        style.setPSoundStyle(IndexStyle.CONVERT);// ASIS|CONVERT
        style.fixWord(b);
        assertEquals(b, expected);
    }

    public void resetToAsis(IndexStyle style) throws Exception {
        style.setSpaceStyle(IndexStyle.ASIS);// ASIS|DELETE
        style.setKatakanaStyle(IndexStyle.ASIS);// ASIS|REVERSE|CONVERT
        style.setLowerStyle(IndexStyle.ASIS);// ASIS|CONVERT
        style.setMarkStyle(IndexStyle.ASIS);// ASIS|DELETE
        style.setLongVowelStyle(IndexStyle.ASIS);// ASIS|CONVERT|DELETE
        style.setDoubleConsonantStyle(IndexStyle.ASIS);// ASIS|CONVERT
        style.setContractedSoundStyle(IndexStyle.ASIS);// ASIS|CONVERT
        style.setSmallVowelStyle(IndexStyle.ASIS);// ASIS|CONVERT
        style.setVoicedConsonantStyle(IndexStyle.ASIS);// ASIS|CONVERT
        style.setPSoundStyle(IndexStyle.ASIS);// ASIS|CONVERT
    }

}