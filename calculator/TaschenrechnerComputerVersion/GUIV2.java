import java.awt.Toolkit;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.AWTException;
import java.awt.Dimension;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Robot;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class GUIV2 extends JFrame
{
    TaschenrechnerV2 calc;
    exceptions exceptionHandle;
    char[] operators;
    String[][] sorted;
    double[] memory;
    Robot robot;
    JButton[] memoryArr;
    int activeMemoryButton;
    private JButton AC;
    private JLabel Ergebnis2;
    private JButton Ergebnis2Button;
    private JLabel Ergebnis3;
    private JButton Ergebnis3Button;
    private JLabel Ergebnis4;
    private String text0;
    private JButton Ergebnis4Button;
    private JLabel Ergebnis5;
    private JButton Ergebnis5Button;
    private JButton Rechnen;
    private char[] outputEgg;
    private JLabel exception;
    private JLabel exceptionOutput;
    private JLabel input;
    private int EggIndex;
    private JTextField inputText;
    private JButton jButton5;
    private JLabel output;
    private JLabel outputException;
    private String text;
    private JTextField outputText;
    private JLabel request;
    
    public GUIV2() {
        this.robot = null;
        this.activeMemoryButton = 0;
        this.text0 = "philipp";
        this.outputEgg = new char[7];
        this.EggIndex = 0;
        this.text = "Philipp hat 15P";
        this.initComponents();
        this.Seticon();
        this.initRobot();
        this.initOther();
        this.inputText.requestFocus();
    }
    
    public void initOther() {
        this.memory = new double[] { 0.0, 0.0, 0.0, 0.0 };
        this.sorted = new String[1][2];
        this.sorted[0][0] = "";
        this.sorted[0][1] = "";
        this.operators = new char[] { '*', '/', '+', '-' };
        this.calc = new TaschenrechnerV2();
        this.exceptionHandle = new exceptions();
    }
    
    private void initComponents() {
        this.jButton5 = new JButton();
        this.inputText = new JTextField();
        this.outputText = new JTextField();
        this.output = new JLabel();
        this.input = new JLabel();
        this.Ergebnis2Button = new JButton();
        this.Ergebnis3Button = new JButton();
        this.Ergebnis4Button = new JButton();
        this.Ergebnis5Button = new JButton();
        this.Rechnen = new JButton();
        this.Ergebnis2 = new JLabel();
        this.Ergebnis3 = new JLabel();
        this.Ergebnis4 = new JLabel();
        this.Ergebnis5 = new JLabel();
        this.request = new JLabel();
        this.exception = new JLabel();
        this.outputException = new JLabel();
        this.exceptionOutput = new JLabel();
        this.AC = new JButton();
        this.jButton5.setText("jButton5");
        this.setDefaultCloseOperation(3);
        this.setTitle("Calculator by Philipp Bleimund");
        this.inputText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                GUIV2.this.inputTextActionPerformed();
            }
        });
        this.outputText.setEditable(false);
        this.output.setText("output");
        this.input.setText("input");
        this.outputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                GUIV2.this.outputEgg[GUIV2.this.EggIndex] = c;
                final GUIV2 this$0 = GUIV2.this;
                GUIV2.access$3(this$0, this$0.EggIndex + 1);
                String Egg = "";
                for (int i = 0; i < 7; ++i) {
                    Egg = String.valueOf(Egg) + GUIV2.this.outputEgg[i];
                }
                if (Egg.equals(GUIV2.this.text0)) {
                    GUIV2.this.outputText.setText(GUIV2.this.text);
                    GUIV2.access$7(GUIV2.this, new char[7]);
                    GUIV2.access$3(GUIV2.this, 0);
                }
            }
        });
        this.inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (c == 'O') {
                    GUIV2.this.activeMemoryButtonUp();
                    GUIV2.this.memoryArr[GUIV2.this.activeMemoryButton].requestFocus();
                }
                else if (c == 'U') {
                    GUIV2.this.activeMemoryButtonDown();
                    GUIV2.this.memoryArr[GUIV2.this.activeMemoryButton].requestFocus();
                }
                if ((c != 'E' && (c < '*' || c > '9')) || c == '\b' || c == ',') {
                    e.consume();
                }
                if (c >= '0' && c <= '9') {
                    GUIV2.this.request.setText("please insert a operator");
                }
                else if (c >= '*' && c <= '/' && c != '.') {
                    GUIV2.this.request.setText("please insert a number");
                }
            }
        });
        this.memoryArr = new JButton[] { this.Ergebnis2Button, this.Ergebnis3Button, this.Ergebnis4Button, this.Ergebnis5Button };
        for (int i = 0; i < this.memoryArr.length; ++i) {
            final int index = i;
            this.memoryArr[i].setText("0.0");
            this.memoryArr[i].addKeyListener(new KeyAdapter(index) {
                int number = number;
                
                @Override
                public void keyTyped(final KeyEvent e) {
                    final char c = e.getKeyChar();
                    if (c == '\n') {
                        GUIV2.this.activeMemoryButton = 0;
                        GUIV2.this.MemoryButtonPressed(this.number);
                    }
                    if (c == 'O') {
                        GUIV2.this.activeMemoryButtonUp();
                        GUIV2.this.memoryArr[GUIV2.this.activeMemoryButton].requestFocus();
                    }
                    else if (c == 'U') {
                        GUIV2.this.activeMemoryButtonDown();
                        GUIV2.this.memoryArr[GUIV2.this.activeMemoryButton].requestFocus();
                    }
                }
            });
            this.memoryArr[i].addActionListener(new ActionListener(index) {
                int number = number;
                
                @Override
                public void actionPerformed(final ActionEvent evt) {
                    GUIV2.this.activeMemoryButton = 0;
                    GUIV2.this.MemoryButtonPressed(this.number);
                }
            });
        }
        this.Rechnen.setText("Rechnen");
        this.Rechnen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GUIV2.this.RechnenActionPerformed();
            }
        });
        this.Ergebnis2.setText("Ergebnis2");
        this.Ergebnis3.setText("Ergebnis3");
        this.Ergebnis4.setText("Ergebnis4");
        this.Ergebnis5.setText("Ergebnis5");
        this.request.setForeground(new Color(0, 102, 51));
        this.request.setText("please insert a number");
        this.exception.setForeground(new Color(255, 0, 0));
        this.exception.setText("exception:");
        this.exceptionOutput.setText("no exception");
        this.AC.setText("AC");
        this.AC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                GUIV2.this.ACActionPerformed();
            }
        });
        this.AC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (c == '\r') {
                    GUIV2.this.ACActionPerformed();
                }
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.inputText).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.request).addComponent(this.AC)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.output).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.Ergebnis2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.Ergebnis2Button)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.Ergebnis3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.Ergebnis3Button)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.Ergebnis4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.Ergebnis4Button)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.Ergebnis5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.Ergebnis5Button)).addComponent(this.outputText, GroupLayout.Alignment.TRAILING, -2, 136, -2)))).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.Rechnen).addGroup(layout.createSequentialGroup().addComponent(this.input).addGap(91, 91, 91).addComponent(this.exception).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.exceptionOutput).addGap(131, 131, 131).addComponent(this.outputException))).addGap(0, 0, 32767))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(38, 38, 38).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.Ergebnis2Button).addComponent(this.Ergebnis2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.Ergebnis3Button).addComponent(this.Ergebnis3)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.Ergebnis4Button).addComponent(this.Ergebnis4)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.Ergebnis5Button).addComponent(this.Ergebnis5).addComponent(this.AC)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.input).addComponent(this.exception).addComponent(this.outputException).addComponent(this.exceptionOutput)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.inputText, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.output).addComponent(this.request)).addGap(5, 5, 5).addComponent(this.outputText, -2, -1, -2).addGap(1, 1, 1).addComponent(this.Rechnen).addGap(46, 46, 46)));
        this.setSize(new Dimension(401, 353));
        this.setLocationRelativeTo(null);
    }
    
    private void inputTextActionPerformed() {
        this.run();
        this.clean();
    }
    
    private void RechnenActionPerformed() {
        this.run();
        this.clean();
    }
    
    private void MemoryButtonPressed(final int number) {
        this.inputText.requestFocus();
        this.memoryInInput(this.memory[number]);
    }
    
    private void ACActionPerformed() {
        this.clean();
        this.inputText.setText("");
    }
    
    private void analyse() {
        boolean isMinus = true;
        final String input = this.inputText.getText();
        final char[] rawInput = input.toCharArray();
        for (int i = 0; i < rawInput.length; ++i) {
            if (isMinus) {
                if (rawInput[i] == '-') {
                    this.sorted[this.sorted.length - 1][1] = String.valueOf('-');
                }
                else {
                    isMinus = false;
                }
            }
            if (!isMinus) {
                boolean tmp = true;
                for (int j = 0; j < this.operators.length; ++j) {
                    if (rawInput[i] == this.operators[j]) {
                        this.expandSorted();
                        this.sorted[this.sorted.length - 1][0] = String.valueOf(this.operators[j]);
                        isMinus = true;
                        tmp = false;
                    }
                }
                if (tmp) {
                    this.sorted[this.sorted.length - 1][1] = String.valueOf(this.sorted[this.sorted.length - 1][1]) + rawInput[i];
                }
            }
        }
    }
    
    private void expandSorted() {
        final String[][] newArray = new String[this.sorted.length + 1][this.sorted[0].length];
        System.arraycopy(this.sorted, 0, newArray, 0, this.sorted.length);
        newArray[newArray.length - 1][0] = "";
        newArray[newArray.length - 1][1] = "";
        this.sorted = newArray;
    }
    
    public void arrangeMemory(final double newNumber) {
        this.memory[3] = this.memory[2];
        this.memory[2] = this.memory[1];
        this.memory[1] = this.memory[0];
        this.memory[0] = newNumber;
        this.Ergebnis2Button.setText(String.valueOf(this.memory[0]));
        this.Ergebnis3Button.setText(String.valueOf(this.memory[1]));
        this.Ergebnis4Button.setText(String.valueOf(this.memory[2]));
        this.Ergebnis5Button.setText(String.valueOf(this.memory[3]));
    }
    
    public void initRobot() {
        try {
            this.robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public void memoryInInput(final double input) {
        String inputStr = String.valueOf(input);
        inputStr = inputStr.toUpperCase();
        final char[] chars = inputStr.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == 'E') {
                this.robot.keyPress(16);
                this.robot.keyPress(chars[i]);
                this.robot.keyRelease(chars[i]);
                this.robot.keyRelease(16);
            }
            this.robot.keyPress(chars[i]);
            this.robot.keyRelease(chars[i]);
        }
    }
    
    public boolean searchException() {
        final String input = this.inputText.getText();
        if (input == "") {
            this.exception.setText("exeption in ln '0':");
            this.exceptionOutput.setText(this.exceptionHandle.nothing());
            return false;
        }
        final char[] rawInput = input.toCharArray();
        for (int i = 0; i < rawInput.length - 1; ++i) {
            if (rawInput[i] == '.') {
                if (rawInput[i + 1] == '.') {
                    this.exception.setText("exception in ln '" + i + 1 + "':");
                    this.exceptionOutput.setText(this.exceptionHandle.doubleDots());
                    return false;
                }
                for (int j = 0; j < this.operators.length; ++j) {
                    if (rawInput[i + 1] == this.operators[j]) {
                        this.exception.setText("exception in ln '" + i + 1 + "':");
                        this.exceptionOutput.setText(this.exceptionHandle.operatorBehindDot());
                        return false;
                    }
                }
            }
            for (int k = 0; k < this.operators.length; ++k) {
                if (rawInput[i] == this.operators[k]) {
                    if (rawInput[i + 1] == '.') {
                        this.exception.setText("exception in ln '" + i + 1 + "':");
                        this.exceptionOutput.setText(this.exceptionHandle.dotBehindOperator());
                        return false;
                    }
                    for (int l = 0; l < this.operators.length - 1; ++l) {
                        if (rawInput[i + 1] == this.operators[l]) {
                            this.exception.setText("exception in ln '" + i + 1 + "':");
                            this.exceptionOutput.setText(this.exceptionHandle.twoOperators());
                            return false;
                        }
                    }
                    if (this.operators[k] == '-' && rawInput[i + 1] == '-') {
                        this.exception.setText("exception in ln '" + i + 1 + "':");
                        this.exceptionOutput.setText(this.exceptionHandle.twoMinus());
                        return false;
                    }
                }
            }
        }
        for (int m = 0; m < this.operators.length; ++m) {
            if (rawInput[rawInput.length - 1] == this.operators[m]) {
                this.exception.setText("exception in ln '" + rawInput.length + "':");
                this.exceptionOutput.setText(this.exceptionHandle.operatorLast());
                return false;
            }
        }
        if (rawInput[rawInput.length - 1] == '.') {
            this.exception.setText("exception in ln '" + rawInput.length + "':");
            this.exceptionOutput.setText(this.exceptionHandle.dotLast());
            return false;
        }
        for (int n = 0; n < this.sorted.length; ++n) {
            int dotCounter = 0;
            final String number = this.sorted[n][1];
            final char[] numberArr = number.toCharArray();
            for (int o = 0; o < numberArr.length; ++o) {
                if (numberArr[o] == '.') {
                    ++dotCounter;
                }
                if (dotCounter > 1) {
                    this.exception.setText("exception in ln '" + n + "':");
                    this.exceptionOutput.setText(this.exceptionHandle.twoDotsInNumber());
                    return false;
                }
            }
        }
        return true;
    }
    
    public void run() {
        this.analyse();
        if (this.searchException()) {
            final double result = this.round(this.calc.work(this.sorted));
            this.outputText.setText(String.valueOf(result));
            this.arrangeMemory(result);
        }
    }
    
    public void activeMemoryButtonUp() {
        if (this.activeMemoryButton == 0) {
            this.activeMemoryButton = 3;
        }
        else {
            --this.activeMemoryButton;
        }
    }
    
    public void activeMemoryButtonDown() {
        if (this.activeMemoryButton == 3) {
            this.activeMemoryButton = 0;
        }
        else {
            ++this.activeMemoryButton;
        }
    }
    
    public void clean() {
        this.sorted = new String[1][2];
        this.sorted[0][0] = "";
        this.sorted[0][1] = "";
        this.inputText.requestFocus();
    }
    
    public double round(double input) {
        input *= 100000.0;
        input = (double)Math.round(input);
        return input / 100000.0;
    }
    
    public static void main(final String[] args) {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels;
            for (int length = (installedLookAndFeels = UIManager.getInstalledLookAndFeels()).length, i = 0; i < length; ++i) {
                final UIManager.LookAndFeelInfo info = installedLookAndFeels[i];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIV2.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(GUIV2.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(GUIV2.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(GUIV2.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIV2().setVisible(true);
            }
        });
    }
    
    private void Seticon() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("logo.png")));
    }
    
    static /* synthetic */ void access$3(final GUIV2 guiv2, final int eggIndex) {
        guiv2.EggIndex = eggIndex;
    }
    
    static /* synthetic */ void access$7(final GUIV2 guiv2, final char[] outputEgg) {
        guiv2.outputEgg = outputEgg;
    }
}
