package progetto.mp.pastorelli.tommaso.observer;

import progetto.mp.pastorelli.tommaso.ChatObserver;

public class ChatRemovedUserEvent extends ChatUserEvent {

	public ChatRemovedUserEvent(ChatObserver user) {
		super(user);
	}

	@Override
	public void accept(ChatEventVisitor visitor) {
		visitor.visitRemoveUserEvent(this);
	}

	
}
