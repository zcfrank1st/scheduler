package com.fanli.dataplatform.scheduler.client.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sunny on 14-9-18.
 */
public class RecallDO implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(RecallDO.class);

    private Date startDate;
    private Date endDate;
    private List<String> instanceIds;
    private String start;
    private String end;

    public RecallDO() {

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isValid() {
        if (instanceIds == null || startDate == null || endDate == null) {
            logger.error("recall instance error, one of the parameters(instanceIds, begin, end) is null");
            return false;
        }
        if (startDate.after(endDate)) {
            logger.error("recall instance error, startDate must less than endDate!");
            return false;
        }
        Date triggerDate = new Date();
        if (startDate.after(triggerDate)) {
            logger.error("recall instance error, startDate must less than today!");
            return false;
        }
        return true;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStartAndEnd() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.start = dateFormat.format(startDate);
        this.end = dateFormat.format(endDate);
    }

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }
}
