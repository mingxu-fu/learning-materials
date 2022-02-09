package com.yingxue.lesson.task;



import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class jiaoben {

    public static void main(String[] args) throws AWTException {
        try {
            while (true){
                Point point = MouseInfo.getPointerInfo().getLocation();
                Robot robot = new Robot();
                robot.delay(1500);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.delay(100);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                System.out.println(point);
            }

     } catch (AWTException e) {
            e.printStackTrace();
        }

    }

}
