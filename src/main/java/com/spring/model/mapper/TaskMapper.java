package com.spring.model.mapper;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.spring.model.entity.Creator;
import com.spring.model.entity.Organizer;
import com.spring.model.entity.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public  Task mapEventToTask(Event event) {
        return Task.builder()
                .id(event.getId())
                .location(event.getLocation())
                .description(event.getDescription())
                .summary(event.getSummary())
                .status(event.getStatus())
                .sequence(event.getSequence())
                .eventType(event.getEventType())
                .etag(event.getEtag())
                .htmlLink(event.getHtmlLink())
                .iCalUID(event.getICalUID())
                .kind(event.getKind())
                .start(event.getStart().getDateTime())
                .end(event.getEnd().getDateTime())
                .created(event.getCreated())
                .updated(event.getUpdated())
                .creator(mapCreatorToEvent(event.getCreator()))
                .organizer(mapOrganizerToEvent(event.getOrganizer()))
                .useDefault(event.getReminders().getUseDefault())
                .build();
    }

    public  List<Task> mapEventToTask(List<Event> event) {
        List<Task> tasks = new ArrayList<Task>();
        for (Event e : event)
            tasks.add(mapEventToTask(e));
        return tasks;
    }

    public  Event mapTaskToEvent(Task task) {
        return new Event().
                setId(task.getId())
                .setLocation(task.getLocation())
                .setDescription(task.getDescription())
                .setSummary(task.getSummary())
                .setStatus(task.getStatus())
                .setSequence(task.getSequence())
                .setEventType(task.getEventType())
                .setEtag(task.getEtag())
                .setHtmlLink(task.getHtmlLink())
                .setICalUID(task.getICalUID())
                .setKind(task.getKind())
                .setStart(new EventDateTime().setDateTime(task.getStart()))
                .setEnd(new EventDateTime().setDateTime(task.getEnd()))
                .setCreated(task.getCreated())
                .setUpdated(task.getUpdated())
                .setCreator(mapCreatorToEvent(task.getCreator()))
                .setOrganizer(mapOrganizerToEvent(task.getOrganizer()))
                .setReminders(new Event.Reminders().setUseDefault(task.isUseDefault()));
    }

    private  Organizer mapOrganizerToEvent(Event.Organizer organizer) {
        Organizer o = new Organizer();
        o.setEmail(organizer.getEmail());
        o.setSelf(organizer.getSelf());
        return o;
    }

    private  Event.Organizer mapOrganizerToEvent(Organizer organizer) {
        Event.Organizer o = new Event.Organizer();
        o.setEmail(organizer.getEmail());
        o.setSelf(organizer.isSelf());
        return o;
    }

    private static Creator mapCreatorToEvent(Event.Creator creator) {
        Creator c = new Creator();
        c.setEmail(creator.getEmail());
        c.setSelf(creator.getSelf());
        return c;
    }

    private static Event.Creator mapCreatorToEvent(Creator creator) {
        Event.Creator c = new Event.Creator();
        c.setEmail(creator.getEmail());
        c.setSelf(creator.isSelf());
        return c;
    }


}
