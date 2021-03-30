package org.eolang;

import org.eolang.core.data.EODataObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EOfloatTest {

    /***
     * Test for datization
     */
    @Test
    @DisplayName("Test Dataization")
    void _getData() {
        final EOfloat left = new EOfloat(12);
        assertEquals(left._getData().toFloat(), 12);
    }

    @Test
    void EOadd() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        MatcherAssert.assertThat(
                left.EOadd(right)._getData().toFloat(),
                Matchers.equalTo(20.0)
        );
    }

    @Test
    void EOsub() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        MatcherAssert.assertThat(
                left.EOsub(right)._getData().toFloat(),
                Matchers.equalTo(4.0)
        );
    }

    @Test
    void EOdiv() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(4.0);
        MatcherAssert.assertThat(
                left.EOdiv(right)._getData().toFloat(),
                Matchers.equalTo(3.0)
        );
    }

    @Test
    void EOmul() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        MatcherAssert.assertThat(
                left.EOmul(right)._getData().toFloat(),
                Matchers.equalTo(96.0)
        );
    }

    @Test
    void EOeq() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        final EObool eq = new EOfloat(left._getData().toFloat()).EOeq(right);

        MatcherAssert.assertThat(
                eq._getData().toBoolean(),
                Matchers.equalTo(false)
        );
    }

    @Test
    void EOneq() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        final EObool notEqual = new EOfloat(left._getData().toFloat()).EOneq(right);

        MatcherAssert.assertThat(
                notEqual._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    @Test
    void EOless() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        final EObool less = new EOfloat(left._getData().toFloat()).EOless(right);

        MatcherAssert.assertThat(
                less._getData().toBoolean(),
                Matchers.equalTo(false)
        );
    }

    @Test
    void EOleq() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        final EObool lessThanOrEquals = new EOfloat(left._getData().toFloat()).EOleq(right);

        MatcherAssert.assertThat(
                lessThanOrEquals._getData().toBoolean(),
                Matchers.equalTo(false)
        );
    }

    @Test
    void EOgreater() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        final EObool greater = new EOfloat(left._getData().toFloat()).EOgreater(right);

        MatcherAssert.assertThat(
                greater._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    @Test
    void EOgeq() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat right = new EOfloat(8.0);
        final EObool greaterOrEquals = new EOfloat(left._getData().toFloat()).EOgeq(right);

        MatcherAssert.assertThat(
                greaterOrEquals._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    @Test
    void EOneg() {
        final EOfloat left = new EOfloat(12.0);
        final EOfloat neg = left.EOneg();

        MatcherAssert.assertThat(
                neg._getData().toFloat(),
                Matchers.equalTo(-12.0)
        );
    }

    @Test
    void EOabs() {
        final EOfloat left = new EOfloat(-12.0);
        final EOfloat absolute = left.EOneg();

        MatcherAssert.assertThat(
                absolute._getData().toFloat(),
                Matchers.equalTo(12.0)
        );
    }

    @DisplayName("Test signum")
    @ParameterizedTest(name = "{0}")
    @ValueSource(doubles = {-23.0, 0.0, 7.0})
    void EOsignum(double number) {
        MatcherAssert.assertThat(
                new EOfloat(
                        number
                ).EOsignum()._getData().toFloat(),
                Matchers.equalTo(Math.signum(number))
        );
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(doubles = {0.0, 1.0, 2.0, 3.0})
    @DisplayName("Test powers")
    void EOpow(double exponent) {
        MatcherAssert.assertThat(
                new EOfloat(
                        12.0
                ).EOpow(
                        new EODataObject(
                                exponent
                        )
                )._getData().toFloat(),
                Matchers.equalTo(Math.pow(12.0, exponent))
        );
    }
}