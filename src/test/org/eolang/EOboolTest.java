package org.eolang;

import org.eolang.core.data.EODataObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EOboolTest {

    @Test
    void _getData() {
        EObool boo = new EObool(true);
        MatcherAssert.assertThat(boo._getData().toBoolean(), Matchers.equalTo(true));
    }

    @Test
    void EOif() {
        EObool bool = new EObool(false);
        MatcherAssert.assertThat(bool.EOif(new EODataObject(1), new EODataObject((2)))._getData().toInt(), Matchers.equalTo(2L));
    }

    @Test
    void EOnot() {
        EObool bool = new EObool(true);
        MatcherAssert.assertThat(bool.EOnot()._getData().toBoolean(), Matchers.equalTo(false));
    }

    @Test
    void EOand() {
        EObool bool = new EObool(true);
        EObool rightBool = new EObool(false);
        MatcherAssert.assertThat(bool.EOand(rightBool)._getData().toBoolean(), Matchers.equalTo(false));
    }

    @Test
    void EOor() {
        EObool bool = new EObool(true);
        EObool rightBool = new EObool(false);
        MatcherAssert.assertThat(bool.EOor(rightBool)._getData().toBoolean(), Matchers.equalTo(true));
    }

    @Test
    void EOwhile() {
    }
}