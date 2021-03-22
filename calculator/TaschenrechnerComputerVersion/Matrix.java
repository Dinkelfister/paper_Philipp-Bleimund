import com.pi4j.io.gpio.GpioController;
import java.awt.AWTException;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.GpioFactory;
import java.awt.Robot;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;

// 
// Decompiled by Procyon v0.5.36
// 

public class Matrix
{
    GpioPinDigitalInput[] Row;
    GpioPinDigitalOutput[] Col;
    GpioPinDigitalOutput[] LED;
    boolean[][] antiDouble;
    int[] display;
    boolean[][] matrix;
    char[][] robotMatrix;
    Robot robot;
    
    public Matrix() {
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput R0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_09, "Row0", PinPullResistance.PULL_UP);
        final GpioPinDigitalInput R2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, "Row1", PinPullResistance.PULL_UP);
        final GpioPinDigitalInput R3 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, "Row2", PinPullResistance.PULL_UP);
        final GpioPinDigitalInput R4 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, "Row3", PinPullResistance.PULL_UP);
        final GpioPinDigitalOutput C0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Colum0", PinState.HIGH);
        final GpioPinDigitalOutput C2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "Colum1", PinState.HIGH);
        final GpioPinDigitalOutput C3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "Colum2", PinState.HIGH);
        final GpioPinDigitalOutput C4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "Colum3", PinState.HIGH);
        final GpioPinDigitalOutput C5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "Colum4", PinState.HIGH);
        this.Row = new GpioPinDigitalInput[] { R0, R2, R3, R4 };
        this.Col = new GpioPinDigitalOutput[] { C0, C2, C3, C4, C5 };
        final GpioPinDigitalOutput A = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "LEDA", PinState.HIGH);
        final GpioPinDigitalOutput B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "LEDB", PinState.HIGH);
        final GpioPinDigitalOutput C6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "LEDC", PinState.HIGH);
        final GpioPinDigitalOutput D = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "LEDC", PinState.HIGH);
        final GpioPinDigitalOutput E = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "LEDE", PinState.HIGH);
        final GpioPinDigitalOutput F = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "LEDF", PinState.HIGH);
        final GpioPinDigitalOutput G = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "LEDG", PinState.HIGH);
        final GpioPinDigitalOutput DP = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "LEDDP", PinState.HIGH);
        final GpioPinDigitalOutput CC1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Ground", PinState.HIGH);
        final GpioPinDigitalOutput CC2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "Ground", PinState.HIGH);
        final GpioPinDigitalOutput CC3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Ground", PinState.HIGH);
        final GpioPinDigitalOutput CC4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "Ground", PinState.HIGH);
        this.LED = new GpioPinDigitalOutput[] { A, B, C6, D, E, F, G, DP, CC1, CC2, CC3, CC4 };
        this.robotMatrix = new char[][] { { 'O', '7', '8', '9', '/' }, { 'L', '4', '5', '6', '*' }, { 'D', '1', '2', '3', '+' }, { 'U', 'E', '0', '.', '-' } };
        this.robot = null;
        try {
            this.robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        this.antiDouble = new boolean[5][4];
        for (int i = 0; i < this.antiDouble.length; ++i) {
            for (int j = 0; j < this.antiDouble[0].length; ++j) {
                this.antiDouble[i][j] = false;
            }
        }
        this.display = new int[4];
        for (int i = 0; i < this.display.length; ++i) {
            this.display[i] = 11;
        }
        final boolean[][] matrix = new boolean[12][];
        matrix[0] = new boolean[] { true, true, true, true, true, true, false, false };
        final int n = 1;
        final boolean[] array = new boolean[8];
        array[2] = (array[1] = true);
        matrix[n] = array;
        matrix[2] = new boolean[] { true, true, false, true, true, false, true, false };
        matrix[3] = new boolean[] { true, true, true, true, false, false, true, false };
        matrix[4] = new boolean[] { false, true, true, false, false, true, true, false };
        matrix[5] = new boolean[] { true, false, true, true, false, true, true, false };
        matrix[6] = new boolean[] { true, false, true, true, true, true, true, false };
        final int n2 = 7;
        final boolean[] array2 = new boolean[8];
        array2[0] = true;
        array2[2] = (array2[1] = true);
        matrix[n2] = array2;
        matrix[8] = new boolean[] { true, true, true, true, true, true, true, false };
        matrix[9] = new boolean[] { true, true, true, true, false, true, true, false };
        matrix[10] = new boolean[] { false, false, false, false, false, false, false, true };
        matrix[11] = new boolean[8];
        this.matrix = matrix;
        System.out.println("bin da");
        this.shutdownOptions();
        this.durchgehen();
    }
    
    public void durchgehen() {
        for (int i = 0; i < this.Col.length; ++i) {
            this.Col[i].low();
            for (int j = 0; j < this.Row.length; ++j) {
                if (this.Row[j].isLow() && !this.antiDouble[i][j]) {
                    System.out.println("Row " + j + " Col " + i);
                    this.Robot(j, i);
                    this.assign(i, j);
                    this.antiDouble[i][j] = true;
                }
                else if (this.Row[j].isHigh()) {
                    this.antiDouble[i][j] = false;
                }
                this.Display();
            }
            this.Col[i].high();
        }
    }
    
    public void Display() {
        for (int i = 0; i < 4; ++i) {
            this.matrix(this.display[i]);
            this.LED[i + 8].high();
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {}
            this.LED[i + 8].low();
        }
    }
    
    public void matrix(final int in) {
        for (int i = 0; i < 8; ++i) {
            this.LED[i].setState(!this.matrix[in][i]);
        }
    }
    
    public void assign(final int Col, final int Row) {
        final char tmp = this.robotMatrix[Row][Col];
        if (tmp >= '0' && tmp <= '9') {
            this.displayAssign(tmp - '0');
            return;
        }
        if (tmp >= '*' && tmp <= '/' && tmp != '.') {
            for (int i = 0; i < 4; ++i) {
                this.displayAssign(11);
            }
            return;
        }
        if (tmp == '.') {
            this.displayAssign(10);
        }
    }
    
    public void displayAssign(final int in) {
        this.display[3] = this.display[2];
        this.display[2] = this.display[1];
        this.display[1] = this.display[0];
        this.display[0] = in;
    }
    
    public void Robot(final int Row, final int Col) {
        final char tmp = this.robotMatrix[Row][Col];
        switch (tmp) {
            case 'R': {
                this.robot.keyPress(9);
                this.robot.keyRelease(9);
                break;
            }
            case 'L': {
                this.robot.keyPress(16);
                this.robot.keyPress(9);
                this.robot.keyRelease(9);
                this.robot.keyRelease(16);
                break;
            }
            case '+': {
                this.robot.keyPress(16);
                this.robot.keyPress(521);
                this.robot.keyRelease(521);
                this.robot.keyRelease(16);
                break;
            }
            case '*': {
                this.robot.keyPress(16);
                this.robot.keyPress(56);
                this.robot.keyRelease(56);
                this.robot.keyRelease(16);
                break;
            }
            case 'E': {
                this.robot.keyPress(10);
                this.robot.keyRelease(10);
                break;
            }
            default: {
                this.robot.keyPress(tmp);
                this.robot.keyRelease(tmp);
                break;
            }
        }
    }
    
    public void shutdownOptions() {
        for (int i = 0; i < this.LED.length; ++i) {
            this.LED[i].setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        }
        for (int i = 0; i < this.Row.length; ++i) {
            this.Row[i].setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        }
        for (int i = 0; i < this.Col.length; ++i) {
            this.Col[i].setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        }
    }
    
    public void pass(final char input) {
        this.displayAssign(input - '0');
    }
}
