package org.usfirst.frc.team4247.robot.parts.impl;

import org.usfirst.frc.team4247.robot.parts.ISmartDashboard;

public class SmartDashboard implements ISmartDashboard {

	@Override
	public double getNumber(String code, double defaultValue) {
		return edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getNumber(code, defaultValue);
	}

	@Override
	public double[] getNumberArray(String code, double[] defaultValue) {
		return edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getNumberArray(code, defaultValue);
	}

	@Override
	public String getString(String code, String defaultValue) {
		return edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getString(code, defaultValue);
	}

	@Override
	public String[] getStringArray(String code, String[] defaultValue) {
		return edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getStringArray(code, defaultValue);
	}

	@Override
	public boolean getBoolean(String code, boolean defaultValue) {
		return edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getBoolean(code, defaultValue);
	}

	@Override
	public boolean[] getBooleanArray(String code, boolean[] defaultValue) {
		return edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getBooleanArray(code, defaultValue);
	}

	@Override
	public void setNumber(String code, double number) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putNumber(code, number);
	}

	@Override
	public void setNumberArray(String code, double[] numbers) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putNumberArray(code, numbers);
	}

	@Override
	public void setString(String code, String string) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putString(code, string);
	}

	@Override
	public void setStringArray(String code, String[] strings) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putStringArray(code, strings);
	}

	@Override
	public void setBoolean(String code, boolean bool) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putBoolean(code, bool);
	}

	@Override
	public void setBooleanArray(String code, boolean[] bools) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putBooleanArray(code, bools);
	}

	@Override
	public void setDefaultNumber(String code, double number) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.setDefaultNumber(code, number);
	}

	@Override
	public void setDefaultNumberArray(String code, double[] numbers) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.setDefaultNumberArray(code, numbers);
	}

	@Override
	public void setDefaultString(String code, String string) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.setDefaultString(code, string);
	}

	@Override
	public void setDefaultStringArray(String code, String[] strings) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.setDefaultStringArray(code, strings);
	}

	@Override
	public void setDefaultBoolean(String code, boolean bool) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.setDefaultBoolean(code, bool);
	}

	@Override
	public void setDefaultBooleanArray(String code, boolean[] bools) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.setDefaultBooleanArray(code, bools);
	}

	@Override
	public void setAutoSelectorOptions(String[] autoModes) {
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putStringArray("Auto List", autoModes);
	}

	@Override
	public String getAutoSelectorOption() {
		return edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getString("Auto Selector", "");
	}

}
