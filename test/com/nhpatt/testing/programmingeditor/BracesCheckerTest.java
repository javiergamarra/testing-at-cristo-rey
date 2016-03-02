package com.nhpatt.testing.programmingeditor;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BracesCheckerTest {

    @Test
    public void parenthesisShouldBeClosed() {
        BracesChecker bracesChecker = new BracesChecker();
        assertTrue(bracesChecker.check("()"));
    }

    public class BracesChecker {

        public boolean check(String s) {
            return false;
        }

    }

}
