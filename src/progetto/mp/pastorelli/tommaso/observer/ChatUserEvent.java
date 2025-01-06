package progetto.mp.pastorelli.tommaso.observer;

import progetto.mp.pastorelli.tommaso.ChatObserver;

public abstract class ChatUserEvent  implements ChatEvent {
	
	private ChatObserver user;
	
	public ChatUserEvent(ChatObserver user) {
		this.user = user;
	}

	public ChatObserver getUser() {
		return user;
	}
}
