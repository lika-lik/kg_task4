/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import kg2019examples_task4threedimensions.bezier.RoundedRectangle;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

/**
 * Описывает параллелипипед по двум диагональным точкам
 * @author Alexey
 */
public class Parallelepiped implements IModel {
    private Vector3 LTF, RBN;

    /**
     * Создаёт экземпляр параллелипипеда
     * @param LTF Левая Верхняя Дальняя точка (Left Top Far)
     * @param RBN Правая Нижняя Ближняя точка (Right Bottom Near)
     */

    public Parallelepiped(Vector3 LTF, Vector3 RBN) {
        this.LTF = LTF;
        this.RBN = RBN;
    }
    

    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();
        /*Дальняя сторона (Z фиксирован и вязт у LTF)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                    new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                    new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
                    new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()),
                    new Vector3(RBN.getX() , LTF.getY(), LTF.getZ())
                }), true));
        /*Ближняя сторона  (Z фиксирован и вязт у RBN)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                    new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                    new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), LTF.getY(), RBN.getZ())
                }), true));
        
        /*Верхняя сторона (Y фиксирован и вязт у LTF)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                    new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                    new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), LTF.getY(), LTF.getZ())
                }), true));
        /*Нижняя сторона (Y фиксирован и вязт у RBN)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                    new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
                    new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())
                }), true));
        
        /*Левая сторона (X фиксирован и вязт у LTF)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                    new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                    new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                    new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                    new Vector3(LTF.getX(), RBN.getY(), LTF.getZ())
                }), true));
        /*Правая сторона (X фиксирован и вязт у RBN)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                    new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()),
                    new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                    new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())
                }), true));
        
        return lines;
    }

    public Line3D getLTF2LBF(){
        return new Line3D(new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()));
    }

    public Line3D getLBF2RBF(){
        return new Line3D(new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()));
    }

    public Line3D getRTF2RBF(){
        return new Line3D(new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()));
    }

    public Line3D getLTF2RTF(){
        return new Line3D(new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()));
    }

    public Line3D getLTN2LBN(){
        return new Line3D(new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()));
    }

    public Line3D getLBN2RBN(){
        return new Line3D(new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()));
    }

    public Line3D getRTN2RBN(){
        return new Line3D(new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()));
    }

    public Line3D getLTN2RTN(){
        return new Line3D(new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()));
    }

    public Line3D getLTN2LTF(){
        return new Line3D(new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()));
    }

    public Line3D getLBN2LBF(){
        return new Line3D(new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()));
    }

    public Line3D getRTN2RTF(){
        return new Line3D(new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()));
    }

    public Line3D getRBN2RBF(){
        return new Line3D(new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()));
    }

    public ModelByPolyLines rounded(int roundedPer){
        List<PolyLine3D> list = new ArrayList<>();
        double roundedPerD = convertPercent(roundedPer);
        RoundedRectangle rr1 = new RoundedRectangle(getLTF2LBF(), getLBF2RBF(), getRTF2RBF(), getLTF2RTF(), roundedPerD);
        RoundedRectangle rr2 = new RoundedRectangle(getLTN2LBN(), getLBN2RBN(), getRTN2RBN(), getLTN2RTN(), roundedPerD);
        RoundedRectangle rr3 = new RoundedRectangle(getLTN2LTF(), getLTF2LBF(), getLBN2LBF(), getLTN2LBN(), roundedPerD);
        RoundedRectangle rr4 = new RoundedRectangle(getRTN2RTF(), getRTF2RBF(), getRBN2RBF(), getRTN2RBN(), roundedPerD);
        RoundedRectangle rr5 = new RoundedRectangle(getLBN2LBF(), getLBF2RBF(), getRBN2RBF(), getLBN2RBN(), roundedPerD);
        RoundedRectangle rr6 = new RoundedRectangle(getLTN2LTF(), getLTF2RTF(), getRTN2RTF(), getLTN2RTN(), roundedPerD);
        list.add(rr1.getPolyLine());
        list.add(rr2.getPolyLine());
        list.add(rr3.getPolyLine());
        list.add(rr4.getPolyLine());
        list.add(rr5.getPolyLine());
        list.add(rr6.getPolyLine());
        return new ModelByPolyLines(list);
    }

    private double convertPercent(int roundedPer){
        return 0.25/100*roundedPer;
    }
}
