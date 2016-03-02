package com.nhpatt.testing.programmingeditor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    public class BracesChecker {

        public boolean check(String s) {
            List<Character> braces = new ArrayList<>();
            for (Character character : s.toCharArray()) {
                if (character == '(' || character == '[') {
                    braces.add(character);
                } else if (braces.isEmpty()) {
                    return false;
                } else if (checkChar(character, braces.get(braces.size() - 1))) {
                    braces.remove(braces.size() - 1);
                }
            }
            return braces.isEmpty();
        }

        private boolean checkChar(Character character, Character lastChar) {
            return character == ')' && lastChar == '(' || character == ']' && lastChar == '[';
        }

    }

}
