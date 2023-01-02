import java.util.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Convert {
    private static char[][] charArr6;
    private static Map<Character, Character>[] maps = new HashMap[6];

    public static void main1(String[] args) {
        initialize();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("הכנס טקטס");
            String input = scanner.nextLine();
            System.out.println(input);

            Set<String> stringSet = convert(input);


            System.out.println("בחר מספר שורה להעתקה");
            String[] stringArr = new String[stringSet.size()];
            int i = 0;
            for (String s : stringSet) {
                stringArr[i] = s;
                System.out.println("X " + (i + 1) + ".     " + s);
                i++;
            }

            int numToCopy = 0;
            while (numToCopy < 1 || numToCopy > stringArr.length)
                try {
                    numToCopy = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("יש לבחור מספר משפט");
                }
            copySentenceNum(stringArr, numToCopy - 1);
        }


    }
    public static void copyText(String text){
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    private static void copySentenceNum(String[] stringArr, int numToCopy) {
        copyText(stringArr[numToCopy]);
    }

    public static void initialize() {
        String lowQwerty = "qwertyuiop[]\\asdfghjkl;'zxcvbnm,./";
        String heQwerty = "/'קראטוןםפ][\\שדגכעיחלךף,זסבהנמצתץ.";
        String upQwerty = "QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";
        System.out.println();

        System.out.println();
        for (int i = 0; i < 6; i++) {
            maps[i] = new HashMap<>();
        }
        for (int i = 0; i < lowQwerty.length(); i++) {
            maps[0].put(lowQwerty.charAt(i), heQwerty.charAt(i));
            maps[1].put(lowQwerty.charAt(i), upQwerty.charAt(i));
            maps[2].put(heQwerty.charAt(i), lowQwerty.charAt(i));
            maps[3].put(heQwerty.charAt(i), upQwerty.charAt(i));
            maps[4].put(upQwerty.charAt(i), heQwerty.charAt(i));
            maps[5].put(upQwerty.charAt(i), lowQwerty.charAt(i));
        }
    }

    public static Set<String> convert(String input) {
        charArr6 = new char[6][input.length()];
        for (int indexOfMaps = 0; indexOfMaps < 6; indexOfMaps++) {
            for (int i = 0; i < input.length(); i++) {
                try {
                    charArr6[indexOfMaps][i] = maps[indexOfMaps].get(input.charAt(i));
                } catch (NullPointerException n) {
                    charArr6[indexOfMaps][i] = input.charAt(i);
                }
            }
        }

        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            stringSet.add(String.valueOf(charArr6[i]));
        }
        stringSet.remove(input);
        return stringSet;
    }

}

class Couple {
    public char[] charArr = new char[2];


    public Couple(char char1, char char2) {
        this.charArr[0] = char1;
        this.charArr[1] = char2;
    }

    @Override
    public String toString() {
        return "" + charArr[0] + " " + charArr[1];
    }
}
