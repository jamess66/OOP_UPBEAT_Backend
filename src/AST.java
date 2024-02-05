import java.util.Map;

public class AST {
    interface Node{
        void prettyPrint(StringBuilder s);
    }
    interface Expr extends Node {
        Float eval(Map<String, Float> bindings) throws EvalError;
    }

    record FloatLit(Float val) implements Expr{
        public Float eval(Map<String,Float> bindings){
            return val;
        }

        @Override
        public void prettyPrint(StringBuilder s) {
            s.append(val);
        }
    }
    record Variable(String name) implements Expr{
        public Float eval(Map<String,Float> bindings) throws EvalError {
            if(bindings.containsKey(name))
                return bindings.get(name);
            throw new EvalError(
                    "undefined variable: " + name);
        }
        public void prettyPrint(StringBuilder s){
            s.append(name);
        }
    }
}
