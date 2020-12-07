package kg2019examples_task4threedimensions.bezier;


import java.util.ArrayList;

public class BezierCurve {
    ArrayList<Point2D> finalPoints = new ArrayList<>();

    public ArrayList<Point2D> getFinalPoints() {
        return finalPoints;
    }

    public BezierCurve(ArrayList<Point2D> sourcePoints) {
        for (double t=0; t<=1; t += 0.01)
            finalPoints.add(calculateBezierFunction(t, sourcePoints));
    }


    private Point2D calculateBezierFunction(double t, ArrayList<Point2D> sourcePoints)
    {   float x = 0;
        float y = 0;

        int n = sourcePoints.size() - 1;
        for (int i=0; i <= n; i++)
        {
            x += fact(n)/(fact(i)*fact(n-i)) * sourcePoints.get(i).getX() * Math.pow(t, i) * Math.pow(1-t, n-i);
            y += fact(n)/(fact(i)*fact(n-i)) * sourcePoints.get(i).getY() * Math.pow(t, i) * Math.pow(1-t, n-i);
        }
        return new Point2D(x, y);
    }

    private double fact(double arg){
        if (arg == 0) return 1;

        double result = 1;
        for (int i=1; i<=arg; i++)
            result *= i;
        return result;
    }


}
