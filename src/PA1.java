import java.util.function.BiFunction;
import java.util.function.Function;

public class PA1 {

    private static void testODE(BiFunction ode, double trueVal, double t0, double y0, double t, double h) {
        double[] sol;
        double approx;
        double error;

        System.out.println("h = " + h + ":");
        System.out.print("Euler: y(" + t + ") = ");
        sol = ODE.euler(ode, t0, y0, t, h);
        approx = sol[sol.length - 1];
        System.out.print(approx);
        error = Error.absolute(trueVal, approx);
        System.out.println("  |  Error: " + error);

        System.out.print("RK4: y(" + t + ") = ");
        sol = ODE.rk4(ode, t0, y0, t, h);
        approx = sol[sol.length - 1];
        System.out.print(approx);
        error = Error.absolute(trueVal, approx);
        System.out.println("  |  Error: " + error);

    }

    public static void main(String[] args) {
        //p2
        double h0 = 0.5;
        double h1 = 0.25;
        double h2 = 0.125;

        //2a
        BiFunction<Double, Double, Double> f = (t, y) -> 1 + y / t;
        Function<Double, Double> F = (t) -> t * Math.log(t) + 2 * t; //log in ln
        double t0 = 1;
        double y0 = 2;
        double tMax = 2;
        double trueVal = F.apply(tMax);

        System.out.println("Problem 2a:");
        System.out.println("y(" + tMax + ") = " + trueVal + "\n");
        testODE(f, trueVal, t0, y0, tMax, h0);
        System.out.println();
        testODE(f, trueVal, t0, y0, tMax, h1);
        System.out.println();
        testODE(f, trueVal, t0, y0, tMax, h2);

        //2b
        f = (t, y) -> Math.cos(2 * t) + Math.sin(3 * t);
        F = (t) -> 0.5 * Math.sin(2 * t) - (1.0 / 3) * Math.cos(3 * t) + (4.0 / 3);
        t0 = 0;
        y0 = 1;
        tMax = 1;
        trueVal = F.apply(tMax);

        System.out.println("\n \nProblem 2b:");
        System.out.println("y(" + tMax + ") = " + trueVal + "\n");
        testODE(f, trueVal, t0, y0, tMax, h0);
        System.out.println();
        testODE(f, trueVal, t0, y0, tMax, h1);
        System.out.println();
        testODE(f, trueVal, t0, y0, tMax, h2);

        //2c
        f = (t, y) -> (1 + t) / (1 + y);
        F = (t) -> Math.sqrt((t * t) + (2 * t) + 6) - 1;
        t0 = 1;
        y0 = 2;
        tMax = 2;
        trueVal = F.apply(tMax);

        System.out.println("\n \nProblem 2c:");
        System.out.println("y(" + tMax + ") = " + trueVal + "\n");
        testODE(f, trueVal, t0, y0, tMax, h0);
        System.out.println();
        testODE(f, trueVal, t0, y0, tMax, h1);
        System.out.println();
        testODE(f, trueVal, t0, y0, tMax, h2);


        //p3
        //ToDo: Do a time comparison with rcos(x), rsin(x)
        double h = 0.02;
        double theta0 = 0;
        double thetaMax = 120;
        double[] r0 = {1, 0}; //First element is the radius

        TriFunction<Double, Double, Double, Double> xP = (theta, x, y) -> -y;
        TriFunction<Double, Double, Double, Double> yP = (theta, x, y) -> x;

        double[][] circEul = ODE.eulerSystem(xP, yP, theta0, r0, thetaMax, h);
        double[][] circRK4 = ODE.rk4System(xP, yP, theta0, r0, thetaMax, h);


        System.out.println("\n\n\n\nProblem 3:");
        Graph.scatterPlot(circEul, "Euler", "DATAIN");
        Graph.scatterPlot(circRK4, "RK4", "DATAIN");
    }

}
