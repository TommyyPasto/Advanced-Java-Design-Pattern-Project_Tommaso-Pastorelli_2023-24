package progetto.mp.pastorelli.tommaso.observer;

public interface ChatEventVisitor {
	
	public void visitAddedMessageEvent(ChatAddedMessageEvent e);
	
	public void visitAddedUserEvent(ChatAddedUserEvent e);
	
	public void visitRemoveUserEvent(ChatRemovedUserEvent e);
	
}
