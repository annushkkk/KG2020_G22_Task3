

import functions.FunctionG;
import functions.IFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    public DrawPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    private ScreenConverter sc = new ScreenConverter(-5, 5, 10, 10, 800, 600);

    private Line axisX;

    private Line axisY;
    private ArrayList<Line> allLines = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        sc.sethS(getHeight());
        sc.setwS(getWidth());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.BLACK);


        for (int i = (int) sc.getxR(); i <= (int) (sc.getxR() + sc.getwR()); i++) {
            gr.drawString(Integer.toString(i), (int) (sc.getwS() * (i - sc.getxR()) / sc.getwR()), sc.r2s(new RealPoint(0, 0)).getY());
        }
        for (int i = (int) sc.getyR(); i >= (int) (sc.getyR() - sc.gethR()); i--) {
            gr.drawString(Integer.toString(i), sc.r2s(new RealPoint(0, 0)).getX(), (int) (sc.gethS() * (sc.getyR() - i) / sc.gethR()));
        }
        gr.dispose();
        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        LineDrawer ld = new DDALineDrawer(pd);
        /**/


        for (int i = (int) sc.getxR(); i <= (int) (sc.getxR() + sc.getwR()); i++) {
            ld.drawLine(new ScreenPoint((int) (sc.getwS() * (i - sc.getxR()) / sc.getwR()), 0),
                    new ScreenPoint((int) (sc.getwS() * (i - sc.getxR()) / sc.getwR()), sc.gethS()), Color.BLACK);

        }
        for (int i = (int) sc.getyR(); i >= (int) (sc.getyR() - sc.gethR()); i--) {
            ld.drawLine(new ScreenPoint(0, (int) (sc.gethS() * (sc.getyR() - i) / sc.gethR())),
                    new ScreenPoint(sc.getwS(), (int) (sc.gethS() * (sc.getyR() - i) / sc.gethR())), Color.BLACK);
        }
        drawAll(ld);
        /**/
        g.drawImage(bi, 0, 0, null);
    }

    private void drawAll(LineDrawer ld) {
        axisX = new Line(-getWidth(), 0, getWidth(), 0);
        axisY = new Line(0, -getHeight(), 0, getHeight());
        drawLine(ld, axisX);
        drawLine(ld, axisY);


        for (Line q : allLines)
            drawLine(ld, q);

        if (function != null)
            drawFunction(ld);
        if (newLine != null)
            drawLine(ld, newLine);

    }
    private Color color= new Color(100, 0, 0);
    public void setColor(Color color) {
        this.color = color;
    }



    public void setFunction(IFunction function) {
        this.function = function;
    }

    private List<Double> functionArgs;

    public void setFunctionArgs(List<Double> functionArgs) {
        this.functionArgs = functionArgs;
    }

    private IFunction function;

    private void drawFunction(LineDrawer ld) {
        ArrayList<RealPoint> points = new ArrayList<>();

        if (function instanceof FunctionG) {
            for (float i = (float) sc.getxR(); i <= (sc.getxR() + sc.getwR()); i += 0.1) {
                points.add(new RealPoint(function.compute(i, functionArgs), i));
            }
        } else {
            for (float i = (float) sc.getxR(); i <= (sc.getxR() + sc.getwR()); i += 0.1) {
                points.add(new RealPoint(i,function.compute(i, functionArgs)));
            }
        }
        int pointsSize = points.size();
        int x = 0;
        for (int i = 1; i < pointsSize; i++) {
            ld.drawLine(sc.r2s(new RealPoint(points.get(i).getX(), points.get(i).getY())), sc.r2s(new RealPoint(points.get(x).getX(), points.get(x).getY())), color);
            x = i;
        }


    }

    private void drawLine(LineDrawer ld, Line l) {
        ld.drawLine(sc.r2s(l.getP1()), sc.r2s(l.getP2()), Color.BLUE);
    }

    private ScreenPoint prevPoint = null;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    private Line newLine = null;

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            prevPoint = new ScreenPoint(e.getX(), e.getY());
        } else {
            if (e.getButton() == MouseEvent.BUTTON1) {

                newLine = new Line(sc.s2r(new ScreenPoint(e.getX(), e.getY())), sc.s2r(new ScreenPoint(e.getX(), e.getY())));

            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            prevPoint = null;
        } else if (mouseEvent.getButton() == MouseEvent.BUTTON1)
            allLines.add(newLine);
        newLine = null;
        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        ScreenPoint currentPoint = new ScreenPoint(mouseEvent.getX(), mouseEvent.getY());
        if (prevPoint != null) {
            ScreenPoint screenDelta = new ScreenPoint(currentPoint.getX() - prevPoint.getX(), currentPoint.getY() - prevPoint.getY());
            RealPoint delta = sc.s2r(screenDelta);
            double vectorX = delta.getX() - sc.getxR();
            double vectorY = delta.getY() - sc.getyR();
            sc.setxR(sc.getxR() - vectorX);
            sc.setyR(sc.getyR() - vectorY);
            prevPoint = currentPoint;

        }
        if (newLine != null) {
            newLine.setP2(sc.s2r(currentPoint));
        }
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double step = (clicks < 0) ? 0.99 : 1.01;
        for (int i = Math.abs(clicks); i > 0; i--) {
            scale *= step;
        }
        sc.setwR(scale * sc.getwR());
        sc.sethR(scale * sc.gethR());
        repaint();
    }
}
