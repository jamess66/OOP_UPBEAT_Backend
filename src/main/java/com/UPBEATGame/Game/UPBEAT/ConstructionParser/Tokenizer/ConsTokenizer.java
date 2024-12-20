package com.UPBEATGame.Game.UPBEAT.ConstructionParser.Tokenizer;

import java.util.Arrays;
import java.util.List;

public class ConsTokenizer implements Tokenizer {
    private final String src;
    private String next;
    private String prev;
    private int pos;

    private final List<String> specVar = Arrays.asList(
            "if", "while", "done", "relocate", "move", "invest", "shoot",
            "up", "down", "upleft", "upright", "downleft", "downright",
            "then", "else", "opponent", "nearby", "rows", "cols", "currow",
            "curcol", "budget", "deposit", "int", "maxdeposit", "random"
    );

    private final List<String> command = Arrays.asList("done" , "relocate" , "move" , "invest" , "collect" , "shoot");

    public ConsTokenizer(String src){
        this.src = src;
        pos = 0;
        compute();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String consume() {
        if (!hasNext())
            throw new TokenException.NoMoreToken(prev);
        String result = next;
        compute();
        return result;
    }

    @Override
    public boolean consume(String str) {
        if (!hasNext()) {
            throw new TokenException.NoMoreToken(prev);
        } else {
            if (next.equals(str)) {
                compute();
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String peek() {
        if (next == null)
            throw new TokenException.NoMoreToken(prev);
        return next;
    }

    @Override
    public boolean peek(String str) {
        if (!hasNext()) {
            return false;
        } else {
            return next.equals(str);
        }
    }

    @Override
    public boolean peekIdentifier() {
        return !specVar.contains(peek());
    }


    public boolean peekCommand() {
        return command.contains(peek());
    }


    private void CommentLineCal() {
        while (pos < src.length() && src.charAt(pos) != '\n') {
            pos++;
        }
    }

    private boolean ignoreCommentChar(char c) {
        return Character.isWhitespace(c) || c == '#' || c == '"';
    }
    
    private boolean isLetter(char c) {
        return Character.isLetter(c) || c == '_';
    }

    private void compute() {
        if (src == null) return;
        StringBuilder sb = new StringBuilder();
        while (pos < src.length() && ignoreCommentChar(src.charAt(pos))) {
            if (src.charAt(pos) == '#')
                CommentLineCal();
            else
                pos++;
        }

        if (pos == src.length()) {
            prev = next;
            next = null;
            return;
        }
        char c = src.charAt(pos);
        if (Character.isDigit(c)) {
            while (pos < src.length() && Character.isDigit(src.charAt(pos))) {
                sb.append(src.charAt(pos));
                pos++;
            }
        } else if (isLetter(c) || c == '_') {
            while (pos < src.length() && isLetter(src.charAt(pos))) {
                sb.append(src.charAt(pos));
                pos++;
            }
        } else if ("()+-*/%^{}=".contains(String.valueOf(c))) {
            sb.append(src.charAt(pos));
            pos++;
        } else {
            throw new TokenException.ExceptChar(c);
        }
        prev = next;
        next = sb.toString();
    }


}
