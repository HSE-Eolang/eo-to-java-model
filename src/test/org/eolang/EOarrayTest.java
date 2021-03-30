package org.eolang;

import org.eolang.core.EOObjectArray;
import org.eolang.core.data.EODataObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EOarrayTest {

    @Test
    void _getData() {
        EOarray array = new EOarray(new EOObjectArray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        ));
//        TO DO
//        EOObjectArray returns only the last element of the array
    }

    @Test
    void isEmpty() {
        EOarray array = new EOarray(new EOObjectArray());
        MatcherAssert.assertThat(array.EOisEmpty()._getData().toBoolean(), Matchers.equalTo(true));

    }

    @Test
    void length() {
        EOarray array = new EOarray(new EOObjectArray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        ));
        MatcherAssert.assertThat(array.EOlength()._getData().toInt(), Matchers.equalTo(5L));
    }

    @Test
    void get() {
        EOarray array = new EOarray(new EOObjectArray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        ));
        MatcherAssert.assertThat(array.EOget(new EODataObject(2))._getData().toInt(), Matchers.equalTo(5L));
    }

    @Test
    void append() {
        EOarray array = new EOarray(new EOObjectArray(
                new EODataObject(1),
                new EODataObject(3),
                new EODataObject(5),
                new EODataObject(7),
                new EODataObject(9)
        ));
        EOarray appendedArray = new EOarray(array.EOappend(new EODataObject(10)));
        MatcherAssert.assertThat(appendedArray.EOget(new EODataObject(5))._getData().toInt(), Matchers.equalTo(10L));

    }

    @Test
    void reduce() {
    }

    @Test
    void EOeach() {
    }

    @Test
    void map() {
    }

    @Test
    void mapi() {
    }
}