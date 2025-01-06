package progetto.mp.pastorelli.tommaso.observer;

import progetto.mp.pastorelli.tommaso.Message;

public class ChatAddedMessageEvent implements ChatEvent {

	private Message msg;

	public ChatAddedMessageEvent(Message msg) {
		this.msg = msg;
	}
	
	public Message getMessage() {
		return msg;
	}

	@Override
	public void accept(ChatEventVisitor visitor) {
		visitor.visitAddedMessageEvent(this);
	}

	
}
