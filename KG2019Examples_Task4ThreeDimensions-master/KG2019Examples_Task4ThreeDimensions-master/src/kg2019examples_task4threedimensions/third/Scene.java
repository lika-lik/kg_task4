/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg2019examples_task4threedimensions.third;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kg2019examples_task4threedimensions.draw.IDrawer;
import kg2019examples_task4threedimensions.math.Vector3;
import models.Line3D;

/**
 * Описывает трёхмерную со всеми объектами на ней
 * @author Alexey
 */
public class Scene {
    private List<IModel> models = new ArrayList<>();
    private List<IModel> modelsRed = new ArrayList<>();

    ICamera cam;
    IDrawer drawer;
    Color color = Color.black;

    public List<IModel> getModelsList() {
        return models;
    }

    public List<IModel> getModelsRed(){
        return modelsRed;
    }
    
    private int backgroundColor;


    /**
     * Создаём сцену с заданным фоном
     * @param backgroundColorRGB цвет фона.
     */
    public Scene(int backgroundColorRGB) {
        this.backgroundColor = backgroundColorRGB;
        this.showAxes = false;
        this.cam = cam;
    }
    
    private boolean showAxes;

    public boolean isShowAxes() {
        return showAxes;
    }

    public void setShowAxes(boolean showAxis) {
        this.showAxes = showAxis;
    }
    
    public void showAxes() {
        this.showAxes = true;
    }
    
    public void hideAxes() {
        this.showAxes = false;
    }
    
    private static final List<Line3D> axes = Arrays.asList(
            new Line3D(new Vector3(0, 0, 0), new Vector3(1, 0, 0)),
            new Line3D(new Vector3(0, 0, 0), new Vector3(0, 1, 0)),
            new Line3D(new Vector3(0, 0, 0), new Vector3(0, 0, 1))
    );
    
    /**
     * Рисуем сцену со всеми моделями
     * @param drawer то, с помощью чего будем рисовать
     * @param cam камера для преобразования координат
     */
    public void drawScene(IDrawer drawer, ICamera cam) {
        this.drawer = drawer;
        this.cam = cam;
        LinkedList<Collection<? extends IModel>> allModels = new LinkedList<>();

        allModels.add(models);
        /*Если требуется, то добавляем оси координат*/
        if (isShowAxes())
            allModels.add(axes);
        drawer.clear(backgroundColor);
        for (Collection<? extends IModel> mc : allModels)
            for (IModel m : mc) {
                drawModel(m, Color.gray);
            }
        for (IModel m: modelsRed){
            drawModel(m, Color.red);
        }
        models.clear();
        modelsRed.clear();
    }

    public void drawModel(IModel model, Color color){
        List<PolyLine3D> lines = new ArrayList<>();
        for (PolyLine3D pl : model.getLines()) {
            /*Все точки конвертируем с помощью камеры*/
            List<Vector3> points = new LinkedList<>();
            for (Vector3 v : pl.getPoints()) {
                points.add(cam.w2s(v));
            }
            lines.add(new PolyLine3D(points, pl.isClosed()));
            drawer.draw(lines, color);
        }
    }
}
