//A class for me to double-check my answers to written homework problems

import java.util.function.BiFunction;
import java.util.function.Function;

public class Workspace {
    public static void main(String[] args) {
        double h = 0.5;
        double t0 = 1.0;
        double y0 = 2.0;
        double tMax = 100.0;


        BiFunction<Double, Double, Double> diffEq = (t, y) -> 1.0 + y / t;
        Function<Double, Double> fnc = (t) -> t * Math.log(t) + 2 * t;

        double[] sol = ODE.adamsBash(diffEq, t0, y0, tMax, h);
        double[] pcSol = ODE.pc(diffEq, t0, fnc.apply(t0), tMax, h);

        double exact = fnc.apply(tMax);
        double adamAp = sol[sol.length - 1];
        double rk4Ap = pcSol[pcSol.length - 1];

        Matrix x = LinearAlgebra.linSpace(t0, tMax, h);
        Matrix real = LinearAlgebra.applyFunction(x, fnc);
        Matrix approx = new Matrix(sol);
        Matrix pcApprox = new Matrix(pcSol);

        System.out.println("Exact " + exact);
        System.out.println("Adam's Approx: " + adamAp + " | Error:" + Error.absolute(exact, adamAp));
        System.out.println("P-C Approx: " + rk4Ap + " | Error:" + Error.absolute(exact, rk4Ap));
    }
}
