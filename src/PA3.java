import java.util.function.Function;

public class PA3 {

    public static void main(String[] args) {
        //Q2
        System.out.println("Question 2");

        TriFunction<Double, Double, Double, Double> diffEq = (t, y, yp) -> 3 * yp + 2 * t * y + t * t;
        Matrix x;
        Matrix linShoot;

        double h = 0.1;
//        x = LinearAlgebra.linSpace(0, 1, h);
//        linShoot = ODE.linearShooting(diffEq, 0, 1, 1, 4, h);
//        Matrix finDiff = ODE.finiteDifferences(diffEq, 0, 1, 1, 4, h);
//        linShoot = LinearAlgebra.vectorFromColumn(linShoot, 1);
//        PyChart.plot(x, linShoot, "Linear Shooting", finDiff, "Finite Differences", "x", "y", "Numerical Solutions");
//
//
//        h = 0.05;
//        x = LinearAlgebra.linSpace(0, 1, h);
//        linShoot = ODE.linearShooting(diffEq, 0, 1, 1, 4, h);
//        finDiff = ODE.finiteDifferences(diffEq, 0, 1, 1, 4, h);
//        linShoot = LinearAlgebra.vectorFromColumn(linShoot, 1);
//
//        PyChart.plot(x, linShoot, "Linear Shooting", finDiff, "Finite Differences", "x", "y", "Numerical Solutions");

        h = 0.25;
        diffEq = (t, y, yp) -> -Math.pow(yp, 2) - y + Math.log(t);
        Function<Double, Double> analSol = (t) -> Math.log(t);
        x = LinearAlgebra.linSpace(1, 2, h);
        Matrix analSolMat = LinearAlgebra.applyFunction(x, analSol);
        linShoot = ODE.nonLinearShooting(diffEq, 1, 0, 2, Math.log(2), h);
        linShoot = LinearAlgebra.vectorFromColumn(linShoot, 1);
        PyChart.plot(x, analSolMat, "Analytical Solution", linShoot, "Numerical Solution", "x","y","Solution ODE");

    }

}
