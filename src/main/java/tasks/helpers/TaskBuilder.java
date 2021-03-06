package tasks.helpers;

import tasks.model.Task;

import java.util.Date;

public class TaskBuilder {
    //required
    private String title;
    private String description;
    private Date time;

    //optional
    private Date start;
    private Date end;
    private int interval;

    public TaskBuilder Init(String title, String description) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.start = time;
        this.end = time;
        return this;
    }

    public TaskBuilder Init(String title, String description, Date time) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.start = time;
        this.end = time;
        return this;
    }

    public TaskBuilder WithInterval(Date start, Date end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        return this;
    }


    public Task Build() {
        Task task = new Task(this.title, this.start, this.end, this.interval, this.description);
        return task;
    }

}
