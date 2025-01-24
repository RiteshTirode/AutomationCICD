package riteshtirodeacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReporterObject() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter extSparkReop=new ExtentSparkReporter(path);
		extSparkReop.config().setReportName("Test Report");
		extSparkReop.config().setDocumentTitle("Test for Fun");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(extSparkReop);
		extent.setSystemInfo("Quality Analyst", "Ritesh Tirode");
		return extent;
	}
}
