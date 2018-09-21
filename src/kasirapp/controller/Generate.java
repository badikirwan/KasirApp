/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller;

import java.util.Random;

/**
 *
 * @author BadikIrwan
 */
public class Generate {
    
    private final char[] chars = "1234567890".toCharArray();
    private final StringBuilder stringBuilder = new StringBuilder();
    private final Random random = new Random();
    private String result;
    
    public String getRandom() {
        for (int lenght = 0; lenght < 5; lenght++) {
            Character character = chars[random.nextInt(chars.length)];
            stringBuilder.append(character);
        }
        result = "BRG"+stringBuilder.toString();
        stringBuilder.delete(0, 10);

        return result;
    }
}
