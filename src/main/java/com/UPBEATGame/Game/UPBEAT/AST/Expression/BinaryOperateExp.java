package com.UPBEATGame.Game.UPBEAT.AST.Expression;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.AST.ASTErrorException.*;
import com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class BinaryOperateExp extends Expr {
    private final Expr left;
    private final Expr right;
    private final String operator;

    public BinaryOperateExp(Expr left, String operator, Expr right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public long eval(PlayerInstance command) {
        long leftValue = left.eval(command);
        long rightValue = right.eval(command);
            return switch (operator) {
                case "+" -> leftValue + rightValue;
                case "-" -> leftValue - rightValue;
                case "*" -> leftValue * rightValue;
                case "/" -> leftValue / rightValue;
                case "%" -> leftValue % rightValue;
                case "^" -> (long) Math.pow(leftValue, rightValue);
                default -> throw new UnknownOperator(operator);
            };
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", left.toString(), operator, right.toString());
    }
}
