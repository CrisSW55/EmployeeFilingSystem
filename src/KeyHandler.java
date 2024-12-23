import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
       // displayLabel.setText(textField.getText() + keyChar);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Keys are being pressed!");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Keys are being released!");
    }
}
