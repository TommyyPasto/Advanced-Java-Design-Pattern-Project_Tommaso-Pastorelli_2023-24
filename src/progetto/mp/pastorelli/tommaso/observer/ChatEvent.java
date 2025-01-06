package progetto.mp.pastorelli.tommaso.observer;

public interface ChatEvent {

	public void accept(ChatEventVisitor visitor);	
	
}
