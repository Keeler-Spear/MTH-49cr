//A class for me to double-check my answers to written homework problems

import java.util.function.BiFunction;
import java.util.function.Function;

public class Workspace {
    public static void main(String[] args) {
        double l = 3;
        int lNum = 5;
        int n = 100;

        Function<Double, Double> f = x -> x;

        Matrix xLinSpace = LinearAlgebra.linSpace(-l * lNum, l * lNum, 0.1);

        Matrix lin = LinearAlgebra.applyFunction(xLinSpace, f);

        Matrix fourier = PDE.fourierSeries(f, l, n);

        fourier = LinReg.buildFunction(xLinSpace, fourier, BasisFunctions.fourier(n, l));

        String title = "Fourier Series of Order " + String.valueOf(n);
        PyChart.twoFnc(xLinSpace, lin, "Y=x", fourier, "Fourier Series", "X", "Y", title);

    }
}
