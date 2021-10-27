package com.shoaibkhan.modmanager;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testGetMinecraftDirectory() {
        System.out.print(profileManager.getMinecraftDirectory());
    }
}
