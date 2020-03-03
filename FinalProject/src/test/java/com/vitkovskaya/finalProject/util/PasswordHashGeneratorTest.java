package com.vitkovskaya.finalProject.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordHashGeneratorTest {
    PasswordHashGenerator generator = new PasswordHashGenerator();
    @Test
    public void testHash() {
        String actual = generator.hash("2016-Om-7");
        String expected = "e84ceafb2fadee8f4290972263face2f1d7887b9";
        Assert.assertEquals(actual, expected);
    }

}