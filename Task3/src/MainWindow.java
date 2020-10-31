import functions.FunctionA;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private DrawPanel dp;

    public MainWindow() throws HeadlessException {
        dp = new DrawPanel();
        this.add(dp);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu menuDraw = new JMenu("Рисовать графики функций");
        JMenu menuColor = new JMenu("Изменить цвет");
        JMenuItem functionA = new JMenuItem("1. y = A*x^3 + B*x^2 + C*x + D");
//        JMenuItem functionB = new JMenuItem("2. y = A*sin(W*x + F) + C");
//        JMenuItem functionC = new JMenuItem("3. y = A1*sin(W1*x + F1)*(A2*cos(W2*x + F2)+C2) + C1");
//        JMenuItem functionD = new JMenuItem("4. y = A*sin(W*x + F)*(e^(-x/T))+C");
//        JMenuItem functionE = new JMenuItem("5. y = A*1/(1+e^(-x)) + C");
//        JMenuItem functionF = new JMenuItem("6. y = A/(B*x+D) + C");
//        JMenuItem functionG = new JMenuItem("7. x = A*y^3 + B*y^2 + C*y + D");
        JMenuItem customColor = new JMenuItem("Поставить собственный");
        menuDraw.add(functionA);
        jMenuBar.add(menuDraw);
        jMenuBar.add(menuColor);
        setJMenuBar(jMenuBar);
        functionA.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(4,
                    "Введите A, B, C и D для y = A*x^3 + B*x^2 + C*x + D");
            if (functionArgs != null) {
                dp.setFunction(new FunctionA(functionArgs));
                dp.repaint();

            }
        });

    }
    private List<Double> getArgsList(int numArgs, String text) {
        List<Double> functionArgs = new ArrayList<>();
        String inputDialog = JOptionPane.showInputDialog(text);
        if (inputDialog != null) {
            try {
                String[] functionArgsString = inputDialog.split("[ ,]");
                for (String arg : functionArgsString) {
                    functionArgs.add(Double.parseDouble(arg));
                }
                if (functionArgs.size() != numArgs) {
                    JOptionPane.showMessageDialog(null, "Недостаточно параметров ");
                    return null;
                } else {
                    return functionArgs;
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Параметры должны быть вещественными числами");
            }
        }
        return null;
    }
}
