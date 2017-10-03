package com.swen.herebethetitle.logic.ai;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Optional;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.Region;

/**
 * A discussion between the player and an NPC.
 * @author dylan
 */
public class Discussion implements Interaction {
    /**
     * How many words the average reader can read.
     */
    public static final int AVERAGE_HUMAN_READING_SPEED_WPM = 200;
    /**
     * An error amount to account for slow readers.
     */
    public static final int SLOW_HUMAN_READING_ERROR_WPM = 50;
    /**
     * How many words per minute we should display our dialog for.
     */
    public static final int DIALOG_SPEED_WPM = AVERAGE_HUMAN_READING_SPEED_WPM + SLOW_HUMAN_READING_ERROR_WPM;

    /**
     * A message from the NPC to the player.
     * @author dylan
     */
    private static class Message {
        /**
         * The text said by the NPC.
         */
        public final String text;
        /**
         * How long it takes to say/read the message.
         */
        public final Duration duration;
        
        /**
         * Creates a new message, estimating the duration to read it.
         */
        public Message(String text) {
            this(text, EstimateReadingDuration(text));
        }
        
        /**
         * Creates a new message.
         */
        public Message(String text, Duration duration) {
            this.text = text;
            this.duration = duration;
        }
        
        /**
         * Estimate the time it takes for a human to read text.
         */
        protected static Duration EstimateReadingDuration(String text) {
            double minutesPerWord = 1.0 / ((double)DIALOG_SPEED_WPM);
            double secondsPerWord = minutesPerWord * 60.0;
            double millisPerWord = secondsPerWord * 1000.0;

            int wordCount = text.split(" ").length;
            double millis = millisPerWord * wordCount;
            long millisRounded = Math.round(millis);
            return Duration.ofMillis(millisRounded);
        }
    }

    /**
     * The NPC that is speaking.
     */
    protected final NPC speaker;

    /**
     * The messages said by the NPC.
     */
    protected Iterator<String> messages;
    
    /**
     * When we should send the next message.
     */
    protected Optional<Instant> nextMessageAt;
    
    /**
     * Creates a new discussion.
     * @param messages The messages the NPC says.
     */
    public Discussion(NPC speaker, Iterable<String> messages) {
        this.speaker = speaker;
        this.messages = messages.iterator();
        this.nextMessageAt = Optional.empty();
    }

    @Override
    public void tick(Region region, Notifier notifier) throws InteractionOver {
        tick(notifier);
    }

    /**
     * Updates the discussion after a tick.
     */
    public void tick(Notifier notifier) throws InteractionOver {
        // Special handling for very first message.
        if (!this.nextMessageAt.isPresent()) {
            notifier.notify(l -> l.onNPCDialogBegin(speaker));
            sayNext(notifier);
            return;
        }

        if (this.nextMessageAt.isPresent() &&
                this.nextMessageAt.get().isAfter(Instant.now()))
            sayNext(notifier);
    }
    
    /**
     * Says the next piece of dialog.
     * @throws InteractionOver when the discussion has finished.
     */
    public void sayNext(Notifier notifier) throws InteractionOver {
        if (!this.messages.hasNext()) {
            notifier.notify(l -> l.onNPCDialogEnd(speaker));
            throw new InteractionOver();
        }

        String messageText = this.messages.next();
        Message message = new Message(messageText);
        
        // Tell the listeners about the message.
        notifier.notify(l -> l.onNPCDialogMessage(speaker, message.text));

        // Schedule the next message.
        this.nextMessageAt = Optional.of(Instant.now().plusMillis(message.duration.toMillis()));
    }

    @Override
    public boolean isSameAs(Interaction interaction) {
        if (!(interaction instanceof Discussion))
            return false;
        
        Discussion d = (Discussion)interaction;
        // Two discussions with the same NPC are the same.
        return d.speaker == this.speaker;
    }

}
