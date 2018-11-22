package com.smilepasta.urchin;

import com.smilepasta.urchin.utils.DateUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_page_date() {
        int pageData = DateUtil.getDecrementPageDate(20181121);
        System.out.println("20181121 = >" + pageData);
        int pageData1 = DateUtil.getDecrementPageDate(20181120);
        System.out.println("20181120 = >" + pageData1);
        int pageData2 = DateUtil.getDecrementPageDate(20181119);
        System.out.println("20181119 = >" + pageData2);
        int pageData3 = DateUtil.getDecrementPageDate(20181118);
        System.out.println("20181118 = >" + pageData3);
        int pageData4 = DateUtil.getDecrementPageDate(20181101);
        System.out.println("20181101 = >" + pageData4);
        int pageData5 = DateUtil.getDecrementPageDate(20181101);
        System.out.println("20181100 = >" + pageData5);
        int pageData6 = DateUtil.getDecrementPageDate(20181001);
        System.out.println("20181000 = >" + pageData6);
        int pageData7 = DateUtil.getDecrementPageDate(20180901);
        System.out.println("20180900 = >" + pageData7);
    }
}