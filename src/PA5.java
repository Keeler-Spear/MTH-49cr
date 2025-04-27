import java.util.function.BiFunction;
import java.util.function.Function;

public class PA5 {

    final static double PI = Math.PI;
    final static double CONSTANT = (1 - Math.cosh(PI)) / Math.sinh(PI);

    public static void main(String[] args) {
        double xMin =0;
        double xMax =1;
        double yMin =0;
        double yMax =1;
        double h = 0.5;
        double k = 0.25;
        Function<Double, Double> f1 = t -> 0.0;
        Function<Double, Double> f2 = t -> 0.0;
        Function<Double, Double> g1 = t -> Math.sin(PI * t);
        Function<Double, Double> g2 = t -> Math.sin(PI * t);
        BiFunction<Double, Double, Double> analSolFnc = (x, y) -> (Math.cosh(PI * x) + CONSTANT * Math.sinh(PI * x)) * Math.sin(PI * y);

        Matrix xLin = LinearAlgebra.linSpace(xMin, xMax, h);
        Matrix yLin = LinearAlgebra.linSpace(yMin, yMax, k);

        Matrix analSol = PDE.build2DFunction(xLin, yLin, analSolFnc);

        System.out.println(analSol);

        PyChart.plot3D(xLin, yLin, analSol, "_", "x", "y", "z", "Laplaces Equation");

    }

}
