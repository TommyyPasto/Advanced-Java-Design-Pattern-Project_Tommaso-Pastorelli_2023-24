package progetto.mp.pastorelli.tommaso.observer;

import progetto.mp.pastorelli.tommaso.ChatObserver;

public class ChatAddedUserEvent extends ChatUserEvent {

	public ChatAddedUserEvent(ChatObserver user) {
		super(user);
	}

	@Override
	public void accept(ChatEventVisitor visitor) {
		visitor.visitAddedUserEvent(this);
	}

}
