package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Bonus requirement: automatically retries a failed test up to 2 times
 * before it is finally marked as FAILED. Helps absorb flaky/unstable runs
 * (slow network, timing issues) without hiding genuine bugs.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("Retrying test: " + result.getName()
                    + " | Attempt: " + retryCount);
            return true;
        }
        return false;
    }
}
