package src.ConstructionParser;

import static AST.Node.Exec;

import AST.Statement.AssignmentExc;
import ConstructionParser.Tokenizer.ExprTokenizer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsParserTest {
    
    public ConsParser parser;
    public List<Exec> node;

    @Test
    public void testExpression() {
        parser = new ConsParser(new ExprTokenizer("123+321-213"));
        assertThrows(ParserException.NoSuchCommandException.class, parser::Parse);
    }
    @Test
    public void testNoStatement() {
        assertThrows(ParserException.NoMoreStatementException.class, () -> new ConsParser(new ExprTokenizer(null)));
        assertThrows(ParserException.NoMoreStatementException.class, () -> new ConsParser(new ExprTokenizer("")));
    }

    @Test
    public void testStatements(){
        parser = new ConsParser(new ExprTokenizer("a = 1 b = 2 c = 3 d = 4 e = 5"));
        node = parser.Parse();
        assertInstanceOf(AssignmentExc.class, node.get(0));
        assertInstanceOf(AssignmentExc.class, node.get(1));
        assertInstanceOf(AssignmentExc.class, node.get(2));
        assertInstanceOf(AssignmentExc.class, node.get(3));
        assertInstanceOf(AssignmentExc.class, node.get(4));
    }

    @Test
    public void testUnknownWord() {
        parser = new ConsParser(new ExprTokenizer("Watch this! pew pew pew!"));
        assertThrows(ParserException.NoSuchCommandException.class, parser::Parse);
    }
    @Test
    public void testSpecWord() {
        parser = new ConsParser(new ExprTokenizer("nearby=10000000"));
        assertThrows(ParserException.SpecVarIdentifierException.class, parser::Parse);
    }

    @Test
    public void testReadFile() throws IOException {
        Path fileName = Path.of("src/ConstructionParser/SimpleConstructor/Construction_plan.txt");
        String str = Files.readString(fileName);
        parser = new ConsParser((new ExprTokenizer(str)));
        assertDoesNotThrow(() -> parser.Parse());
    }
}