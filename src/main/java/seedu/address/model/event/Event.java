package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

//@@author SandhyaGopakumar
/**
 * Represents an event.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    //Identity fields
    private EventName eventName;
    private Set<Tag> eventTags = new HashSet<>();
    private boolean isNotInitialisedByUser;
    /**
     * Every field must be present and not null.
     */
    public Event(EventName eventName, Set<Tag> eventTags) {
        requireAllNonNull(eventName, eventTags);
        this.eventName = eventName;
        this.eventTags.addAll(eventTags);
        this.isNotInitialisedByUser = false;
    }
    public Event() {
        EventName eventName = new EventName("event not created yet");
        this.eventName = eventName;
        this.isNotInitialisedByUser = true;
    }

    public String getName() {
        return eventName.getEventName();
    }

    public void setEvent(Event event) {
        if (!this.equals(event)) {
            this.eventName.setEventName(event.getName());
            this.eventTags = event.eventTags;
        }
    }

    /** Adds user-given details of the event. */
    public void addEvent(Event event) {
        if (!this.equals(event)) {
            this.eventName.setEventName(event.getName());
            this.eventTags = event.eventTags;
        }
        this.isNotInitialisedByUser = false;
    }
    /** Deletes user-given details of the event. */
    public void deleteEvent() {
        Event event = new Event();
        this.eventName.setEventName(event.getName());
        this.eventTags = event.eventTags;
        this.isNotInitialisedByUser = true;
    }

    public boolean isUserInitialised() {
        return !isNotInitialisedByUser;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getEventTags() {
        return Collections.unmodifiableSet(eventTags);
    }

    /**
     * Returns true if both events of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(seedu.address.model.event.Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.event.Event)) {
            return false;
        }

        seedu.address.model.event.Event otherEvent = (seedu.address.model.event.Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getEventTags().equals(getEventTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventTags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Tags: ");
        getEventTags().forEach(builder::append);
        return builder.toString();
    }

}
