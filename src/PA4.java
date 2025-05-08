import java.util.function.BiFunction;
import java.util.function.Function;

public class PA4 {

    final static boolean runQ1 = false;
    final static boolean runQ3 = false;
    final static boolean runQ4 = true;



    private static void compareSolutions(BiFunction<Double, Double, Double> analSol, Matrix apSol, double xMin, double xMax, double tMin, double tMax, double h, double k, boolean printSol, boolean plot) {
        Matrix xLin = LinearAlgebra.linSpace(xMin, xMax, h);
        Matrix yLin = LinearAlgebra.linSpace(tMin, tMax, k);
        Matrix analSolMat = PDE.build2DFunction(xLin, yLin, analSol);
        Matrix anLastRow = LinearAlgebra.vectorFromRow(analSolMat, analSolMat.getRows());
        Matrix apLastRow = LinearAlgebra.vectorFromRow(apSol, apSol.getRows());
        double error = LinearAlgebra.matrixSum(LinearAlgebra.abs(LinearAlgebra.subtractMatrices(anLastRow, apLastRow)));

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

        double h;
        double k;
        double c;
        double xMin;
        double xMax;
        double tMin;
        double tMax;
        Function<Double, Double> f;
        Function<Double, Double> g0;
        Function<Double, Double> gl;
        BiFunction<Double, Double, Double> analSol;
        Matrix apSol;

        if (runQ1) {

            System.out.println("Question 1.");
            analSol = (x, t) -> {
                if (Math.abs(x - t) < 0.5) {
                    return Math.pow(Math.cos(Math.PI * (x - t)), 2);
                }
                return 0.0;
            };

            c = 1;
            xMin = -1;
            xMax = 3;
            tMin = 0;
            tMax = 2.4;
            f = (t) -> {
                if (Math.abs(t) <= 0.5) {
                    return Math.pow(Math.cos(Math.PI * t), 2);
                }
                return 0.0;
            };
            g0 = (t) -> 0.0;

            h = 0.5;
            k = 0.4;
            System.out.println("\nFTFS\n");
            apSol = PDE.solveTrEqFTFS(c, f, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 0.2;
            apSol = PDE.solveTrEqFTFS(c, f, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 0.1;
            apSol = PDE.solveTrEqFTFS(c, f, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 0.05;
            apSol = PDE.solveTrEqFTFS(c, f, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.03125;
            k = 0.025;
            apSol = PDE.solveTrEqFTFS(c, f, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            //

            h = 0.5;
            k = 0.4;
            System.out.println("\n\nFTBS\n");
            apSol = PDE.solveTrEqFTBS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 0.2;
            apSol = PDE.solveTrEqFTBS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 0.1;
            apSol = PDE.solveTrEqFTBS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 0.05;
            apSol = PDE.solveTrEqFTBS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.03125;
            k = 0.025;
            apSol = PDE.solveTrEqFTBS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            //FTCS

            h = 0.5;
            k = 0.4;
            System.out.println("\n\nFTCS\n");
            apSol = PDE.solveTrEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 0.2;
            apSol = PDE.solveTrEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 0.1;
            apSol = PDE.solveTrEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 0.05;
            apSol = PDE.solveTrEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.03125;
            k = 0.025;
            apSol = PDE.solveTrEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

        }

        if (runQ3) {
            System.out.println("\n\nQuestion 3.");
            analSol = (x, t) -> Math.exp(-9 * Math.PI * Math.PI * t / 2) * Math.sin(3 * Math.PI * x / 2);

            c = 2;
            xMin = 0;
            xMax = 1;
            tMin = 0;
            tMax = 1;
            g0 = (t) -> 0.0;
            double glC = 0.0;
            f = (t) -> Math.sin(3 * Math.PI * t / 2);

            System.out.println("FTCS");
            h = 0.5;
            k = 0.25;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 32.0;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 1 / 128.0;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 1 / 512.0;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.5;
            k = 1 / 16.0;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 64.0;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 8.0;
            k = 1 / 256.0;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 16.0;
            k = 1 / 1024.0;
            apSol = PDE.solveHeEqFTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);


            System.out.println("\n\nBTCS");
            h = 0.5;
            k = 0.25;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 32.0;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 1 / 128.0;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 1 / 512.0;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.5;
            k = 1 / 16.0;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 64.0;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 8.0;
            k = 1 / 256.0;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 16.0;
            k = 1 / 1024.0;
            apSol = PDE.solveHeEqBTCS(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            System.out.println("\n\nCrank-Nicolson");
            h = 0.5;
            k = 0.25;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 32.0;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 1 / 128.0;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 1 / 512.0;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.5;
            k = 1 / 16.0;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 64.0;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 8.0;
            k = 1 / 256.0;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 16.0;
            k = 1 / 1024.0;
            apSol = PDE.solveHeEqCN(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            System.out.println("\n\nLeapfrog");
            h = 0.5;
            k = 0.25;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 32.0;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 1 / 128.0;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 1 / 512.0;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.5;
            k = 1 / 16.0;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 1 / 64.0;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 8.0;
            k = 1 / 256.0;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 1 / 16.0;
            k = 1 / 1024.0;
            apSol = PDE.solveHeEqLF(c, f, g0, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

        }


        if (runQ4) {
            System.out.println("\n\nQuestion 4.");
            analSol = (x, t) -> 3 * Math.cos(Math.PI * t) * Math.sin(Math.PI * x);

            c = 1;
            xMin = 0;
            xMax = 1;
            tMin = 0;
            tMax = 1;
            g0 = (t) -> 0.0;
            gl = (t) -> 0.0;
            f = (t) -> 3 * Math.sin(Math.PI * t);
            Function<Double, Double> ft = (t) -> 0.0;

            h = 0.5;
            k = 0.25;
            apSol = PDE.solveWaEqCTCS(c, f, ft, g0, gl, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.25;
            k = 0.125;
            apSol = PDE.solveWaEqCTCS(c, f, ft, g0, gl, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.125;
            k = 0.0625;
            apSol = PDE.solveWaEqCTCS(c, f, ft, g0, gl, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);

            h = 0.0625;
            k = 0.0375;
            apSol = PDE.solveWaEqCTCS(c, f, ft, g0, gl, xMin, xMax, tMin, tMax, h, k);
            compareSolutions(analSol, apSol, xMin, xMax, tMin, tMax, h, k, false, false);
        }
    }

}
