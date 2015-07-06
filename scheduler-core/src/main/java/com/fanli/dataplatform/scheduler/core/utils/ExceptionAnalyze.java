package com.fanli.dataplatform.scheduler.core.utils;

import com.fanli.dataplatform.scheduler.core.dao.ExceptionDAO;
import com.fanli.dataplatform.scheduler.core.domain.ExceptionAlertDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sunny on 14-7-18.
 */
public class ExceptionAnalyze {

    private static Logger logger = LoggerFactory.getLogger(ExceptionAnalyze.class);

    @Resource(name = "exceptionDAO")
    private ExceptionDAO exceptionDAO;

    private Map<String, ExceptionAlertDO> getExceptionAlertsByTaskType(String taskType) {
        Map<String, ExceptionAlertDO> alertMap = new HashMap<String, ExceptionAlertDO>();
        List<ExceptionAlertDO> exceptionAlertDOs = exceptionDAO.getExceptionAlertsByTaskType(taskType);
        for (ExceptionAlertDO exceptionAlertDO : exceptionAlertDOs) {
            String rule = exceptionAlertDO.getRule();
            alertMap.put(rule, exceptionAlertDO);
        }
        return alertMap;
    }

    public ExceptionAlertDO analyze(InputStream is, String taskType) {
        logger.warn("analysis log starts :" + taskType);
        Map<String, ExceptionAlertDO> exceptionAlertDOMap = getExceptionAlertsByTaskType(taskType);
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                ExceptionAlertDO alert = analyzeLine(exceptionAlertDOMap, line);
                if (alert != null) {
                    logger.warn("exception log line: " + line);
                    return alert;
                }
            }
            return null;
        } catch (IOException ioe) {
            logger.error("analysis log error", ioe);
            return null;
        } finally {
            logger.warn("analysis log ens :" + taskType);
        }
    }

    private ExceptionAlertDO analyzeLine(Map<String, ExceptionAlertDO> alertMap, String line) {
        Iterator iter = alertMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Pattern pattern = Pattern.compile(key.toLowerCase());
            Matcher matcher = pattern.matcher(line.toLowerCase());
            if (matcher.matches())
                return (ExceptionAlertDO) entry.getValue();
        }
        return null;
    }

//    public static void main(String args[]) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext-resource.xml");
//        exceptionDAO = (ExceptionDAO)context.getBean("exceptionDAO");
//        File file = new File("/Users/Sunny/Desktop/test.txt");
//        try {
//            FileInputStream in = new FileInputStream(file);
//            ExceptionAlertDO alertDO = new ExceptionAnalyze().analyze(in, "wormhole");
//            System.out.println(alertDO.getId());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
