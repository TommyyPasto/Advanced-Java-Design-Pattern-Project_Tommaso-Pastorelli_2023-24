package progetto.mp.pastorelli.tommaso;

import progetto.mp.pastorelli.tommaso.observer.ChatEvent;

public interface ChatObserver{
	
	public Message sendMessage(Community chat, String text);

	public void update(ChatEvent e);

	public boolean joinChat(Chat chat);
	
	public boolean exitChat(Chat chat);
}
