import java.util.function.BiFunction;
import java.util.function.Function;

public class PA2 {

    private static void testODE(Function<Double, Double> fnc, BiFunction<Double, Double, Double> diffEq, double t0, double y0, double tMax, double h) {

        double exact = fnc.apply(tMax);
        double fm1 = diffEq.apply(t0 - h, fnc.apply(t0 - h));
        double fm2 = diffEq.apply(t0 - 2 * h, fnc.apply(t0 - 2 * h));
        double fm3 = diffEq.apply(t0 - 3 * h, fnc.apply(t0 - 3 * h));
        double start;
        double stop;
        double time1;
        double time2;

        System.out.println("Exact Prior Values:");

        Matrix sol = ODE.adamsBash(diffEq, t0, y0, tMax, h, fm1, fm2, fm3);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            sol = ODE.adamsBash(diffEq, t0, y0, tMax, h, fm1, fm2, fm3);
        }
        stop = System.nanoTime();
        time1 = (stop - start)/1000;
        Matrix pcSol = ODE.pc(diffEq, t0, y0, tMax, h, fm1, fm2, fm3);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            pcSol = ODE.pc(diffEq, t0, y0, tMax, h, fm1, fm2, fm3);
        }
        stop = System.nanoTime();
        time2 = (stop - start)/1000;

        double adamAp = sol.getValue(sol.getRows(), 1);
        double pcAp =  pcSol.getValue(pcSol.getRows(), 1);

        System.out.println("AB: " + adamAp + " | Error: " + Error.absolute(exact, adamAp) + " | Execution time: " + time1 + " ns");
        System.out.println("PC: " + pcAp + " | Error: " + Error.absolute(exact, pcAp) + " | Execution time: " + time2 + " ns");

        sol = ODE.adamsBash(diffEq, t0, y0, tMax, h);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            sol = ODE.adamsBash(diffEq, t0, y0, tMax, h);
        }
        stop = System.nanoTime();
        time1 = (stop - start)/1000;
        pcSol = ODE.pc(diffEq, t0, fnc.apply(t0), tMax, h);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            pcSol = ODE.pc(diffEq, t0, y0, tMax, h);
        }
        stop = System.nanoTime();
        time2 = (stop - start)/1000;

        exact = fnc.apply(tMax);
        adamAp = sol.getValue(sol.getRows(), 1);
        pcAp = pcSol.getValue(pcSol.getRows(), 1);

        System.out.println("\nApproximating Prior Values:");
        System.out.println("AB: " + adamAp + " | Error: " + Error.absolute(exact, adamAp) + " | Execution time: " + time1 + " ns");
        System.out.println("PC: " + pcAp + " | Error: " + Error.absolute(exact, pcAp) + " | Execution time: " + time2 + " ns");

        Matrix eul = ODE.euler(diffEq, t0, y0, tMax, h);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            eul = ODE.euler(diffEq, t0, y0, tMax, h);
        }
        stop = System.nanoTime();
        time1 = (stop - start)/1000;
        double eulVal = eul.getValue(eul.getRows(), 1);

        Matrix rk4 = ODE.rk4(diffEq, t0, y0, tMax, h);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            rk4 = ODE.rk4(diffEq, t0, y0, tMax, h);
        }
        stop = System.nanoTime();
        time2 = (stop - start)/1000;
        double rkVal = rk4.getValue(rk4.getRows(), 1);

        System.out.println("\nOther Methods:");
        System.out.println("Euler: " + eulVal + " | Error: " + Error.absolute(exact, eulVal) + " | Execution time: " + time1 + " ns");
        System.out.println("RK4: " + rkVal + " | Error: " + Error.absolute(exact, rkVal) + " | Execution time: " + time2 + " ns");


    }

    public static void main(String[] args) {

        //QUESTION 2

        double h0 = 0.125;
        double h1 = 0.0625;
        double h2 = 0.03125;
        double t0 = 1.0;
        double y0 = 2.0;
        double tMax = 20.0;


//        BiFunction<Double, Double, Double> diffEq = (t, y) -> 1.0 + y / t;
//        Function<Double, Double> fnc = (t) -> t * Math.log(t) + 2 * t;
//
//        System.out.println("Problem 2a");
//        System.out.println("Exact: " + fnc.apply(tMax));
//        System.out.println("\nh = " + h0 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h0);
//        System.out.println("\nh = " + h1 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h1);
//        System.out.println("\nh = " + h2 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h2);
//
//        t0 = 0;
//        y0 = 1;
//        tMax = 1;
//        diffEq = (t, y) -> Math.cos(2 * t) + Math.sin(3 * t);
//        fnc = (t) -> 0.5 * Math.sin(2 * t) - 1.0/3.0 * Math.cos(3 * t) + 4.0/3.0;
//        System.out.println("Problem 2b");
//        System.out.println("Exact: " + fnc.apply(tMax));
//        System.out.println("\nh = " + h0 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h0);
//        System.out.println("\nh = " + h1 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h1);
//        System.out.println("\nh = " + h2 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h2);
//
//        t0 = 1;
//        y0 = 2;
//        tMax = 2;
//        diffEq = (t, y) -> (1 + t) / (1 + y);
//        fnc = (t) -> Math.sqrt(t * t + 2 * t + 6) - 1;
//        System.out.println("Problem 2c");
//        System.out.println("Exact: " + fnc.apply(tMax));
//        System.out.println("\nh = " + h0 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h0);
//        System.out.println("\nh = " + h1 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h1);
//        System.out.println("\nh = " + h2 + "\n");
//        testODE(fnc, diffEq, t0, y0, tMax, h2);

        //QUESTION 3

        double exactVal = Math.cos(20*20) + Math.sin(20 * 20);
        TriFunction<Double, Double, Double, Double> odeP3 = (t, y, yp) -> (1 / t) * yp - 4 * (t * t) * y;
        Matrix x = LinearAlgebra.linSpace(1, 20, 0.01);
        Matrix sol3 = ODE.solveIVP(odeP3, 1.0, 1.38177329068, -0.60233735788, 20,0.01);
        sol3 = LinearAlgebra.vectorFromColumn(sol3, 1);
        System.out.println("u(20) = " + sol3.getValue(sol3.getRows(), 1));
        System.out.println("Steps: " + (sol3.getRows() - 1));
        System.out.println("Absolute Error = " + Error.absolute(exactVal, sol3.getValue(sol3.getRows(), 1)));
        sol3.addRowTop(new double[]{1.38177329068});//Fixing the matrix size
//        PyChart.plot(x, sol3, "_", "t", "y", "RK4 Solution");

        sol3 = ODE.solveIVP(odeP3, 1.0, 1.38177329068, -0.60233735788, 20,0.002);
        sol3 = LinearAlgebra.vectorFromColumn(sol3, 1);
        System.out.println("\nu(20) = " + sol3.getValue(sol3.getRows(), 1));
        System.out.println("Steps: " + (sol3.getRows() - 1));
        System.out.println("Absolute Error = " + Error.absolute(exactVal, sol3.getValue(sol3.getRows() - 1, 1)));


    }

}
