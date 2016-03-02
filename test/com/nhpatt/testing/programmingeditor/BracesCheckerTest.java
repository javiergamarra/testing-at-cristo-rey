package com.nhpatt.testing.programmingeditor;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class BracesCheckerTest {

    private BracesChecker bracesChecker;

    @Before
    public void setUp() {
        bracesChecker = new BracesChecker();
    }

    @Test
    public void closedParenthesisParse() {
        assertTrue(bracesChecker.check("()"));
    }

    @Test
    public void twoOpenParenthesisFail() {
        assertFalse(bracesChecker.check("(("));
    }

    @Test
    public void invertParenthesisFail() {
        assertFalse(bracesChecker.check(")("));
    }

    @Test
    public void closedBracketsPass() {
        assertTrue(bracesChecker.check("[]"));
    }

    @Test
    public void differentBracketsFail() {
        assertFalse(bracesChecker.check("[)"));
    }

    @Test
    public void complexChecksPass() {
        assertTrue(bracesChecker.check("[()]"));
    }

    @Test
    public void otherBracketsAreValidToo() {
        assertTrue(bracesChecker.check("{}"));
    }

    @Test
    public void hugeValidTest() {
        for (String string : valid) {
            assertTrue(bracesChecker.check(string));
        }
    }

    @Test
    public void hugeInvalidTest() {
        for (String string : invalid) {
            assertFalse(bracesChecker.check(string));
        }
    }

    String[] valid = {"()", "[]", "{}", "(){}[]", "([{}])", "({})[({})]", "(({{[[]]}}))", "{}({})[]"};
    String[] invalid = {"[(])", "(}", "(})", ")(}{][", "())({}}{()][][", "(((({{", "}]]))}])",};

    public class BracesChecker {

        public boolean check(String s) {
            Stack<Character> stack = new Stack<>();

            for (Character character : s.toCharArray()) {
                if (!stack.isEmpty() && paired(character, stack.peek())) {
                    stack.pop();
                } else {
                    stack.push(character);
                }
            }
            return stack.isEmpty();
        }

        private boolean paired(char a, char b) {
            return a == ')' && b == '(' || a == ']' && b == '[' || a == '}' && b == '{';
        }

    }

}
