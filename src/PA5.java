import java.util.function.BiFunction;
import java.util.function.Function;

public class PA5 {

    final static double PI = Math.PI;
    final static double CONSTANT = (1 - Math.cosh(PI)) / Math.sinh(PI);

    private static void compareSolutions(BiFunction<Double, Double, Double> analSol, Matrix apSol, double xMin, double xMax, double tMin, double tMax, double h, double k, boolean printSol, boolean plot) {
        Matrix xLin = LinearAlgebra.linSpace(xMin, xMax, h);
        Matrix yLin = LinearAlgebra.linSpace(tMin, tMax, k);
        Matrix analSolMat = PDE.build2DFunction(xLin, yLin, analSol);
        Matrix anLastRow = LinearAlgebra.vectorFromRow(analSolMat, analSolMat.getRows());
        Matrix apLastRow = LinearAlgebra.vectorFromRow(apSol, apSol.getRows());
        Matrix temp;

        double error = 0.0;
        temp = LinearAlgebra.subtractMatrices(analSolMat, apSol);
        for (int i = 2; i < temp.getCols(); i++) {
            error += Math.abs(LinearAlgebra.colSum(temp, i));
        }

        System.out.println("\n(h, k) = (" + h + ", " + k + "); Absolute Error: " + error);
        if (printSol) {
            System.out.println("\nAnalytical Solution:");
            System.out.println(analSolMat);
            System.out.println("\nNumerical Solution:");
            System.out.println(apSol);
        }

        if (plot) {
            PyChart.plot3D(xLin, yLin, analSol, "_", "x", "t", "z", "Analytical Solution");
            PyChart.plot3D(xLin, yLin, apSol, "_", "x", "t", "z", "Approximate Solution");
        }


    }

    public static void main(String[] args) {
        BiFunction<Double, Double, Double> analSolFnc = (x, y) -> (Math.cosh(PI * x) + CONSTANT * Math.sinh(PI * x)) * Math.sin(PI * y);
        double xMin = 0;
        double xMax = 1;
        double yMin = 0;
        double yMax = 1;
        Function<Double, Double> f1 = t -> 0.0;
        Function<Double, Double> f2 = t -> 0.0;
        Function<Double, Double> g1 = t -> Math.sin(PI * t);
        Function<Double, Double> g2 = t -> Math.sin(PI * t);
        Matrix apSol;
        double h;
        double k;

        h = 0.5;
        k = 0.25;
        apSol = PDE.solveLaEq(f1, f2, g1, g2, xMin, xMax, yMin, yMax, h, k);
        compareSolutions(analSolFnc, apSol, xMin, xMax, yMin, yMax, h, k,true, false);

        h = 0.25;
        k = 0.125;
        apSol = PDE.solveLaEq(f1, f2, g1, g2, xMin, xMax, yMin, yMax, h, k);
        compareSolutions(analSolFnc, apSol, xMin, xMax, yMin, yMax, h, k,false, false);

        h = 0.125;
        k = 0.0625;
        apSol = PDE.solveLaEq(f1, f2, g1, g2, xMin, xMax, yMin, yMax, h, k);
        compareSolutions(analSolFnc, apSol, xMin, xMax, yMin, yMax, h, k,false, false);

//        h = 0.0625;
//        k = 0.0375;
//        apSol = PDE.solveLaEq(f1, f2, g1, g2, xMin, xMax, yMin, yMax, h, k);
//        compareSolutions(analSolFnc, apSol, xMin, xMax, yMin, yMax, h, k,false, false);

    }

}
