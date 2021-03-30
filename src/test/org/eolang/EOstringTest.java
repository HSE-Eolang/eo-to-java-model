package org.eolang;

import org.eolang.core.data.EODataObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EOstringTest {

    @Test
    void _getData() {
        EOstring string = new EOstring("Hello");
        MatcherAssert.assertThat(string._getData().toString(), Matchers.equalTo("Hello"));
    }

    @Test
    void EOtrim() {
        EOstring string = new EOstring(" Hello ");
        MatcherAssert.assertThat(
                string.EOtrim()._getData().toString(),
                Matchers.equalTo("Hello")
        );
    }

    @Test
    void toInt() {
        EOstring string = new EOstring("12");
        MatcherAssert.assertThat(string.EOtoInt()._getData().toInt(), Matchers.equalTo(12L));
    }

    @Test
    void EOeq() {
        EOstring string = new EOstring("Hello");
        MatcherAssert.assertThat(
                string.EOeq(
                        new EOstring("Hello")
                )._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }
}