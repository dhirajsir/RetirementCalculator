package com.dhiraj;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * This class is implemented to print the value inside the annotation @Ignore so
 * as to avoid justification of skipping certain tests.
 * 
 * Help of http://stackoverflow.com/questions/7644926/ignore-junit-test
 * 
 * @author dkumar
 *
 */

public class CustomJUnit4Runner extends BlockJUnit4ClassRunner {

	public CustomJUnit4Runner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	public void run(RunNotifier notifier) {
		notifier.addListener(new PrintIgnoreRunListener());
		super.run(notifier);
	}

}