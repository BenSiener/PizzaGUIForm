public class PizzaGUIRunner {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PizzaGUIFrame frame = new PizzaGUIFrame();
                frame.setVisible(true);
            }
        });
    }
}
