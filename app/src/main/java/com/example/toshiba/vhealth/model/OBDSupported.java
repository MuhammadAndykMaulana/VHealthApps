package com.example.toshiba.vhealth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TOSHIBA on 13/11/2017.
 */

public class OBDSupported {
    @Override
    public String toString() {
        return "OBDSupported{" +
                "airFlowRateMAF='" + airFlowRateMAF + '\'' +
                ", calcLoadValue='" + calcLoadValue + '\'' +
                ", coolantTemp='" + coolantTemp + '\'' +
                ", dTCCFF='" + dTCCFF + '\'' +
                ", engineRPM='" + engineRPM + '\'' +
                ", engineRunMIL='" + engineRunMIL + '\'' +
                ", engineStartMIN='" + engineStartMIN + '\'' +
                ", locOfO2Sensors='" + locOfO2Sensors + '\'' +
                ", o2Sensor11='" + o2Sensor11 + '\'' +
                ", oBDDesignation='" + oBDDesignation + '\'' +
                ", sSDTCCleared='" + sSDTCCleared + '\'' +
                ", vehicleSpeed='" + vehicleSpeed + '\'' +
                ", carId='" + carId + '\'' +
                ", intakeAirTemp='" + intakeAirTemp + '\'' +
                ", intakeManifoldPressure='" + intakeManifoldPressure + '\'' +
                ", throttlePosition='" + throttlePosition + '\'' +
                ", timingAdvance='" + timingAdvance + '\'' +
                '}';
    }

    @SerializedName("Air Flow Rate (MAF)")
    @Expose
    private String airFlowRateMAF;
    @SerializedName("Calc Load Value")
    @Expose
    private String calcLoadValue;
    @SerializedName("Coolant Temp")
    @Expose
    private String coolantTemp;
    @SerializedName("DTC C-F-F")
    @Expose
    private String dTCCFF;
    @SerializedName("Engine RPM")
    @Expose
    private String engineRPM;
    @SerializedName("Engine Run MIL")
    @Expose
    private String engineRunMIL;
    @SerializedName("Engine Start MIN")
    @Expose
    private String engineStartMIN;
    @SerializedName("Loc of O2 sensors")
    @Expose
    private String locOfO2Sensors;
    @SerializedName("O2 Sensor: 1 - 1")
    @Expose
    private String o2Sensor11;
    @SerializedName("OBD Designation")
    @Expose
    private String oBDDesignation;
    @SerializedName("S-S DTC Cleared")
    @Expose
    private String sSDTCCleared;
    @SerializedName("Vehicle Speed")
    @Expose
    private String vehicleSpeed;
    @SerializedName("car_id")
    @Expose
    private String carId;
    @SerializedName("Intake Air Temp")
    @Expose
    private String intakeAirTemp;
    @SerializedName("Intake Manifold Pressure")
    @Expose
    private String intakeManifoldPressure;
    @SerializedName("Throttle Position")
    @Expose
    private String throttlePosition;
    @SerializedName("Timing Advance")
    @Expose
    private String timingAdvance;

    public String getAirFlowRateMAF() {
        return airFlowRateMAF;
    }

    public void setAirFlowRateMAF(String airFlowRateMAF) {
        this.airFlowRateMAF = airFlowRateMAF;
    }

    public String getCalcLoadValue() {
        return calcLoadValue;
    }

    public void setCalcLoadValue(String calcLoadValue) {
        this.calcLoadValue = calcLoadValue;
    }

    public String getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(String coolantTemp) {
        this.coolantTemp = coolantTemp;
    }

    public String getDTCCFF() {
        return dTCCFF;
    }

    public void setDTCCFF(String dTCCFF) {
        this.dTCCFF = dTCCFF;
    }

    public String getEngineRPM() {
        return engineRPM;
    }

    public void setEngineRPM(String engineRPM) {
        this.engineRPM = engineRPM;
    }

    public String getEngineRunMIL() {
        return engineRunMIL;
    }

    public void setEngineRunMIL(String engineRunMIL) {
        this.engineRunMIL = engineRunMIL;
    }

    public String getEngineStartMIN() {
        return engineStartMIN;
    }

    public void setEngineStartMIN(String engineStartMIN) {
        this.engineStartMIN = engineStartMIN;
    }

    public String getLocOfO2Sensors() {
        return locOfO2Sensors;
    }

    public void setLocOfO2Sensors(String locOfO2Sensors) {
        this.locOfO2Sensors = locOfO2Sensors;
    }

    public String getO2Sensor11() {
        return o2Sensor11;
    }

    public void setO2Sensor11(String o2Sensor11) {
        this.o2Sensor11 = o2Sensor11;
    }

    public String getOBDDesignation() {
        return oBDDesignation;
    }

    public void setOBDDesignation(String oBDDesignation) {
        this.oBDDesignation = oBDDesignation;
    }

    public String getSSDTCCleared() {
        return sSDTCCleared;
    }

    public void setSSDTCCleared(String sSDTCCleared) {
        this.sSDTCCleared = sSDTCCleared;
    }

    public String getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(String vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getIntakeAirTemp() {
        return intakeAirTemp;
    }

    public void setIntakeAirTemp(String intakeAirTemp) {
        this.intakeAirTemp = intakeAirTemp;
    }

    public String getIntakeManifoldPressure() {
        return intakeManifoldPressure;
    }

    public void setIntakeManifoldPressure(String intakeManifoldPressure) {
        this.intakeManifoldPressure = intakeManifoldPressure;
    }

    public String getThrottlePosition() {
        return throttlePosition;
    }

    public void setThrottlePosition(String throttlePosition) {
        this.throttlePosition = throttlePosition;
    }

    public String getTimingAdvance() {
        return timingAdvance;
    }

    public void setTimingAdvance(String timingAdvance) {
        this.timingAdvance = timingAdvance;
    }
}
