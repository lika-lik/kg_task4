package kg2019examples_task4threedimensions.controller;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.screen.ScreenConverter;
import kg2019examples_task4threedimensions.screen.ScreenPoint;
import kg2019examples_task4threedimensions.third.Scene;
import models.Parallelepiped;

import java.awt.event.*;
import java.util.*;

public class ModelDrawerController implements MouseListener, MouseMotionListener, MouseWheelListener, MouseController{

    public static interface RepaintListener {
        /**
         * Метод, вызываемый при изменении
         */
        void shouldRepaint();
    }


    /* Далее описывается приватная коллекция, в данном случае - Set,
     * где будет хрнаиться список всех слушателей, подписанных на данное событие.
     */
    private Set<CameraController.RepaintListener> listeners = new HashSet<>();

    /**
     * Метод добавления слушателя
     * @param listener слушатель
     */
    public void addRepaintListener(CameraController.RepaintListener listener) {
        listeners.add(listener);
    }

    /**
     * Метод удаления слушателя
     * @param listener слушатель
     */
    public void removeRepaintListener(CameraController.RepaintListener listener) {
        listeners.remove(listener);
    }

    /**
     * Вспомогательный метод, который оповещает всех слушателей о произошедшем событии.
     */
    protected void onRepaint() {
        for (CameraController.RepaintListener cl : listeners)
            cl.shouldRepaint();
    }



    private int x1, x2, y1, y2;
    private float z1 = -0.5f, z2 = 0.5f;
    private Vector3 vector1, vector2;
    private Parallelepiped paral;
    private ScreenConverter sc;
    private Scene scene;

    public ModelDrawerController(ScreenConverter sc, Scene scene) {
        this.sc = sc;
        this.scene = scene;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        vector1 = sc.s2r(new ScreenPoint(x1, y1), z1);
        x2=x1;
        y2=y1;
        vector2 = sc.s2r(new ScreenPoint(x2, y2), z2);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Parallelepiped getParal(){
        return paral;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        vector1 = sc.s2r(new ScreenPoint(x1, y1), z1);
        paral = new Parallelepiped(vector1, vector2);
        scene.getModelsList().add(paral);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }

}
