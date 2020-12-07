package models;

import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.List;

public class ModelByPolyLines implements IModel {
    List<PolyLine3D> list;
    public ModelByPolyLines(List<PolyLine3D> list) {
        this.list = list;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return list;
    }
}
