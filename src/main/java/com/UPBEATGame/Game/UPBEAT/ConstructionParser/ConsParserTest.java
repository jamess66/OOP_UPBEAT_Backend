package com.UPBEATGame.Game.UPBEAT.ConstructionParser;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.Exec;

import com.UPBEATGame.Game.UPBEAT.AST.Execution.AssignmentExc;
import com.UPBEATGame.Game.UPBEAT.ConstructionParser.Tokenizer.ConsTokenizer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsParserTest {
    
    public ConsParser parser;
    public List<Exec> node;

    @Test
    public void testExpression() {
        parser = new ConsParser(new ConsTokenizer("123+321-213"));
        assertThrows(ParserException.NoSuchCommandException.class, parser::Parse);
    }
    @Test
    public void testNoStatement() {
        assertThrows(ParserException.NoMoreStatementException.class, () -> new ConsParser(new ConsTokenizer(null)));
        assertThrows(ParserException.NoMoreStatementException.class, () -> new ConsParser(new ConsTokenizer("")));
    }

    @Test
    public void testStatements(){
        parser = new ConsParser(new ConsTokenizer("a = 1 b = 2 c = 3 d = 4 e = 5"));
        node = parser.Parse();
        assertInstanceOf(AssignmentExc.class, node.get(0));
        assertInstanceOf(AssignmentExc.class, node.get(1));
        assertInstanceOf(AssignmentExc.class, node.get(2));
        assertInstanceOf(AssignmentExc.class, node.get(3));
        assertInstanceOf(AssignmentExc.class, node.get(4));
    }

    @Test
    public void testUnknownWord() {
        parser = new ConsParser(new ConsTokenizer("Happy new year!!"));
        assertThrows(ParserException.NoSuchCommandException.class, parser::Parse);
    }
    @Test
    public void testSpecWord() {
        parser = new ConsParser(new ConsTokenizer("nearby=9594"));
        assertThrows(ParserException.SpecVarIdentifierException.class, parser::Parse);
    }

    @Test
    public void testReadFile() throws IOException {
        Path fileName = Path.of("src/ConstructionParser/SimpleConstructor/Construction_plan.txt");
        String str = Files.readString(fileName);
        parser = new ConsParser((new ConsTokenizer(str)));
        assertDoesNotThrow(() -> parser.Parse());
    }

    @Test
    public void testRandom(){
        parser = new ConsParser(new ConsTokenizer("x = random"));

        System.out.println(Arrays.toString(parser.Parse().toArray()));
    }
}