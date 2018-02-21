package org.usfirst.frc.team4247.robot.parts;

public interface ISmartDashboard {
	double getNumber(String code, double defaultValue);
	double[] getNumberArray(String code, double[] defaultValue);
	String getString(String code, String defaultValue);
	String[] getStringArray(String code, String[] defaultValue);
	boolean getBoolean(String code, boolean defaultValue);
	boolean[] getBooleanArray(String code, boolean[] defaultValue);
	
	void setNumber(String code, double number);
	void setNumberArray(String code, double[] numbers);
	void setString(String code, String string);
	void setStringArray(String code, String[] strings);
	void setBoolean(String code, boolean bool);
	void setBooleanArray(String code, boolean[] bools);
	
	void setDefaultNumber(String code, double number);
	void setDefaultNumberArray(String code, double[] numbers);
	void setDefaultString(String code, String string);
	void setDefaultStringArray(String code, String[] strings);
	void setDefaultBoolean(String code, boolean bool);
	void setDefaultBooleanArray(String code, boolean[] bools);
	
	void setAutoSelectorOptions(String[] autoModes);
	String getAutoSelectorOption();
}
