import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class GUI {


    protected static JFrame frame;
    private static JPanel panel;
    private static JTextField jTextToConvert;
    private static JButton[] result;
    private static Color colorCopied = Color.CYAN;

    public static void main(String[] args) {
        Convert.initialize();
        // Creating instance of JFrame
        frame = new JFrame("היפוך");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific
         * positions in a JFrame. Inside panels we can add text
         * fields, buttons and other components.
         */
        panel = new JPanel();
        // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        placeComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);
        GlobalKeyListenerAndKeyPress.initGlobalKeyListener(frame);
        System.out.println("dfsdfsdf");
    }

    private static void placeComponents(JPanel panel) {
        JButton button = new JButton("חחלף");
        button.setBounds(10, 80, 80, 25);
        panel.add(button);
        JButton buttonClear = new JButton("\uD83D\uDDD1");
        Font font = new Font("Dialog", Font.PLAIN, 15);
        buttonClear.setForeground(Color.RED);
        buttonClear.setFont(font);
        buttonClear.setBounds(10, 80, 10, 10);
        panel.add(buttonClear);

        panel.add(buttonClear);
        JButton buttonPaste = new JButton("\uD83D\uDCCB");
        buttonPaste.setBounds(10, 80, 10, 10);
        panel.add(buttonPaste);

        panel.add(buttonPaste);

        JLabel jLabel_textToConvert = new JLabel("יש להכניס טקסט להחלקפה");

        jLabel_textToConvert.setBounds(150, 20, 100, 25);
        panel.add(jLabel_textToConvert);


        jTextToConvert = new JTextField(20);
        jTextToConvert.setBounds(10, 20, 50, 25);
        panel.add(jTextToConvert);
        jTextToConvert.setDragEnabled(true);
        jTextToConvert.requestFocusInWindow();


        actionComponents(button, buttonClear, buttonPaste);
    }

    private static void actionComponents(JButton button, JButton buttonClear, JButton buttonPaste) {

        button.addActionListener(e -> convertAndAppear());

        buttonClear.addActionListener(e -> {
            jTextToConvert.setText("");
            jTextToConvert.requestFocusInWindow();
        });

        buttonPaste.addActionListener(e -> pasteTojTextToConvert();
        // It is impossible to become a lambda because there are other methods in the interface
        jTextToConvert.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    convertAndAppear();
            }
        });
    }

    public static void pasteTojTextToConvert() {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = c.getContents(frame);
        if (t == null)
            return;
        try {
            jTextToConvert.setText((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        jTextToConvert.requestFocusInWindow();
        convertAndAppear();
        result[0].doClick();
    }

    private static void convertAndAppear() {
        if (result != null) {
            for (JButton jButton : result) {
                panel.remove(jButton);
            }
        }
        Set<String> stringSet = Convert.convert(jTextToConvert.getText());
        System.out.println("בחר מספר שורה להעתקה");
        String[] stringArr = new String[stringSet.size()];
        int y = 50;
        int i = 0;
        result = new JButton[stringSet.size()];
        for (String s : stringSet) {
            stringArr[i] = s;
            System.out.println("ו " + (i + 1) + ".     " + s);

            JButton jButtonResult = new JButton(/*"ו " +*/ (i + 1) + ".     " + s);
            jButtonResult.setBounds(10, y, 200, 25);
            jButtonResult.addActionListener(e -> {
                Convert.copyText(s);
//                jButtonResult.setText("הועתק: " + s);
                jButtonResult.setBackground(colorCopied);
//                jButtonResult.setInheritsPopupMenu(true);
                copiedBeforeUnmark(jButtonResult);
            });
            result[i] = jButtonResult;


            panel.add(result[i]);

            i++;
            y += 10;
        }

        frame.revalidate();
        frame.repaint();

        frame.remove(panel);
        frame.add(panel);


    }

    private static void copiedBeforeUnmark(JButton jButtonResult) {
        for (int i = 0; i < result.length; i++) {
            if (result[i].equals(jButtonResult))
                continue;
            if (result[i].getBackground() == colorCopied) {
                result[i].setBackground(Color.LIGHT_GRAY);
               /* String textButtonCopied =result[i].getText().substring(0,"הועתק: ".length());
                result[i].setText();*/
            }
        }
//        for (JButton jButton : result) {
//            if (jButton.equals(jButtonResult))
//                continue;
//            if (jButton.getBackground() == colorCopied) {
//                jButton.setBackground(Color.LIGHT_GRAY);
//                jButton.setText(textButtonCopied);
//            }
//
//        }
    }

}
