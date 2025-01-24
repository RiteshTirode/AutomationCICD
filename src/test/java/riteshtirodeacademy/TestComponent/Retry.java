package riteshtirodeacademy.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
//When Test fails due to inconistencies(network switch)(flakiness in Application), then you can re-run test again
public class Retry implements IRetryAnalyzer{

	int count=0;
	int imax=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<imax) {
			count++;
			return true;
		}
		return false;
	}

}
