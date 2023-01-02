import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class GlobalKeyListenerAndKeyPress implements NativeKeyListener {
    private static JFrame frame;
    private String KeyboardShortcuts_Convert;

    public void nativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyText(e.getKeyCode()).contains("F6")) {
            System.out.print("KeyboardShortcuts_Convert pressed");
//
            frame.setExtendedState(Frame.ICONIFIED);
            frame.setExtendedState(Frame.NORMAL);
            System.out.println("normal");
            GUI.pasteTojTextToConvert();

        }
        if (e.getKeyText(e.getKeyCode()).contains("F9")) {
            System.out.println("KeyboardShortcuts_Convert pressed1");
            SuperRobot robot = null;
            try {
                robot = new SuperRobot();
            } catch (AWTException ex) {
                ex.printStackTrace();
                System.out.println("the platform configuration does not allow low-level input control");
            }

            robot.typeKey(KeyEvent.VK_HOME);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.typeKey(KeyEvent.VK_END);
            robot.keyRelease(KeyEvent.VK_SHIFT);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.typeKey(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            GUI.pasteTojTextToConvert();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.typeKey(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            frame.setExtendedState(Frame.ICONIFIED);
            frame.setExtendedState(Frame.NORMAL);
            System.out.println("normal");
        }

    }

    public void nativeKeyReleased(NativeKeyEvent e) {
//        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public static void initGlobalKeyListener(JFrame frame) {
        GlobalKeyListenerAndKeyPress.frame = frame;
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerAndKeyPress());
    }
}
class SuperRobot extends Robot {
    public SuperRobot() throws AWTException {
    }

    public void typeKey(int keyCode) {
        keyPress(keyCode);
        delay(20);
        keyRelease(keyCode);
    }
}