package Listeners;

import org.testng.IExecutionListener;


public class Execution implements IExecutionListener {
//    private final String deleteJsonCommand = "cmd /C cd target/allure-result && del /g *.Json";
//    private final String generateAllureReportCommand = "cmd /C allure generate target/allure-results -o reports/ --clean --single-file";
    @Override
    public void onExecutionStart() {
        System.out.println("MyExecutionListener onExecutionStart");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("MyExecutionListener  onExecutionFinish");
    }
}
