import java.util.function.BiFunction;
import java.util.function.Function;

public class PDE {

    /**
     * Solves the transport equation (ut + cux = 0) analytically.
     *
     * @param c The coefficient of the ux term.
     * @param f The initial condition for the PDE.
     * @return A function that is a solution to the transport equation.
     */
    public static BiFunction<Double, Double, Double> solveTrEq(double c, Function<Double, Double> f) {
        BiFunction<Double, Double, Double> sol = (x, t) -> f.apply(x - c * t);
        return sol;
    }

}
