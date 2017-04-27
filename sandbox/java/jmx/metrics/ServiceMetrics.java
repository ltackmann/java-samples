package jmx.metrics;

import java.util.List;
import java.util.Map;

import javax.management.MXBean;

import jmx.metrics.beans.ServiceStat;

@MXBean
public interface ServiceMetrics {
	List<ServiceStat> getMostCalledServices();
	
	Map<String,Long> getSlowestServices();
}
