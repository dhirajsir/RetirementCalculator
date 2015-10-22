package com.dhiraj;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;

/**
 * Main class to calculate projections for retirement .
 * 
 * References from http://www.zweigmedia.com/ThirdEdSite/cprob/cprob2.html
 * https://www.safaribooksonline.com/library/view/monte-carlo-simulation/
 * 9781466566903/chapter-11.html
 * http://docs.oracle.com/javase/7/docs/api/java/util/Random.html#nextGaussian()
 * http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/
 * math3/distribution/NormalDistribution.html
 * 
 * 
 * @author dkumar
 *
 */
public class RetirementCalculator {

	public static void main(String[] args)

	{
		double principal = 100000;
		int years = 20;
		double meanAggReturn = 9.4324;
		double meanAggStandardDeviation = 15.675;
		double meanConserReturn = 6.189;
		double meanConserStandardDeviation = 6.3438;
		int noIterations = 10000;
		double inflationIndex = 3.5;

		Simulator aggresiveSimulation = new Simulator(principal, years, meanAggReturn, meanAggStandardDeviation,
				inflationIndex, noIterations);
		displayResults(aggresiveSimulation.run(), "Aggresive");

		Simulator conservativeSimulation = new Simulator(principal, years, meanConserReturn,
				meanConserStandardDeviation, inflationIndex, noIterations);
		displayResults(conservativeSimulation.run(), "Conservative");

	}

	/**
	 * Helper method to display statistics
	 * 
	 * @param returns
	 * @param description
	 */
	public static void displayResults(double[] returns, String description) {

		Percentile percentile = new Percentile();
		System.out.println("Results for " + description + " Stratergy ");
		System.out.println("10 Percentile " + percentile.evaluate(returns, 10.0));
		System.out.println("50 Percentile " + percentile.evaluate(returns, 50.0));
		System.out.println("90 Percentile " + percentile.evaluate(returns, 90.0) + "\n");

	}

}
