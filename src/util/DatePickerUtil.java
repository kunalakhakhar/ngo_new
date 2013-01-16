package util;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;

public class DatePickerUtil {
private static JDatePicker datePicker;
	public static JDatePicker GetDatePicker(){
		DateModel myModel = null;
		try {
			myModel = JDateComponentFactory.createDateModel(java.lang.Class
					.forName("java.sql.Date"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		datePicker = JDateComponentFactory.createJDatePicker(myModel);
		return datePicker;
	}
}
