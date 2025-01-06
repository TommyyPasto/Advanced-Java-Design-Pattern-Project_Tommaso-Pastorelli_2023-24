package progetto.mp.pastorelli.tommaso.visitor;

import java.util.Iterator;

import progetto.mp.pastorelli.tommaso.Community;
import progetto.mp.pastorelli.tommaso.SubCommunity;
import progetto.mp.pastorelli.tommaso.TextChat;

public abstract class CommunityVisitorAdapter implements CommunityVisitor{

	@Override
	public void visitTextChat(TextChat chat) {
		
	}

	@Override
	public void visitSubCommunity(SubCommunity subCommunity) {
		Iterator<Community> iterator = subCommunity.iterator();
		while(iterator.hasNext()) {
			iterator.next().accept(this);
		}
	}

}
