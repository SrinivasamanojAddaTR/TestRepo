package com.thomsonreuters.pageobjects.utils.alerts;

import java.util.Date;

/**
 * Class represents single Search alert entity.
 * It's purpose is to indicate the state of alert during test run.
 * Created by Olga_Nadenenko on 2/21/2017.
 */
public class SearchAlert {

    private String name;

    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
