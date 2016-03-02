package com.nhpatt.testing.programmingeditor;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PaginationHelperTest {

    private List<Integer> items = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    private PaginationHelper paginationHelper;

    @Before
    public void setUp() {
        paginationHelper = new PaginationHelper<>(items, 10);
    }

    @Test
    public void testItemCount() {
        assertEquals(24, paginationHelper.itemCount());
    }

    @Test
    public void testEmptyCollection() {
        paginationHelper = new PaginationHelper<>(new ArrayList<>(), 10);
        assertEquals(0, paginationHelper.itemCount());
    }

    @Test
    public void testPageItemCount() {
        assertEquals(3, paginationHelper.pageCount());
        assertEquals(10, paginationHelper.pageItemCount(0));
        assertEquals(10, paginationHelper.pageItemCount(1));
        assertEquals(4, paginationHelper.pageItemCount(2));
        assertEquals(-1, paginationHelper.pageItemCount(3));
    }

    @Test
    public void testPageIndex() {
        assertEquals(-1, paginationHelper.pageIndex(40));
        assertEquals(24, paginationHelper.itemCount());
        assertEquals(2, paginationHelper.pageIndex(22));
        assertEquals(0, paginationHelper.pageIndex(3));
        assertEquals(0, paginationHelper.pageIndex(0));
        assertEquals(-1, paginationHelper.pageIndex(-1));
        assertEquals(-1, paginationHelper.pageIndex(-23));
        assertEquals(-1, paginationHelper.pageIndex(-15));
    }

    public class PaginationHelper<I> {

        private List<I> collection;
        private int itemsPerPage;

        public PaginationHelper(List<I> collection, int itemsPerPage) {
            this.collection = collection;
            this.itemsPerPage = itemsPerPage;
        }

        public int itemCount() {
            return collection.size();
        }

        public int pageCount() {
            return collection.size() / itemsPerPage + (collection.size() % itemsPerPage != 0 ? 1 : 0);
        }

        public int pageItemCount(int pageIndex) {
            if (pageIndex < 0 || pageIndex >= pageCount()) {
                return -1;
            } else if (pageIndex == pageCount() - 1) {
                return collection.size() % itemsPerPage;
            } else {
                return itemsPerPage;
            }
        }

        public int pageIndex(int itemIndex) {
            if (itemIndex < 0 || itemIndex >= itemCount()) {
                return -1;
            } else {
                return (itemIndex / itemsPerPage);
            }
        }
    }
}
