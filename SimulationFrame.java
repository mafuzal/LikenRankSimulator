import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SimulationFrame extends JFrame implements SimulationListener, ActionListener {

    private JTextField docEntry;
    private JTextField userEntry;
    private JPanel panel;
    private JTextArea textArea;
    private JScrollPane scroller;
    private JButton runButton;

    public SimulationFrame() {
        super("Simulation");
        //GridLayout was easy and better than GridBagLayout, FlowLayout, BoxLayout 
        //So I decided to use GridLayout
        //I was able to successfully readjust the contents of frame while readjusting frame 
        //This is not working when I used other layouts
        panel = new JPanel(new GridLayout(3, 3, 10, 3));
        panel.setBorder(BorderFactory.createTitledBorder(null, "Public View", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        panel.add(new JLabel("Users "));
        userEntry = new JTextField(20);
        panel.add(userEntry);
        userEntry.addActionListener(this);
        panel.add(new JLabel("Documents "));
        docEntry = new JTextField(20);
        panel.add(docEntry);
        docEntry.addActionListener(this);
        //textArea is created here 
        textArea = new JTextArea("");
        textArea.setEditable(false);
        //ScrollPane is created here
        scroller = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //Button is created here 
        runButton = new JButton("RUN");
        runButton.addActionListener(this);
        // The Content Pane will add Panel,Scroll and Button
        //All positioning was done to give the ContentPane a nice outlook
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout(5, 5));
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(scroller, BorderLayout.CENTER);
        contentPane.add(runButton, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        //the following PrintStream codes below are used to call inner Class ConsoleRedirectJTextArea
        //this will allow the output() stuff to be displayed on JTextArea
        PrintStream printStream = new PrintStream(new ConsoleRedirectJTextArea(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SimulationFrame().setVisible(true);
                }
            });
    }
    //ActionListener checks for null values 
    //as soon as values are there run willbe executed on enter
    //or by pressing runButton
    @Override
    public void actionPerformed(ActionEvent e) {

        if (docEntry.getText() != null && userEntry.getText() != null) {
            Simulation s = new Simulation(Integer.parseInt(userEntry.getText()), Integer.parseInt(docEntry.getText()),"docpop");
            SimulationHandler sH = new SimulationHandler();
            s.addSimulationListeners(sH);
            s.run();

        }

    }

    @Override
    public void output(SimulationEvent e) {
        // TODO Auto-generated method stub

    }
    //This innerclass was used to redirect the text on output screen to JTextArea in the frame
    //It inherits output stream class and therefore instead of outstreaming to console
    //it will output to JTextArea
    public class ConsoleRedirectJTextArea extends OutputStream {
        private JTextArea textArea;

        public ConsoleRedirectJTextArea(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}