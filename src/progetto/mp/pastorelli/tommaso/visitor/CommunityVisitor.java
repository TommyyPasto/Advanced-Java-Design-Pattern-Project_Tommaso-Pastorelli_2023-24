package progetto.mp.pastorelli.tommaso.visitor;

import progetto.mp.pastorelli.tommaso.SubCommunity;
import progetto.mp.pastorelli.tommaso.TextChat;

public interface CommunityVisitor {
	
	public void visitTextChat(TextChat chat);
	
	public void visitSubCommunity(SubCommunity subCommunity);
	
}
