package com.vne.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;


public class MethodListener implements IInvokedMethodListener {
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
		log.debug("Before invocation of " + method.getTestMethod().getMethodName());
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		log.debug("After invocation of " + method.getTestMethod().getMethodName());
		Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {
			VerificationFailures allFailures = VerificationFailures.getFailures();

			// Add an existing failure for the result to the failure list.
			if (result.getThrowable() != null) {
				allFailures.addFailureForTest(result, result.getThrowable());
			}

			List<Throwable> failures = allFailures.getFailuresForTest(result);
			int size = failures.size() - 1;

			if (size > 0) {
				result.setStatus(ITestResult.FAILURE);
				
				result.setThrowable(failures.get(size-1));
				if(size > 1) {
					StringBuffer message = new StringBuffer("Multiple failures (").append(size).append("):\n");
					for (int failure = 0; failure < size; failure++) {
						message.append("Failure ").append(failure + 1).append(" of ").append(size).append("\n");
						message.append(Utils.shortStackTrace(failures.get(failure), false)).append("\n");
					}
					System.out.println(message);
					
				}
			}
		}
	}

	private static final Logger log = Logger.getLogger(MethodListener.class);
}