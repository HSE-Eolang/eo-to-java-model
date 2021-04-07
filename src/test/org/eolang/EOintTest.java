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
 * Test cases for {@link EOint}
 *
 */
class EOintTest {

    /***
     * Test for datization
     * Checks if the data is returned
     */
    @Test
    @DisplayName("Test Dataization")
    void _getData() {
        final EOint left = new EOint(12L);
        assertEquals(left._getData().toInt(), 12L);
    }

    /***
    * Test for {@code EOadd}
    * checks if addition is successful
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

    /***
    * Test for {@code EOsub}
    * checks if subtraction is successful
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

    /***
    * Test for {@code EOdiv}
    * checks if division is successful
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

    /***
    * Test for {@code EOmul}
    * checks if multiplication is successful
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

    /***
    * Test for {@code EOeq}
    * checks equality (==)
    */
    @Test
    void EOeq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(12L);
        final EObool eq = new EObool(left.EOeq(right)._getData().toBoolean());

        MatcherAssert.assertThat(
                eq._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /***
    * Test for {@code EOneq}
    * checks equality (!=)
    */
    @Test
    void EOneq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool notEqualTo = new EObool(left.EOneq(right)._getData().toBoolean());

        MatcherAssert.assertThat(
                notEqualTo._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /***
    * Test for {@code EOless}
    * checks if the left side value is less than that of the right side
    */
    @Test
    void EOless() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(18L);
        final EObool less = new EObool(left.EOless(right)._getData().toBoolean());

        MatcherAssert.assertThat(
                less._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /***
    * Test for {@code EOless}
    * checks if the left side value is less than or equal to that of the right side
    */
    @Test
    void EOleq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(12L);
        final EObool lessThanOrEqualTo = new EObool(left.EOleq(right)._getData().toBoolean());

        MatcherAssert.assertThat(
                lessThanOrEqualTo._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /***
    * Test for {@code EOgreater}
    * checks if the left side value is greater than that of the right side
    */
    @Test
    void EOgreater() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool greater = new EObool(left.EOgreater(right)._getData().toBoolean());

        MatcherAssert.assertThat(
                greater._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /***
    * Test for {@code EOgeq}
    * checks if the left side value is greater than or equal to that of the right side
    */
    @Test
    void EOgeq() {
        final EOint left = new EOint(12L);
        final EOint right = new EOint(8L);
        final EObool greaterOrEqualTo = new EObool(left.EOgeq(right)._getData().toBoolean());

        MatcherAssert.assertThat(
                greaterOrEqualTo._getData().toBoolean(),
                Matchers.equalTo(true)
        );
    }

    /***
    * Test for {@code EOneg}
    * checks if the base value gets negated
    */
    @Test
    void EOneg() {
        final EOint base = new EOint(12L);
        final EOint neg = base.EOneg();

        MatcherAssert.assertThat(
                neg._getData().toInt(),
                Matchers.equalTo(-12L)
        );

    }

    /***
    * Test for {@code EOabs}
    * checks if the base value is returned as a non-negative number
    */
    @Test
    void EOabs() {
        final EOint base = new EOint(-12L);
        final EOint absolute = base.EOabs();

        MatcherAssert.assertThat(
                absolute._getData().toInt(),
                Matchers.equalTo(12L)
        );
    }

    /**
     * Tests for all three possibilities of {@code signum}
     * checks if the correct sign value is returned
     * @param number an integer representing the test value to apply {@code EOsignum} to
     */
    @ParameterizedTest(name = "{0}")
    @ValueSource(ints = {-23, 0, 7})
    @DisplayName("Test signum")
    void EOsignum(int number) {
        MatcherAssert.assertThat(
                new EOint(
                        number
                ).EOsignum()._getData().toInt(),
                Matchers.equalTo((long)Math.signum(number))
        );
    }

    /***
     * Test for {@EOpow}
     * checks if a number raised to a power is correctly evaluated
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
     * checks if a number raised to the power 0 returns 1
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
     * Test for {@code EOmod}
     * checks if the modulo of a number is the remainder after division
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