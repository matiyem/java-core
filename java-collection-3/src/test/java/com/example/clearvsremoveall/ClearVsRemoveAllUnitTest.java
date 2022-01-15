package com.example.clearvsremoveall;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


class ClearVsRemoveAllUnitTest {
//    ابتدا به متد Collection.clear() می پردازیم. بیایید Javadoc روش را بررسی کنیم. بر اساس آن، هدف clear() حذف تک تک عناصر از لیست است.
//بنابراین، اساساً فراخوانی ()clear در هر لیستی باعث خالی شدن لیست می شود.

//    اکنون نگاهی به Javadoc از Collection.removeAll (). می بینیم که متد یک collection را به عنوان آرگومان می گیرد. و هدف آن حذف تمام عناصر مشترک بین list و collection است.

    /*
     * Tests
     */
    @Test
    void givenArrayListWithElements_whenClear_thenListBecomesEmpty() {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        collection.clear();

        assertTrue(collection.isEmpty());
    }

    @Test
    void givenTwoArrayListsWithCommonElements_whenRemoveAll_thenFirstListMissElementsFromSecondList() {
        Collection<Integer> firstCollection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collection<Integer> secondCollection = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));

        firstCollection.removeAll(secondCollection);

        assertEquals(Arrays.asList(1, 2), firstCollection);
        assertEquals(Arrays.asList(3, 4, 5, 6, 7), secondCollection);
    }

}
