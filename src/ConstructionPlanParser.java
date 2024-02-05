
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
public class ConstructionPlanParser {
    private final ExprTokenizer token; //token = เก็บ text ทั้งบรรทักเลย
    public ConstructionPlanParser(ExprTokenizer ConstructionPlan_Input){
        this.token = ConstructionPlan_Input ;
    }
    public void parse() throws ExprErrorException {
        Statement();
    }
    public void Statement() throws ExprErrorException {
        if (token.peek("if")) {
            token.consume("if");
            IfStatement();
        } else if (token.peek("while")) {
            token.consume("while");
            WhileStatement();
        } else if (token.peek("{")) {
            token.consume("{");
            BlockStatement();
        } else {
            Command();
        }
    }
    public void Command() throws ExprErrorException {
        if (matchIdentifier()) {
            token.consume();
            AssignmentStatement();
        } else {
            ActionCommand();
        }
    }
    public void AssignmentStatement() throws ExprErrorException {
        token.consume("=");
        Expression();
    }
    public void ActionCommand() throws ExprErrorException {
        switch (token.peek()) {
            case "done":
                token.consume("done");
                break;
            case "relocate":
                token.consume("relocate");
                break;
            case "move":
                token.consume("move");
                MoveCommand();
                break;
            case "invest":
                token.consume("invest");
                RegionCommand();
                break;
            case "collect":
                token.consume("collect");
                RegionCommand();
                break;
            case "shoot":
                token.consume("shoot");
                AttackCommand();
                break;
            default:
                throw new RuntimeException("Invalid action command: " + token.peek());
        }

    }
    public void MoveCommand() throws ExprErrorException {
        Direction();
    }
    public void RegionCommand() throws ExprErrorException {
//        if (token.peek("invest") || token.peek("collect")) {
            Expression();
//        } else {
//            throw new RuntimeException("Invalid region command");
//        }
    }
    public void AttackCommand() throws ExprErrorException {
        if(token.peek("shoot")){
            Direction();
            Expression();
        }else {
            throw new ExprErrorException("Invalid command");
        }
    }
    public void Direction() throws ExprErrorException {
        String[] validDirections = {"up", "down", "upleft", "upright", "downleft", "downright"};
        String token = this.token.peek();
        if (Arrays.asList(validDirections).contains(token)) {
            // need to fix
            System.out.println(this.token.peek());
            this.token.consume();
        } else {
            throw new RuntimeException("Invalid direction: " + token);
        }
    }
    public void BlockStatement() throws ExprErrorException {
        if(token.peek("{")) {return;}
        while (!token.peek().equals("}")) {
            Statement();
        }
        if (token.peek("}")){
            token.consume("}");
        }
    }
    public void IfStatement() throws ExprErrorException {

        token.consume("(");
        Expression();
        token.consume(")");
        token.consume("then");
        Statement();
        token.consume("else");
        Statement();

    }
    public void WhileStatement() throws ExprErrorException {
        token.consume("(");
        Expression();
        token.consume(")");
        Statement();
    }
    public void Expression() throws ExprErrorException {
        Term();
        if(token.peek("+")) {
            token.consume("+");
            Expression();
        }
        if(token.peek("-")){
            token.consume("+");
            Expression();
        }
    }
    public void Term () throws ExprErrorException {
        Factor();
        while (token.peek("*") || token.peek("/") || token.peek("%")) {
            token.consume();
            Term ();
        }
        if(token.peek("*")){
            token.consume("*");
            Term ();
        }
        if(token.peek("/")){
            token.consume("/");
            Term ();
        }
        if(token.peek("%")){
            token.consume("%");
            Term ();
        }

    }
    public void Factor () throws ExprErrorException {
        Power();
        while (token.peek("^")) {
            token.consume("^");
            Factor();
        }
    }
    public void Power() throws ExprErrorException {
        if (matchIdentifier()){
            token.consume();
            System.out.println("dosomething form Power");
            return;
        }else if (matchNumber()) {
            token.consume();
            System.out.println("dosomething form Power");
            return;
        }
        if (token.peek("(")) {
            token.consume("(");
            Expression();
            token.consume(")");
        }
        InfoExpression();

    }
    public void InfoExpression() throws ExprErrorException {
        if (token.peek("nearby")) {
            token.consume();
            Direction();
        } else if(token.peek("opponent")){
            System.out.println("Call opponent funtion");
        }else {
            throw new RuntimeException("Invalid InfoExpression");
        }
    }


    private boolean matchIdentifier() throws ExprErrorException {
        return Pattern.matches("[a-zA-Z][a-zA-Z0-9]*", token.peek());
    }

    private boolean matchNumber() {
        try {
            long number = Long.parseLong(token.peek());
            return number >= 0;
        } catch (NumberFormatException | ExprErrorException e) {
            return false;
        }

    }
    private boolean isReservedWord(String token) {
        List<String> reservedWords = Arrays.asList(
                "collect", "done", "down", "downleft", "downright", "else", "if", "invest",
                "move", "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft",
                "upright", "while"
        );
        return reservedWords.contains(token);
    }

    record VariableMaker(AST.Expr s, AST.Expr f) implements AST.Expr{
        public Float eval(Map<String,Float> bindings) throws EvalError {
            Float i = s.eval(bindings);
            Float j = f.eval(bindings);
            return i;
        }

        @Override
        public void prettyPrint(StringBuilder s) {

        }
    }

}
