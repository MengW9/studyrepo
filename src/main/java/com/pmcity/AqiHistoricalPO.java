package com.pmcity;

import lombok.Data;

import java.sql.Date;

/**
 * @program: algorithm_demo
 * @description: 数据库对象
 * @author: WangMengWei
 * @create: 2019-09-10 13:43
 **/
@Data
public class AqiHistoricalPO {

    private int sourceId;

    private int cityId;

    private int InspectionSiteId;

    private int AOI;

    private int PM2_5;

    private int PM10;

    private String majorPollutants;

    private Date checkDate;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getInspectionSiteId() {
        return InspectionSiteId;
    }

    public void setInspectionSiteId(int inspectionSiteId) {
        InspectionSiteId = inspectionSiteId;
    }

    public int getAOI() {
        return AOI;
    }

    public void setAOI(int AOI) {
        this.AOI = AOI;
    }

    public int getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(int PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public int getPM10() {
        return PM10;
    }

    public void setPM10(int PM10) {
        this.PM10 = PM10;
    }

    public String getMajorPollutants() {
        return majorPollutants;
    }

    public void setMajorPollutants(String majorPollutants) {
        this.majorPollutants = majorPollutants;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
