package progetto.mp.pastorelli.tommaso;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import progetto.mp.pastorelli.tommaso.observer.ChatAddedUserEvent;
import progetto.mp.pastorelli.tommaso.observer.ChatEvent;
import progetto.mp.pastorelli.tommaso.observer.ChatRemovedUserEvent;

public abstract class Chat extends Community{
	
	private Collection<ChatObserver> users;

	public Chat(Collection<ChatObserver> users, String name, String description) {
		super(name, description);
		this.users = new HashSet<ChatObserver>(users);
	}

	public Chat(String name, String description) {
		super(name, description);
		users = new HashSet<ChatObserver>();
	}
	
	//uso solo per i test
	Collection<ChatObserver> getUsers(){
		return users;
	}
	
	public boolean addUser(ChatObserver user) {
		boolean completed = users.add(user);
		if(completed) {
			notifyObservers(
				new ChatAddedUserEvent(user)
			);
		}
		return completed;
	}
	
	public boolean removeUser(ChatObserver user) {
		boolean completed = users.remove(user); 
		if(completed) {
			notifyObservers(
				new ChatRemovedUserEvent(user)
			);
		}
		return completed;
	}
	
	public Iterator<ChatObserver> usersIterator(){
		return users.iterator();
	}
	
	protected void notifyObservers(ChatEvent event) {
        users.stream()
            .forEach(user -> user.update(event));
    }
	
}
