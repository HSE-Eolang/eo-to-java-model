package org.eolang;

import org.eolang.core.data.EODataObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test case for {@link EOint}
 *
 */
class EOintTest {

    /***
     * Test for datization
     */
    @Test
    @DisplayName("Test Dataization")
    void _getData() {
        final EOint left = new EOint(12L);
        assertEquals(left._getData().toInt(), 12L);
    }

    /**
     * Tests for addition
     */
    @Test
    void add() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
//        Immutability
//        left.add(right);
//        assertEquals(12, left._getData().toInt());

//        Addition
        MatcherAssert.assertThat(
                left.EOadd(right)._getData().toInt(),
                Matchers.equalTo(20L)
        );
    }

    /**
     * Tests for subtraction
     */
    @Test
    void sub() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);

        MatcherAssert.assertThat(
                left.EOsub(right)._getData().toInt(),
                Matchers.equalTo(4L)
        );
    }

    /**
     * Tests for division
     */
    @Test
    void div() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(4L);

        MatcherAssert.assertThat(
                left.EOdiv(right)._getData().toInt(),
                Matchers.equalTo(3L)
        );
    }

    /**
     * Tests for multiplication
     */
    @Test
    void mul() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);

        MatcherAssert.assertThat(
                left.EOmul(right)._getData().toInt(),
                Matchers.equalTo(96L)
        );
    }

    /**
     * Tests for equals
     */
    @Test
    void EOeq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool eq = new EOint(left._getData().toInt()).EOeq(right);

        MatcherAssert.assertThat(
                eq._getData().toBoolean(),
                Matchers.equalTo(false)
        );
    }

    /**
     * Tests for not equals
     */
    @Test
    void EOneq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool notEqualTo = new EOint(left._getData().toInt()).EOneq(right);

        MatcherAssert.assertThat(
                notEqualTo._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /**
     * Tests for less than
     */
    @Test
    void EOless() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool less = new EOint(left._getData().toInt()).EOless(right);

        MatcherAssert.assertThat(
                less._getData().toBoolean(),
                Matchers.equalTo(false)
        );
    }

    /**
     * Tests for less than or equals
     */
    @Test
    void EOleq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool lessThanOrEqualTo = new EOint(left._getData().toInt()).EOleq(right);

        MatcherAssert.assertThat(
                lessThanOrEqualTo._getData().toBoolean(),
                Matchers.equalTo(false)
        );
    }

    /**
     * Tests for greater than
     */
    @Test
    void EOgreater() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool greater = new EOint(left._getData().toInt()).EOgreater(right);

        MatcherAssert.assertThat(
                greater._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /**
     * Tests for greater than or equals
     */
    @Test
    void EOgeq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool greaterOrEqualTo = new EOint(left._getData().toInt()).EOgeq(right);

        MatcherAssert.assertThat(
                greaterOrEqualTo._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /**
     * Tests for negation
     */
    @Test
    void EOneg() {
        final EOint left = new EOint(12L);
        final EOint neg = left.EOneg();

        MatcherAssert.assertThat(
                neg._getData().toInt(),
                Matchers.equalTo(-12L)
        );

    }

    /**
     * Tests for absolute number
     */
    @Test
    void EOabs() {
        final EOint left = new EOint(12L);
        final EOint absolute = left.EOabs();

        MatcherAssert.assertThat(
                absolute._getData().toInt(),
                Matchers.equalTo(12L)
        );
    }

    /**
     * Tests for all three possibilities of signum
     * @param number an integer representing the number to apply {@code EOsignum()} to
     */
    @DisplayName("Test signum")
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {-23, 0, 7})
    void EOsignum(int number) {
        MatcherAssert.assertThat(
                new EOint(
                        number
                ).EOsignum()._getData().toInt(),
                Matchers.equalTo((long)Math.signum(number))
        );
    }

    /***
     * Test for power
     * @param exponent An integer representing the exponent
     */
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("Test powers")
    void EOpow(int exponent) {
        MatcherAssert.assertThat(
                new EOint(
                        12L
                ).EOpow(
                        new EODataObject(
                                exponent
                        )
                )._getData().toInt(),
                Matchers.equalTo((long)Math.pow(12, exponent))
        );

    }

    /***
     * Tests Zero to the power Zero
     */
    @Test
    void zeroToZeroPower() {
        MatcherAssert.assertThat(
                new EOint(
                        0L
                ).EOpow(
                        new EODataObject(
                                0
                        )
                )._getData().toInt(),
                Matchers.equalTo(1L)
        );

    }

    /***
     * Tests zero to the power of a negative number
     */
//    @Test
//    void zeroToNegative() {
//        MatcherAssert.assertThat(
//                new EOint(
//                        0L
//                ).EOpow(
//                        new EODataObject(
//                                -1
//                        )
//                )._getData().toInt(),
//                Matchers.equalTo((int) Math.pow(0, -1))
//        );
//
//    }

    /***
     * Tests for modulo
     */
    @Test
    void EOmod() {
        final EOint left = new EOint(12L);
        final EOint modulo = left.EOmod(new EODataObject(5));

        MatcherAssert.assertThat(
                modulo._getData().toInt(),
                Matchers.equalTo(2L)
        );
    }
}