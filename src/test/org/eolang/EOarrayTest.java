package org.eolang;

import org.eolang.core.data.EODataObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link EOarray}
 */
class EOarrayTest {

    /***
     * Test for dataization
     * Checks if the data is returned
     */
    @Test
    void getArray() {
        EOarray array = new EOarray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        );
        EOarray eOarray = array;
        MatcherAssert.assertThat(eOarray.EOlength()._getData().toInt(),
                Matchers.equalTo(5L));
    }

    /***
     * Test for {@code EOisEmpty}
     * checks if an array is empty
     */
    @Test
    void isEmpty() {
        EOarray array = new EOarray();
        MatcherAssert.assertThat(
                array.EOisEmpty()._getData().toBoolean(),
                Matchers.equalTo(true)
        );

    }

    /***
     * Test for {@code EOlength}
     * checks if the length of the array is returned
     */
    @Test
    void length() {
        EOarray array = new EOarray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        );
        MatcherAssert.assertThat(
                array.EOlength()._getData().toInt(),
                Matchers.equalTo(5L)
        );
    }

    /***
     * Test for {@code EOget}
     * checks if the element at a specified position of the array is returned
     */
    @Test
    void get() {
        EOarray array = new EOarray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        );
        MatcherAssert.assertThat(
                array.EOget(
                        new EODataObject(2))._getData().toInt(),
                Matchers.equalTo(5L)
        );
    }

    /***
     * Test for {@code EOappend}
     * checks if an element successfully appends to an array
     */
    @Test
    void append() {
        EOarray array = new EOarray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        );
        EOarray appendedArray = array.EOappend(new EODataObject(10));
        MatcherAssert.assertThat(
                appendedArray.EOget(new EODataObject(5))._getData().toInt(),
                Matchers.equalTo(10L)
        );

    }

    /***
     * Test for {@code EOreduce}
     * To Do
     */
    @Test
    void reduce() {
    }

    /***
     * Test for {@code EOeach}
     * To Do
     */
    @Test
    void EOeach() {
    }

    /***
     * Test for {@code EOmap}
     * To Do
     */
    @Test
    void map() {
    }

    /***
     * Test for {@code EOmapi}
     * To Do
     */
    @Test
    void mapi() {
    }
}