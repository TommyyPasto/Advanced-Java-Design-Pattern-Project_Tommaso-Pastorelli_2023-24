package progetto.mp.pastorelli.tommaso.visitor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import progetto.mp.pastorelli.tommaso.ChatObserver;
import progetto.mp.pastorelli.tommaso.SubCommunity;
import progetto.mp.pastorelli.tommaso.TextChat;


public class CommunityUsersVisitor extends CommunityVisitorAdapter{
	
	private Collection<ChatObserver> users = new HashSet<ChatObserver>();
	
	public Collection<ChatObserver> getUsers() {
		return users;
	}
	
	@Override
	public void visitTextChat(TextChat chat) {
		Iterator<ChatObserver> iterator = chat.usersIterator();
		while(iterator.hasNext()) {
			users.add(iterator.next());
		}
	}

	@Override
	public void visitSubCommunity(SubCommunity subCommunity) {
		super.visitSubCommunity(subCommunity);
	}

}
