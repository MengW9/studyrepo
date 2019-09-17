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
public class aqiHistoricalPO {

    private int sourceId;

    private int cityId;

    private int InspectionSiteId;

    private int AOI;

    private int PM2_5;

    private int PM10;

    private String majorPollutants;

    private Date checkDate;

}
