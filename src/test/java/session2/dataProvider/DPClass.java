package session2.dataProvider;

import org.testng.annotations.DataProvider;

public class DPClass {
	@DataProvider(name = "Search data")
	public static Object[][] dataProvFunc() {
		return new Object[][] { { "Lambda Test" }, { "Automation" } };
	}
}