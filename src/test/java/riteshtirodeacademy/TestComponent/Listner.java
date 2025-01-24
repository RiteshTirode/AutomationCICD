package riteshtirodeacademy.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import riteshtirodeacademy.resources.ExtentReporterNG;

public class Listner extends BaseTest implements ITestListener{
 
	ExtentReports extent=ExtentReporterNG.getReporterObject();
//When you run Test parallely/concurrently from xml, then below 'test' variable is used by multiple TEST in xml, and 'test' varaible is overriden by each TEST executing concurrently
//So We may get 'Failed Error' message of one TEST in another as overriden(in Report), so Use  Thread Local class to avoid sync Issue 	
	ExtentTest test;
	ThreadLocal<ExtentTest> threadLocal=new ThreadLocal<>();  //Thread Safe
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		test=extent.createTest(result.getMethod().getMethodName());  //Creates Entry for Test Case in 
		threadLocal.set(test);      //pulls unique Thread Id for test
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		threadLocal.get().log(Status.PASS, "TEST PASSED");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
		String path=null;
		threadLocal.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}; 
		
		//BaseTest baseTest=new BaseTest(); //try debugging this step
		try {
			 path=getScreenShot(result.getMethod().getMethodName());
			 threadLocal.get().addScreenCaptureFromBase64String(path,result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		extent.flush(); //Reports are generated after this step only
		
	}
	
}
