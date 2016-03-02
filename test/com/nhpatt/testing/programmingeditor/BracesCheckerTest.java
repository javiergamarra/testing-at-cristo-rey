package com.nhpatt.testing.programmingeditor;

import org.junit.Test;

import java.util.Stack;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class BracesCheckerTest {

    @Test
    public void closedParenthesisParse() {
        BracesChecker bracesChecker = new BracesChecker();
        assertTrue(bracesChecker.check("()"));
    }

    @Test
    public void twoOpenParenthesisFail() {
        BracesChecker bracesChecker = new BracesChecker();
        assertFalse(bracesChecker.check("(("));
    }

    @Test
    public void invertParenthesisFail() {
        BracesChecker bracesChecker = new BracesChecker();
        assertFalse(bracesChecker.check(")("));
    }

    @Test
    public void closedBracketsPass() {
        BracesChecker bracesChecker = new BracesChecker();
        assertTrue(bracesChecker.check("[]"));
    }

    @Test
    public void differentBracketsFail() {
        BracesChecker bracesChecker = new BracesChecker();
        assertFalse(bracesChecker.check("[)"));
    }


    @Test
    public void complexChecksPass() {
        BracesChecker bracesChecker = new BracesChecker();
        assertTrue(bracesChecker.check("[()]"));
    }

    public class BracesChecker {

        public boolean check(String s) {
            Stack<Character> stack = new Stack<>();

            for (Character character : s.toCharArray()) {
                if (character == '(' || character == '[') {
                    stack.push(character);
                } else if (!stack.isEmpty() && paired(character, stack.peek())) {
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }

        private boolean paired(Character character, Character lastChar) {
            return character == ')' && lastChar == '(' || character == ']' && lastChar == '[';
        }

    }

}
