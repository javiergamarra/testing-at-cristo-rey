package com.nhpatt.testing.programmingeditor;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BracesCheckerTest {

    @Test
    public void closedParenthesisParse() {
        BracesChecker bracesChecker = new BracesChecker();
        assertTrue(bracesChecker.check("()"));
    }

    public class BracesChecker {

        public boolean check(String s) {
            int parenthesis = 0;
            for (Character character : s.toCharArray()) {
                parenthesis += character == '(' ? 1 : -1;
            }
            return parenthesis == 0;
        }

    }

}
