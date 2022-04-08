package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportUtils {

		ExtentHtmlReporter htmlRepoerter;
		ExtentReports extentReports;
		ExtentTest extenTest;
		
		public ExtentReportUtils(String fileName) {
			htmlRepoerter = new ExtentHtmlReporter(fileName);
			extentReports = new ExtentReports();
			extentReports.attachReporter(htmlRepoerter);
		}
		
		public void createTestcase(String testcaseName) {
			extenTest = extentReports.createTest(testcaseName);
		}
		
		public void addLog(Status status,String comments) {
			extenTest.log(status, comments);
		}
		
		public void closeReports() {
			extentReports.flush();
		}
		
		
}
