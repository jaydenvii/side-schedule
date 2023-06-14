

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class Util {

    public static Font fontText, fontTextSemiBold, fontTextBold;
    
    public static void loadFonts() {
        // normal-sized font
        InputStream is3 = SideScheduler.class.getResourceAsStream("Poppins-Regular.ttf");
        try {
            assert is3 != null;
            fontText = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(40f);
        } catch(IOException ex){
            System.out.println(ex);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        InputStream is2 = SideScheduler.class.getResourceAsStream("Poppins-SemiBold.ttf");
        try {
            assert is2 != null;
            fontTextSemiBold = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(40f);
        } catch(IOException ex){
            System.out.println(ex);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        InputStream is1 = SideScheduler.class.getResourceAsStream("Poppins-Bold.ttf");
        try {
            assert is1 != null;
            fontTextBold = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(45f);
        } catch(IOException ex){
            System.out.println(ex);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
