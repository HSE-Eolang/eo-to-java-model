package org.eolang;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


class EOcharTest {

    @Test
    void _getData() {
        final EOchar character = new EOchar('J');
        MatcherAssert.assertThat(character._getData().toString(), Matchers.equalTo("J"));
    }

    @Test
    void EOtoString() {
        final EOchar character = new EOchar('J');
        MatcherAssert.assertThat(character.EOtoString()._getData().toString(), Matchers.equalTo("J"));
    }
}