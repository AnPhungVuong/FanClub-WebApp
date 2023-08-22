/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cst8218.assignemnt2.entity.Bouncer;

/**
 *
 * @author anvp0
 */
public class JUnitTest {

    @Test
    public void testBouncerConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Bouncer(-32, 534, -23));
        assertThrows(IllegalArgumentException.class, () -> new Bouncer(32, -534, 23));
    }

    @Test
    public void testXConstructor() {
        Bouncer bouncer = new Bouncer(0, 0, 0);
        assertDoesNotThrow(() -> bouncer.setX(100));
        assertThrows(IllegalArgumentException.class, () -> bouncer.setX(-100));
        assertThrows(IllegalArgumentException.class, () -> bouncer.setX(600));
    }

    @Test
    public void testYConstructor() {
        Bouncer bouncer = new Bouncer(0, 0, 0);
        assertDoesNotThrow(() -> bouncer.setY(100));
        assertThrows(IllegalArgumentException.class, () -> bouncer.setY(-100));
        assertThrows(IllegalArgumentException.class, () -> bouncer.setY(600));
    }
    
    @Test
    public void testAdvanceOneFrame() {
        Bouncer bouncer = new Bouncer(20, 220, 50);
        bouncer.advanceOneFrame();
        assertEquals(20, bouncer.getX());
        
        Bouncer bouncer1 = new Bouncer(20, 270, 50);
        bouncer1.advanceOneFrame();
        assertEquals(320, bouncer1.getY());
        
        Bouncer bouncer2 = new Bouncer(20, 320, -50);
        bouncer2.advanceOneFrame();
        assertEquals(270, bouncer2.getY());
        
        Bouncer bouncer3 = new Bouncer(20, 220, 50);
        bouncer3.advanceOneFrame();
        assertEquals(50, bouncer3.getYSpeed());

    }
}
