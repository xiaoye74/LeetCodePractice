package com.qianxiang.leetcode;

import java.util.Stack;
/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are+,-,*,/. Each operand may be an integer or another expression.
Some examples:
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class StackSolve {
    public int evalRPN(String[] tokens) {
        Stack<Integer> sk = new Stack<>();
        for (String s : tokens){
            if (s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/"))
            {
                int b = sk.pop();
                int a = sk.pop();
                sk.push(Cal(s,a,b));
            }
            else
            {
                sk.push(Integer.parseInt(s));
            }
        }
        return sk.pop();
    }
    public int Cal(String op, int a, int b)
    {
        if (op.equals("+"))
            return a+b;
        else if (op.equals("-"))
            return a-b;
        else if (op.equals("*"))
            return a*b;
        else
            return a/b;
    }
}
