package kg2019examples_task4threedimensions.bezier;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.PolyLine3D;
import models.Line3D;

import java.util.ArrayList;
import java.util.List;

public class RoundedRectangle {
    private Line3D ox1, oy1, ox2, oy2;
    double roundedPer;

    public RoundedRectangle(Line3D ox1, Line3D oy1, Line3D ox2, Line3D oy2, double roundedPer) {
        this.roundedPer = roundedPer;
        this.ox1 = ox1;
        this.oy1 = oy1;
        this.ox2 = ox2;
        this.oy2 = oy2;
    }

    public PolyLine3D getPolyLine(){
        List<Vector3> res = new ArrayList<>();
        addVectorsToList(new TwoLineEdgeRounder(ox1, oy1, 1, roundedPer).getCurve(), res);
        addVectorsToList(new TwoLineEdgeRounder(oy1, ox2, 0, roundedPer).getCurve(), res);
        addVectorsToList(new TwoLineEdgeRounder(ox2, oy2, 1, roundedPer).getCurve(), res);
        addVectorsToList(new TwoLineEdgeRounder(oy2, ox1, 0, roundedPer).getCurve(), res);
        return new PolyLine3D(res, true);
    }


    private void addVectorsToList(List<Vector3> sourceList, List<Vector3> finalList){
        for (Vector3 v : sourceList){
            finalList.add(v);
        }
    }
}

