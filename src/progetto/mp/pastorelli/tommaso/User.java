package progetto.mp.pastorelli.tommaso;

import progetto.mp.pastorelli.tommaso.observer.ChatAddedMessageEvent;
import progetto.mp.pastorelli.tommaso.observer.ChatAddedUserEvent;
import progetto.mp.pastorelli.tommaso.observer.ChatEvent;
import progetto.mp.pastorelli.tommaso.observer.ChatEventVisitor;
import progetto.mp.pastorelli.tommaso.observer.ChatRemovedUserEvent;
import progetto.mp.pastorelli.tommaso.utils.ChatEventPrinter;

public class User implements ChatObserver{

	private String name;
	private String surname;
	private ChatEventPrinter printer;

	public User(String name, String surname, ChatEventPrinter printer) {
		this.name = name;
		this.surname = surname;
		this.printer = printer;
	}
	
	@Override
	public boolean joinChat(Chat chat) {
		return chat.addUser(this);
	}
	
	@Override
	public boolean exitChat(Chat chat) {
		return chat.removeUser(this);
	}
	
	@Override
	public Message sendMessage(Community chat, String text) {
		Message msg = new Message(this,text);
		chat.addMessage(msg);
		return msg;
	}
	
	@Override
	public void update(ChatEvent event) {
		event.accept(new ChatEventVisitor() {
			
			@Override
			public void visitRemoveUserEvent(ChatRemovedUserEvent e) {
				printer.print("RemovedUser: " + e.getUser().toString() + "\n");
			}
			
			@Override
			public void visitAddedUserEvent(ChatAddedUserEvent e) {
				printer.print("AddedUser: " + e.getUser().toString() + "\n");
			}
			
			@Override
			public void visitAddedMessageEvent(ChatAddedMessageEvent e) {
				if(!e.getMessage().getUser().equals(User.this))
					printer.print("AddedMessage: " + e.getMessage().getText() + "\n");
			}
		});
	}
	
	@Override
	public String toString() {
		return name + " " + surname;
	}
}
