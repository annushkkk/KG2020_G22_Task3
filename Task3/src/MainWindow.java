import functions.*;

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
        JMenuItem functionB = new JMenuItem("2. y = A*sin(W*x + F) + C");
        JMenuItem functionC = new JMenuItem("3. y = A1*sin(W1*x + F1)*(A2*cos(W2*x + F2)+C2) + C1");
        JMenuItem functionD = new JMenuItem("4. y = A*sin(W*x + F)*(e^(-x/T))+C");
        JMenuItem functionE = new JMenuItem("5. y = A*1/(1+e^(-x)) + C");
        JMenuItem functionF = new JMenuItem("6. y = A/(B*x+D) + C");
        JMenuItem functionG = new JMenuItem("7. x = A*y^3 + B*y^2 + C*y + D");
        JMenuItem customColor = new JMenuItem("Поставить собственный");
        menuDraw.add(functionA);
        menuDraw.add(functionB);
        menuDraw.add(functionC);
        menuDraw.add(functionD);
        menuDraw.add(functionE);
        menuDraw.add(functionF);
        menuDraw.add(functionG);
        jMenuBar.add(menuDraw);
        jMenuBar.add(menuColor);
        menuColor.add(customColor);
        setJMenuBar(jMenuBar);

        customColor.addActionListener(actionEvent -> {
            Color color = getColorFromUser();
            if (color != null) {
                dp.setColor(color);
                dp.repaint();
            }
        });
        functionA.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(4,
                    "Введите A, B, C и D для y = A*x^3 + B*x^2 + C*x + D");
            if (functionArgs != null) {
                dp.setFunction(new FunctionA());
                dp.setFunctionArgs(functionArgs);
                dp.repaint();

            }
        });
        functionB.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(4,
                    "Введите A, W, F и C для y = A*sin(W*x + F) + C");
            if (functionArgs != null) {
                dp.setFunction(new FunctionB());
                dp.setFunctionArgs(functionArgs);
                dp.repaint();
            }
        });

        functionC.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(8,
                    "Введите A1, W1, F1, A2, W2, F2, C2 и C1 для y = A1*sin(W1*x + F1)*(A2*cos(W2*x + F2)+C2) + C1");
            if (functionArgs != null) {
                dp.setFunction(new FunctionC());
                dp.setFunctionArgs(functionArgs);
                dp.repaint();
            }
        });

        functionD.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(5,
                    "Введите A, W, F, T и C для y = A*sin(W*x + F)*(e^(-x/T)) + C");
            if (functionArgs != null) {
                dp.setFunction(new FunctionD());
                dp.setFunctionArgs(functionArgs);
                dp.repaint();
            }
        });

        functionE.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(2,
                    "Введите A и C для y = A*1/(1+e^(-x)) + C");
            if (functionArgs != null) {
                dp.setFunction(new FunctionE());
                dp.setFunctionArgs(functionArgs);
                dp.repaint();
            }
        });

        functionF.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(4,
                    "Введите A, B, D и C для y = A/(B*x+D) + C");
            if (functionArgs != null) {
                dp.setFunction(new FunctionF());
                dp.setFunctionArgs(functionArgs);
                dp.repaint();
            }
        });

        functionG.addActionListener(actionEvent -> {
            List<Double> functionArgs = getArgsList(4,
                    "Введите A, B, С и D для x = A*y^3 + B*y^2 + C*y + D");
            if (functionArgs != null) {
                dp.setFunction(new FunctionG());
                dp.setFunctionArgs(functionArgs);
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
    private Color getColorFromUser() {
        String inputDialog = JOptionPane.showInputDialog("Введите цвет в формате <255 255 255>");
        if (inputDialog != null) {
            try {
                int r = Integer.parseInt(inputDialog.split(" ")[0]);
                int g = Integer.parseInt(inputDialog.split(" ")[1]);
                int b = Integer.parseInt(inputDialog.split(" ")[2]);

                if (r < 0 || g < 0 || b < 0) {
                    JOptionPane.showMessageDialog(null, "Атрибуты цвета должны быть неотрицательными числами");
                }
                return new Color(r, g, b);

            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Атрибуты цвета должны быть целыми числами");
            }
        }

        return null;
    }
}
