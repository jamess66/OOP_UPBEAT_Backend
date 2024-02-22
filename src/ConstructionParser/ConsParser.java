package ConstructionParser;

import java.util.ArrayList;
import java.util.List;

import AST.Expression.*;
import AST.Statement.*;
import AST.Node.*;
import AST.ASTErrorException.*;
import GameLogics.Utility.Direction;
import ConstructionParser.ParserException.*;
import ConstructionParser.Tokenizer.ConsTokenizer;

public class ConsParser implements Parser {

    private final ConsTokenizer tkz;

    public ConsParser(ConsTokenizer tkz){
        if(!tkz.hasNext()){
            throw new NoMoreStatementException();
        }
        this.tkz = tkz;
    }

    @Override
    public List<Exec> Parse() {
        List<Exec> nodes = parsePlan();
        if(tkz.hasNext()){
            throw new ExceptTokenErrorException(tkz.peek());
        }
        return nodes;
    }

    private List<Exec> parsePlan(){
        List<Exec> plan = new ArrayList<>();
        plan.add(parseStatement());
        plan.addAll(parseMultipleStatement());
        return plan;
    }

    private Exec parseStatement() {
        if(tkz.peek("if")){
            return parseIfStatement();
        }else if(tkz.peek("while")){
            return parseWhileStatement();
        }else if(tkz.peek("{")){
            return parseBlockStatement();
        }else{
            return parseCommand();
        }
    }

    private List<Exec> parseMultipleStatement(){
        List<Exec> plans = new ArrayList<>();
        while(tkz.hasNext() && !tkz.peek("}")) {
            plans.add(parseStatement());
        }
        return plans;
    }

    private Exec parseBlockStatement() {
        tkz.consume("{");
        List<Exec> blocks = new ArrayList<>(parseMultipleStatement());
        tkz.consume("}");
        return new BlockExc(blocks);
    }

    private Exec parseWhileStatement() {
        tkz.consume("while");
        tkz.consume("{");
        Expr expr = parseExpression();
        tkz.consume("}");
        Exec parse = parseStatement();
        return new WhileExc(expr, parse);
    }


    private Exec parseIfStatement() {
        tkz.consume("if");
        tkz.consume("(");
        Expr expr = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Exec trueState = parseStatement();
        Exec falseState;
        if(tkz.peek("else")){
            tkz.consume("else");
            falseState = parseStatement();
        }else {
            falseState = new BlockExc(new ArrayList<>());
        }
        return new IfExc(expr, trueState, falseState);
    }

    private Exec parseCommand() {
        if (tkz.peekCommand())
            return parseActionCommand();
        else
            return parseAssignmentStatement();
    }

    private Exec parseAssignmentStatement() {
        if (!tkz.peekIdentifier()) throw new SpecVarIdentifierException(tkz.peek());
        String identifier = tkz.consume();
        if (tkz.peek("="))
            tkz.consume();
        else
            throw new NoSuchCommandException(identifier);
        Expr expr= parseExpression();
        return new AssignmentExc(identifier, expr);
    }

    private Exec parseActionCommand() {
        String command = tkz.consume();
        return switch (command) {
            case "done" -> new DoneExc();
            case "relocate" -> new RelocateExc();
            case "move" -> parseMoveCommand();
            case "invest" -> parseInvestCommand();
            case "collect" -> parseCollectCommand();
            case "shoot" -> parseAttackCommand();
            default -> throw new InvalidActionCommandException(command);
        };
    }

    private Exec parseMoveCommand() {
        Direction direction = parseDirection();
        return new MoveExc(direction);
    }

    private Exec parseAttackCommand() {
        Direction dir = parseDirection();
        Expr expr = parseExpression();
        return new AttackExc(expr,dir);
    }

    private Direction parseDirection() {
        String direction = tkz.consume();
        return switch (direction) {
            case "up" -> Direction.Up;
            case "down" -> Direction.Down;
            case "upleft" -> Direction.UpLeft;
            case "upright" -> Direction.UpRight;
            case "downleft" -> Direction.DownLeft;
            case "downright" -> Direction.DownRight;
            default -> throw new InvalidDirectionException(direction);
        };
    }

    private Exec parseCollectCommand() {
        Expr expr = parseExpression();
        return new CollectExc(expr);
    }

    private Exec parseInvestCommand() {
        Expr expr = parseExpression();
        return new InvestExc(expr);
    }

    private Expr parseExpression() {
        Expr left = parseTerm();
        while (tkz.peek("+") || tkz.peek("-")) {
            String operator = tkz.consume();
            Expr right = parseTerm();
            left = new BinaryOperateExp(left, operator, right);
        }
        return left;
    }

    private Expr parseTerm() {
        Expr left = parseFactor();
        while (tkz.peek("*") || tkz.peek("/") || tkz.peek("%")) {
            String operator = tkz.consume();
            Expr right = parseFactor();
            left = new BinaryOperateExp(left, operator, right);
        }
        return left;
    }
    private Expr parseFactor() {
        Expr left = parsePower();
        if (tkz.peek("^")) {
            String operator = tkz.consume();
            Expr right = parseFactor();
            left = new BinaryOperateExp(left, operator, right);
        }
        return left;
    }

    private Expr parsePower() {
        if (Character.isDigit(tkz.peek().charAt(0))) {
            return new NumberExp(Long.parseLong(tkz.consume()));
        } else if (tkz.peek("opponent") || tkz.peek("nearby")) {
            return parseInfoExpression();
        } else if (tkz.peek("(")) {
            tkz.consume("(");
            Expr expr = parseExpression();
            tkz.consume(")");
            return expr;
        }else if(Character.isAlphabetic(tkz.peek().charAt(0)) ){
            return new IdentifierExp(tkz.consume());
        }
        return null;
    }
    private Expr parseInfoExpression() {
        if (tkz.peek("opponent")) {
            tkz.consume();
            return new OpponentExp();
        } else if (tkz.peek("nearby")) {
            tkz.consume();
            Direction direction = parseDirection();
            return new NearbyExp(direction);
        } else {
            throw new InvalidInfoExpressionException(tkz.peek());
        }
    }


}

