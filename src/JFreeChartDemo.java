//Demo class to test out the JFreeChart library

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class JFreeChartDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JFreeChart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(5, "Category 1", "A");
            dataset.addValue(7, "Category 1", "B");
            dataset.addValue(3, "Category 1", "C");

            JFreeChart chart = ChartFactory.createBarChart(
                    "Sample Chart", "Category", "Value", dataset);

            ChartPanel chartPanel = new ChartPanel(chart);
            frame.add(chartPanel);
            frame.setVisible(true);
        });
    }
}
