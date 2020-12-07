package kg2019examples_task4threedimensions.controller;

import kg2019examples_task4threedimensions.DrawPanel;
import kg2019examples_task4threedimensions.screen.ScreenPoint;
import models.Parallelepiped;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public interface MouseController extends MouseListener, MouseMotionListener, MouseWheelListener {
    void mouseClicked(MouseEvent e);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseEntered(MouseEvent e);
    void mouseExited(MouseEvent e);
    void mouseDragged(MouseEvent e);
    void mouseMoved(MouseEvent e);

    void addRepaintListener(CameraController.RepaintListener listener);
}
