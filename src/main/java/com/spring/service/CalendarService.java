package com.spring.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class CalendarService {


    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_FILE = "service-account-key.json";
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/calendar");

    private Calendar getCalendarService()  {
        try {
            GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_FILE))
                    .createScoped(SCOPES);
            return new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                    .setApplicationName("Task-manager")
                    .build();
        }catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException("Error getting calendar service");
        }
    }

    public Event create(Event event) throws  IOException {
        Calendar service = getCalendarService();
        return service.events().insert("primary", event).execute();
    }

    public List<Event> findAll() throws IOException {
        Calendar service = getCalendarService();
        Events events = service.events().list("primary")
                .execute();
        return events.getItems();
    }

    public Event update(String eventId, Event event) throws IOException {
        Calendar service = getCalendarService();
        return service.events().update("primary", eventId, event).execute();
    }

    public void delete(String id) throws IOException {
        Calendar service = getCalendarService();
        service.events().delete("primary", id).execute();
    }



}
