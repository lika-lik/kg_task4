/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg2019examples_task4threedimensions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

import kg2019examples_task4threedimensions.controller.CameraController;
import kg2019examples_task4threedimensions.controller.ModelDrawerController;
import kg2019examples_task4threedimensions.controller.MouseController;
import kg2019examples_task4threedimensions.draw.IDrawer;
import kg2019examples_task4threedimensions.draw.SimpleEdgeDrawer;
import kg2019examples_task4threedimensions.screen.ScreenConverter;
import kg2019examples_task4threedimensions.third.Camera;
import kg2019examples_task4threedimensions.third.Scene;

/**
 *
 * @author Alexey
 */
public class DrawPanel extends JPanel
        implements CameraController.RepaintListener {

    private Scene scene;
    private ScreenConverter sc;
    private Camera cam;
    private CameraController camController;
    private ModelDrawerController modController;

    private MouseController mouseController;
    private boolean controllerCamera = true;

    public void setControllerCamera(boolean controllerCamera) {
        this.controllerCamera = controllerCamera;
        if (controllerCamera){
            mouseController = camController;
        }else{
            mouseController = modController;
        }
        mouseController.addRepaintListener(this);
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
        addMouseWheelListener(mouseController);
    }

    public DrawPanel() {
        super();
        sc = new ScreenConverter(-1, 1, 2, 2, 1, 1);
        cam = new Camera();
        camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        modController = new ModelDrawerController(sc, scene);
        scene.showAxes();
        setControllerCamera(controllerCamera);
    }
    
    @Override
    public void paint(Graphics g) {
            sc.setScreenSize(getWidth(), getHeight());
            BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = (Graphics2D) bi.getGraphics();
            IDrawer dr = new SimpleEdgeDrawer(sc, graphics);
            scene.drawScene(dr, cam);
            g.drawImage(bi, 0, 0, null);
            graphics.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }

    public void clearModels(){
        scene.getModelsList().clear();
        scene.getModelsRed().clear();
    }

    public void roundParl(int roundPer){
        scene.getModelsRed().add(modController.getParal().rounded(roundPer));
        setControllerCamera(true);
        repaint();
    }

}
