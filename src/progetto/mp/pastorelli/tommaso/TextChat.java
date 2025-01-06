package progetto.mp.pastorelli.tommaso;

import java.util.*;
import java.util.stream.Collectors;

import progetto.mp.pastorelli.tommaso.observer.ChatAddedMessageEvent;
import progetto.mp.pastorelli.tommaso.visitor.CommunityVisitor;

public class TextChat extends Chat{
	
	private Collection<Message> messages = new ArrayList<Message>();
	
	public TextChat(Collection<ChatObserver> users, String name, String description) {
		super(users, name, description);
	}
	
	public TextChat(String name, String description) {
		super(name, description);
	}
	
	//uso solo per i test
	Collection<Message> getMessages() {
		return messages;
	}
	
	public Collection<Message> findBySubstringInText(String text) {
        return messages.stream()
        		.filter(message -> message.getText().contains(text))
        		.collect(Collectors.toList());
    }
	
	public Iterator<Message> messageIterator() {
		return messages.iterator();
	}
	
	@Override
	public void addMessage(Message message) {
		if(getUsers().contains(message.getUser())) {
			messages.add(message);
			notifyObservers(
					new ChatAddedMessageEvent(message)
		    );
		}
	}

	@Override 
	public void accept(CommunityVisitor visitor) {
		visitor.visitTextChat(this);
	}
	
}
