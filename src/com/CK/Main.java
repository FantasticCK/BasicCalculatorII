package com.CK;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String s = " -3+5 / -2 * 9";
//        String[] cArr = s.split("\\w");
//        System.out.println(cArr);
        Solution solution = new Solution();
        System.out.println(solution.calculate(s));
    }
}

class Solution {
    public int calculate(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (((!Character.isDigit(s.charAt(i)) && (s.charAt(i)) != ' ')) || i == len - 1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }
}

class Solution {
    public int calculate(String s) {
        if (s.length() == 0)
            return 0;
        s = s + "$";
        int prevNum = 0, currNum = 0, sum = 0;
        char prevOpt = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            if (Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0';
                continue;
            }

            switch(prevOpt){
                case '+':
                    sum += prevNum;
                    prevNum = currNum;
                    break;
                case '-':
                    sum += prevNum;
                    prevNum = -currNum;
                    break;
                case '*':
                    prevNum *= currNum;
                    break;
                case '/':
                    prevNum /= currNum;
                    break;
            }
            currNum = 0;
            prevOpt = c;
        }
        return sum + prevNum;
    }
}