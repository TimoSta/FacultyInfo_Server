package de.uni_passau.facultyinfo.server.scheduler;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

import de.uni_passau.facultyinfo.server.dataloader.BusLineLoader;
import de.uni_passau.facultyinfo.server.dataloader.EventLoader;
import de.uni_passau.facultyinfo.server.dataloader.FaqLoader;
import de.uni_passau.facultyinfo.server.dataloader.MenuLoader;
import de.uni_passau.facultyinfo.server.dataloader.NewsLoader;
import de.uni_passau.facultyinfo.server.dataloader.SportsCourseLoader;

@Stateless
public class Scheduler {
	@Schedule()
	public void BusLineTask() {
		System.out.println(new BusLineLoader().load());
	}

	@Schedule(minute = "10")
	public void EventTask() {
		System.out.println(new EventLoader().load());
	}

	@Schedule(minute = "20")
	public void FaqTask() {
		System.out.println(new FaqLoader().loadFaqs());
	}

	@Schedule(minute = "30")
	public void MenuTask() {
		System.out.println(new MenuLoader().load());
	}

	@Schedule(minute = "40")
	public void NewsTask() {
		System.out.println(new NewsLoader().loadNews());
	}

	@Schedule(minute = "50")
	public void SportsCourseTask() {
		System.out.println(new SportsCourseLoader().load());
	}
}
