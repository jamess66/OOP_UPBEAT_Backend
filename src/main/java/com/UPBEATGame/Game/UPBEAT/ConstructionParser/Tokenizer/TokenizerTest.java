package com.UPBEATGame.Game.UPBEAT.ConstructionParser.Tokenizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenizerTest {
    private ConsTokenizer tkz;

    @Test
    public void testHasNext() {
        tkz = new ConsTokenizer(null);
        assertFalse(tkz.hasNext());
        tkz = new ConsTokenizer("");
        assertFalse(tkz.hasNext());
        tkz = new ConsTokenizer("john");
        assertTrue(tkz.hasNext());
    }

    @Test
    public void testPeek() {
        tkz = new ConsTokenizer(null);
        assertThrows(TokenException.NoMoreToken.class, tkz::peek);
        tkz = new ConsTokenizer("");
        assertThrows(TokenException.NoMoreToken.class, tkz::peek);
        tkz = new ConsTokenizer("james");
        assertEquals("james", tkz.peek());
    }

    @Test
    public void testPeekString() {
        tkz = new ConsTokenizer(null);
        assertFalse(tkz.peek(""));
        assertFalse(tkz.peek("ahh"));
        tkz = new ConsTokenizer("");
        assertFalse(tkz.peek(""));
        assertFalse(tkz.peek("uhhh"));
        tkz = new ConsTokenizer("hoho");
        assertTrue(tkz.peek("hoho"));
        tkz = new ConsTokenizer("Hello World");
        assertFalse(tkz.peek("hi"));
        assertTrue(tkz.peek("Hello"));
    }

    @Test
    public void testConsume() {
        tkz = new ConsTokenizer(null);
        assertThrows(TokenException.NoMoreToken.class, tkz::consume);
        tkz = new ConsTokenizer("");
        assertThrows(TokenException.NoMoreToken.class, tkz::consume);
        tkz = new ConsTokenizer("jeff");
        assertEquals("jeff", tkz.consume());
    }

    @Test
    public void testConsumeString() {
        tkz = new ConsTokenizer(null);
        assertThrows(TokenException.NoMoreToken.class, () -> tkz.consume(""));
        assertThrows(TokenException.NoMoreToken.class, () -> tkz.consume("a"));
        tkz = new ConsTokenizer("");
        assertThrows(TokenException.NoMoreToken.class, () -> tkz.consume(""));
        assertThrows(TokenException.NoMoreToken.class, () -> tkz.consume("a"));
        tkz = new ConsTokenizer("a");
        assertTrue(tkz.consume("a"));
        tkz = new ConsTokenizer("a");
        assertFalse(tkz.consume("abc"));
        assertTrue(tkz.consume("a"));
        tkz = new ConsTokenizer("abc");
        assertFalse(tkz.consume("a"));
        assertTrue(tkz.consume("abc"));
    }

    @Test
    public void testComment() {
        tkz = new ConsTokenizer("# test commend \n wasd \n ###########qwerty");
        assertTrue(tkz.consume("wasd"));
    }

}