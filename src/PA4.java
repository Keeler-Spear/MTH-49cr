import java.util.function.BiFunction;
import java.util.function.Function;

public class PA4 {

    public static void main(String[] args) {

        double h = 0.25;
        double k = 0.25;

        Function<Double, Double> u0 = (t) -> Math.cos(t);
        BiFunction<Double, Double, Double> sol = PDE.solveTrEq(2, u0);

        Matrix x = LinearAlgebra.linSpace(1, 2, h);
        Matrix y = LinearAlgebra.linSpace(0, 1, k);

        Matrix approxSol = PDE.solveTrEqFTCS(2, u0, 1, 2, 0, 1, h, k);

        System.out.println("Approximate Solution: ");
        System.out.println(approxSol);
        System.out.println("Analytical Solution: ");
//
        PyChart.plot3D(x, y, sol, "Function", "x", "t", "z", "Function");
        PyChart.plot3D(x, y, approxSol, "Function", "x", "t", "z", "Function");




    }

}
