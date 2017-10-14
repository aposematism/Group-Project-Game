package com.swen.herebethetitle.logic.ai;

import com.swen.herebethetitle.entity.FriendlyStrategy;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.Region;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * A discussion between the player and an NPC.
 * @author dylan
 */
public class Discussion implements Interaction {
    /**
     * How many words the average reader can read.
     */
    public static final double AVERAGE_HUMAN_READING_SPEED_WPM = 200;
    /**
     * An error amount to account for slow readers.
     */
    public static final double SLOW_HUMAN_READING_ERROR_WPM = 50;
    /**
     * How many words per minute we should display our dialog for.
     */
    public static final double DIALOG_SPEED_WPM = AVERAGE_HUMAN_READING_SPEED_WPM + SLOW_HUMAN_READING_ERROR_WPM;
    /**
     * The NPC that is speaking.
     */
    protected final NPC speaker;
    /**
     * The friendly said by the NPC.
     */
    protected final FriendlyStrategy friendly;
    /**
     * When we should send the next message.
     */
    protected Optional<Instant> nextMessageAt;
    
    /**
     * Creates a new discussion.
     */
    public Discussion(NPC speaker) {
        this.speaker = speaker;
        this.friendly = ((FriendlyStrategy) speaker.getBehavior().get());
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
                this.nextMessageAt.get().isBefore(Instant.now()))
            sayNext(notifier);
    }

    /**
     * Says the next piece of dialog.
     * @throws InteractionOver when the discussion has finished.
     */
    public void sayNext(Notifier notifier) throws InteractionOver {
        if (!this.friendly.canTalkTo()) {
            notifier.notify(l -> l.onNPCDialogEnd(speaker));
            throw new InteractionOver();
        }

        String messageText = this.friendly.nextMessage();
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

	/**
	 * A message from the NPC to the player.
	 *
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
			double minutesPerWord = 1.0 / DIALOG_SPEED_WPM;
			double secondsPerWord = minutesPerWord * 60.0;
			double millisPerWord = secondsPerWord * 1000.0;

			int wordCount = text.split(" ").length;
            double millis = millisPerWord * wordCount;
			long millisRounded = Math.round(millis);
            return Duration.ofMillis(millisRounded);
        }
	}

}
