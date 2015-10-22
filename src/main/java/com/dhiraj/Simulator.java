package com.dhiraj;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * @author dkumar Simulation of retirement class based on Monte Carlo simulation
 * 
 */
public class Simulator {

	private double principal;
	private int years;
	private double meanReturn;
	private double standardDeviation;
	private double inflationIndex;
	private int noIterations;

	/**
	 * Constructor to initialize values
	 * 
	 * @param principal
	 * @param years
	 * @param meanReturn
	 * @param standardDeviation
	 * @param inflationIndex
	 * @param noIterations
	 */

	public Simulator(double principal, int years, double meanReturn, double standardDeviation, double inflationIndex,
			int noIterations) {
		this.principal = principal;
		this.years = years;
		this.meanReturn = meanReturn;
		this.standardDeviation = standardDeviation;
		this.inflationIndex = inflationIndex;
		this.noIterations = noIterations;
	}

	/**
	 * Core logic implementation of Monte Carlo Simulation
	 * 
	 * Create a gaussian number for the mean and standard deviation and
	 * calculate the amount for the number of years . Adjust the final amount
	 * with inflation index and repeat the steps for number of iterations
	 * 
	 * @return Array of Probabilty distributed returns for years adjusted for
	 *         inflation
	 */
	public double[] run() {

		double[] valuesAtYearEnd = new double[noIterations];

		for (int i = 0; i < noIterations; i++) {

			double vlaueAtYrEnd = 0, initialPrincipal = this.principal;

			for (int j = 1; j <= years; j++) {

				double gaussianReturn = new NormalDistribution(meanReturn, standardDeviation).sample();
				vlaueAtYrEnd = initialPrincipal * (1 + (gaussianReturn / 100));
				initialPrincipal = vlaueAtYrEnd;
			}

			valuesAtYearEnd[i] = compoundAdjustment(vlaueAtYrEnd, inflationIndex, years);

		}

		return valuesAtYearEnd;
	}

	/**
	 * 
	 * Return amount at the end of the year adjusted for inflation Calculate as
	 * A=P(1+r/100)^t
	 * 
	 * @param yrlyReturn
	 *            - normally distributed return value
	 * @param principal
	 *            - Principal amount at the start of the year
	 * @param inflationPct
	 *            - inflation
	 * @return return amount at the end of the year adjusted for inflation
	 */

	public static double compoundAdjustment(double principal, double inflationPct, int years) {

		// inflation as a percentage of principal at end of each year
		double depreciationRate = inflationPct / (100 + inflationPct);
		return principal * Math.pow((1 + (-depreciationRate)), years);

	}

	public void setMeanReturn(double meanReturn) {
		this.meanReturn = meanReturn;
	}

	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

}
