package hu.landov.mnb;

import java.time.LocalDate;

/**
 * Two dates type of java.time.LocalDate represent a time interval.
 * Methods:
 *
 * get/setStartDate();
 * get/setEndDate();
 *
 * @author landov
 * @version 1.1.2
 * @since 2020-11-30
 */
public class LocalDateInterval {
	private LocalDate startDate;
	private LocalDate endDate;
	
	public LocalDateInterval(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}
