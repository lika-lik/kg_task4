package kg2019examples_task4threedimensions.bezier;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.PolyLine3D;
import models.Line3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoLineEdgeRounder {
    PolyLine3D edge;
    Vector3 vStart, vEnd, vCross;
    List<Vector3> vNot;
    int index;
    double roundedPer;
    public List<Vector3> getCurve() {
        return Arrays.asList(curveBy3V(vCross, vNot, index));
    }

    public TwoLineEdgeRounder(Line3D l1, Line3D l2, int index, double roundedPer) {
        this.roundedPer = roundedPer;
        this.index = index;
        List<Vector3> vCrossList = findVectorCross(splitIntoVectors(l1, l2));
        vCross = vCrossList.get(0);
        vNot = findNotCrossVectors(splitIntoVectors(l1, l2), vCrossList);
        vStart = cutEnd(0);
        vEnd = cutEnd(1);
        edge = round2Lines();
    }

    private PolyLine3D round2Lines(){
        List<Vector3> lines = new ArrayList<>();
        Vector3[] curve = curveBy3V(vCross, vNot, index);
        lines.add(vStart);
        for (Vector3 v : curve){
            lines.add(v);
        }
        lines.add(vEnd);
        return new  PolyLine3D(lines, false);
    }

    private Vector3 cutEnd(int index) {
        Vector3 vEnd;
        if (index < vNot.size()) {
            if (vNot.get(index).getX() == vCross.getX() && vNot.get(index).getZ() == vCross.getZ()) {
                float yEnd = vNot.get(index).getY() - (float) roundedPer * (vNot.get(index).getY() - vCross.getY());
                vEnd = new Vector3(vNot.get(index).getX(), yEnd, vNot.get(index).getZ());
            } else if (vNot.get(index).getY() == vCross.getY() && vNot.get(index).getZ() == vCross.getZ()) {
                float xEnd = vNot.get(index).getX() + (float) roundedPer * (vCross.getX() - vNot.get(index).getX());
                vEnd = new Vector3(xEnd, vNot.get(index).getY(), vNot.get(index).getZ());
            } else {
                float zEnd = vNot.get(index).getZ() + (float) roundedPer * (vCross.getZ() - vNot.get(index).getZ());
                vEnd = new Vector3(vNot.get(index).getX(), vNot.get(index).getY(), zEnd);
            }
            return vEnd;
        }
        return vNot.get(0);
    }

    private Vector3[] curveBy3V(Vector3 vCross, List<Vector3> vNot, int index){
        Vector3 xDelV = new Vector3(0,0,0);
        Vector3 yDelV = new Vector3(0,0,0);
        Vector3 zDelV = new Vector3(0,0,0);
        for (Vector3 v1 : vNot){
            Vector3 v2 = vCross;
            if (v1.getX()==v2.getX() && v1.getZ()==v2.getZ()) {
                yDelV = getYDelVector(v1, v2);
            }else if (v1.getY() == v2.getY() && v1.getZ()==v2.getZ()){
                xDelV = getXDelVector(v1, v2);
            } else if (v1.getX()==v2.getX() && v1.getY()==v2.getY()){
                zDelV = getZDelVector(v1, v2);
            }
        }
        if (index == 0) {
            if (zDelV.isEqual(new Vector3(0, 0, 0)))
                return bez(xDelV, vCross, yDelV);
            else if (yDelV.isEqual(new Vector3(0, 0, 0)))
                return bez(xDelV, vCross, zDelV);
            else
                return bez(yDelV, vCross, zDelV);
        }else{
            if (zDelV.isEqual(new Vector3(0, 0, 0)))
                return bez(yDelV, vCross, xDelV);
            else if (yDelV.isEqual(new Vector3(0, 0, 0)))
                return bez(zDelV, vCross, xDelV);
            else
                return bez(zDelV, vCross, yDelV);
        }
    }

    private Vector3 getXDelVector(Vector3 v1, Vector3 v2){
        float xDel = v2.getX() - (float)roundedPer*(v2.getX()-v1.getX());
        return new Vector3(xDel, v1.getY(), v1.getZ());
    }

    private Vector3 getYDelVector(Vector3 v1, Vector3 v2){
        float yDel = v2.getY() + (float)roundedPer*(v1.getY()-v2.getY());
        return new Vector3(v1.getX(), yDel, v1.getZ());
    }

    private Vector3 getZDelVector(Vector3 v1, Vector3 v2){
        float zDel = v2.getZ() + (float)roundedPer*(v1.getZ()-v2.getZ());
        return new Vector3(v1.getX(), v1.getY(), zDel);
    }

    private List<Vector3> findNotCrossVectors(List<Vector3> allV, List<Vector3> vCross){
        List<Vector3> vNot = new ArrayList<>();
        for (Vector3 v : allV){
            if (!vCross.contains(v))
                vNot.add(v);
        }
        return  vNot;
    }

    private List<Vector3> splitIntoVectors(Line3D l1, Line3D l2){
        List<Vector3> list = new ArrayList<>();
        list.add(l1.getP1()); list.add(l1.getP2());
        list.add(l2.getP1()); list.add(l2.getP2());
        return list;
    }

    private List<Vector3> findVectorCross(List<Vector3> listV){
        List<Vector3> vCross = new ArrayList<>();
        for (int i = 0; i < listV.size(); i ++){
            for (int j = 0; j < listV.size(); j ++){
                if (i != j){
                    if(listV.get(i).isEqual(listV.get(j))) {
                        vCross.add(listV.get(i));
                        vCross.add(listV.get(j));
                    }
                }
            }
        }
        return vCross;
    }

    private Vector3[] bez(Vector3 v1, Vector3 v2, Vector3 v3){
        Vector3[] vec;
        ArrayList<Point2D> points = new ArrayList<>();
        int i = 0;
        if (v1.getZ() == v3.getZ()) {
            points.add(new Point2D(v1.getX(), v1.getY()));
            points.add(new Point2D(v2.getX(), v2.getY()));
            points.add(new Point2D(v3.getX(), v3.getY()));
            BezierCurve bezierCurve = new BezierCurve(points);
            ArrayList<Point2D> finPoints = bezierCurve.getFinalPoints();
            vec = new Vector3[finPoints.size()];
            for (Point2D p : finPoints){
                vec[i] = new Vector3(p.getX(), p.getY(), v1.getZ());
                i++;
            }
        } else if (v1.getY() == v3.getY()) {
            points.add(new Point2D(v1.getX(), v1.getZ()));
            points.add(new Point2D(v2.getX(), v2.getZ()));
            points.add(new Point2D(v3.getX(), v3.getZ()));
            BezierCurve bezierCurve = new BezierCurve(points);
            ArrayList<Point2D> finPoints = bezierCurve.getFinalPoints();
            vec = new Vector3[finPoints.size()];
            for (Point2D p : finPoints){
                vec[i] = new Vector3(p.getX(), v1.getY(), p.getY());
                i++;
            }
        } else {
            points.add(new Point2D(v1.getZ(), v1.getY()));
            points.add(new Point2D(v2.getZ(), v2.getY()));
            points.add(new Point2D(v3.getZ(), v3.getY()));
            BezierCurve bezierCurve = new BezierCurve(points);
            ArrayList<Point2D> finPoints = bezierCurve.getFinalPoints();
            vec = new Vector3[finPoints.size()];
            for (Point2D p : finPoints){
                vec[i] = new Vector3(v1.getX(), p.getY(), p.getX());
                i++;
            }
        }
        return vec;
    }
}
