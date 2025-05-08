//A class for me to double-check my answers to written homework problems
import java.util.function.BiFunction;
import java.util.function.Function;

public class HW4 {

    public static void main(String[] args) {

        double h;
        double k;
        double c;
        double xMin;
        double xMax;
        double tMin;
        double tMax;
        Function<Double, Double> f;
        BiFunction<Double, Double, Double> analSol = (x,t) -> Math.cos(x - 2 * t);
        Matrix apSol;

        c = 2;
        xMin = 1;
        xMax = 2;
        tMin = 0;
        tMax = 1;
        f = (t) -> Math.cos(t);
        Function<Double, Double> g0 = (t) -> 0.0;
        //
        h = 0.25;
        k = 0.25;
        Matrix xLin = LinearAlgebra.linSpace(xMin, xMax, h);
        Matrix yLin = LinearAlgebra.linSpace(tMin, tMax, k);
        apSol = PDE.solveTrEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
        Matrix analSolMat = PDE.build2DFunction(xLin, yLin, analSol);
        System.out.println("Analytical Solution:");
        System.out.println(analSolMat);
        System.out.println("Approximate Solution:");
        System.out.println(apSol);
        PyChart.plot3D(xLin, yLin, analSolMat, "_", "x", "t", "z", "Analytical Solution");

    }

}
