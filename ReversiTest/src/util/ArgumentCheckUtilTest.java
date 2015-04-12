// CHECKSTYLE:OFF

package util;

import org.junit.Test;

import core.Cell;
import junit.framework.Assert;

public class ArgumentCheckUtilTest {

    @Test
    public void checkNotNullに非Null() {
        ArgumentCheckUtil.checkNotNull(new Object());
        Assert.assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void checkNotNullにNull() {
        ArgumentCheckUtil.checkNotNull(null);
    }
    
    @Test
    public void checkNotNothingにBLACK() {
        ArgumentCheckUtil.checkNotNothing(Cell.BLACK);
        Assert.assertTrue(true);
    }
    
    @Test
    public void checkNotNothingにWHITE() {
        ArgumentCheckUtil.checkNotNothing(Cell.WHITE);
        Assert.assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void checkNotNothingにNOTHING() {
        ArgumentCheckUtil.checkNotNothing(Cell.NOTHING);
    }
    
    @Test
    public void checkNotNegativeValueに正の値() {
        ArgumentCheckUtil.checkNotNegativeValue(1);
        Assert.assertTrue(true);
    }
    
    @Test
    public void checkNotNegativeValueに0() {
        ArgumentCheckUtil.checkNotNegativeValue(0);
        Assert.assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void checkNotNegativeValueに負の値() {
        ArgumentCheckUtil.checkNotNegativeValue(-1);
    }
    
    @Test
    public void checkNotZeroAndNegativeValueに正の値() {
        ArgumentCheckUtil.checkNotZeroAndNegativeValue(1);
        Assert.assertTrue(true);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void checkNotZeroAndNegativeValueに0() {
        ArgumentCheckUtil.checkNotZeroAndNegativeValue(0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void checkNotZeroAndNegativeValueに負の値() {
        ArgumentCheckUtil.checkNotZeroAndNegativeValue(-1);
    }
}

//CHECKSTYLE:ON