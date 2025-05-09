import java.util.function.Function;

public class PA3 {

    final static boolean RUN_Q2 = false;
    final static boolean RUN_Q3 = true;

    public static void main(String[] args) {
        int count = 5000;
        TriFunction<Double, Double, Double, Double> diffEq = (t, y, yp) -> 3 * yp + 2 * t * y + t * t;
        Matrix x;
        Matrix linShoot;
        double start;
        double stop;
        double time;

        if (RUN_Q2) {
            double h = 0.1;
            x = LinearAlgebra.linSpace(0, 1, h);
            linShoot = ODE.linearShooting(diffEq, 0, 1, 1, 4, h);
            Matrix finDiff = ODE.finiteDifferences(diffEq, 0, 1, 1, 4, h);
            linShoot = LinearAlgebra.vectorFromColumn(linShoot, 1);
            PyChart.plot(x, linShoot, "Linear Shooting", finDiff, "Finite Differences", "x", "y", "Numerical Solutions for h = 0.1" );

            start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                linShoot = ODE.linearShooting(diffEq, 0, 1, 1, 4, h);
            }
            stop = System.nanoTime();
            time = (stop - start) / count;
            System.out.println("Average time for linear shooting (h = 0.1): " + time);


            start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                finDiff = ODE.finiteDifferences(diffEq, 0, 1, 1, 4, h);
            }
            stop = System.nanoTime();
            time = (stop - start) / count;
            System.out.println("Average time for finite differences (h = 0.1): " + time);


            h = 0.05;
            x = LinearAlgebra.linSpace(0, 1, h);
            linShoot = ODE.linearShooting(diffEq, 0, 1, 1, 4, h);
            finDiff = ODE.finiteDifferences(diffEq, 0, 1, 1, 4, h);
            linShoot = LinearAlgebra.vectorFromColumn(linShoot, 1);

            PyChart.plot(x, linShoot, "Linear Shooting", finDiff, "Finite Differences", "x", "y", "Numerical Solutions for h = 0.05");

            start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                linShoot = ODE.linearShooting(diffEq, 0, 1, 1, 4, h);
            }
            stop = System.nanoTime();
            time = (stop - start) / count;
            System.out.println("Average time for linear shooting (h = 0.05): " + time);


            start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                finDiff = ODE.finiteDifferences(diffEq, 0, 1, 1, 4, h);
            }
            stop = System.nanoTime();
            time = (stop - start) / count;
            System.out.println("Average time for finite differences (h = 0.05): " + time);
        }

        if (RUN_Q3) {
            System.out.println("\nQuestion 3");
            double[] error = new double[3];
            double[] hs = {0.25, 0.1, 0.05};
            diffEq = (t, y, yp) -> -Math.pow(yp, 2) - y + Math.log(t);
            Function<Double, Double> analSol = (t) -> Math.log(t);
            x = LinearAlgebra.linSpace(1, 2, 0.05);
            Matrix nonlinShoot = ODE.nonLinearShooting(diffEq, 1, 0, 2, Math.log(2), 0.05);
            nonlinShoot = LinearAlgebra.vectorFromColumn(nonlinShoot, 1);
            Matrix analSolMat = LinearAlgebra.applyFunction(x, analSol);
            PyChart.plot(x, analSolMat, "Analytical Solution", nonlinShoot, "Numerical Solution", "x", "y", "Solutions for h = 0.05");
            for (int i = 0; i < 3; i++) {
                x = LinearAlgebra.linSpace(1, 2, hs[i]);
                analSolMat = LinearAlgebra.applyFunction(x, analSol);
                nonlinShoot = ODE.nonLinearShooting(diffEq, 1, 0, 2, Math.log(2), hs[i]);
                nonlinShoot = LinearAlgebra.vectorFromColumn(nonlinShoot, 1);
                error[i] = Error.absolute(analSolMat, nonlinShoot) / nonlinShoot.getRows();
                System.out.println("Average Absolute Error per Point for h = " + hs[i] + " : " + error[i]);
            }
            PyChart.plotLogLog(new Matrix(error), new Matrix(hs), "_", "Error", "Step Size", "Step Size vs. Error");

            start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                nonlinShoot = ODE.nonLinearShooting(diffEq, 1, 0, 2, Math.log(2), 0.05);
            }
            stop = System.nanoTime();
            time = (stop - start) / count;
            System.out.println("Average time for nonlinear shooting (h = 0.05): " + time);

            x = LinearAlgebra.linSpace(1, 2, 0.05);
            start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                analSolMat = LinearAlgebra.applyFunction(x, analSol);
            }
            stop = System.nanoTime();
            time = (stop - start) / count;
            System.out.println("Average time for analytical solution (h = 0.05): " + time);


        }

    }

}
