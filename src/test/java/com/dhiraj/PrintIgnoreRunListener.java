package com.dhiraj;

import org.junit.Ignore;

import org.junit.runner.notification.RunListener;

/**
 * This class is implemented to print the value inside the annotation @Ignore so
 * as to avoid justification of skipping certain tests.
 * 
 * This is an add on class created to customize Junit @Ignore behavior 
 * 
 * @author dkumar
 *
 */
public class PrintIgnoreRunListener extends RunListener {

	public void testIgnored(org.junit.runner.Description description) throws java.lang.Exception {
		super.testIgnored(description);
		Ignore ignore = description.getAnnotation(Ignore.class);
		String ignoreMessage = String.format("@Ignore test method '%s()': '%s", description.getMethodName(),
				ignore.value());

		System.out.println(ignoreMessage);
	}
}