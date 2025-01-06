package progetto.mp.pastorelli.tommaso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import progetto.mp.pastorelli.tommaso.visitor.CommunityVisitor;

public class SubCommunity extends Community{
	
	private Collection<Community> communities = new ArrayList<Community>();
	
	public SubCommunity(String name, String description) {
		super(name, description);
	}
	
	//uso solo per i test
	Collection<Community> getCommunities() {
		return communities;
	}

	@Override
	public void addMessage(Message message) {
		communities.forEach(community->community.addMessage(message));
	}
	
	public void add(Community community) {
		communities.add(community);
	}
	
	public void remove(Community community) {
		communities.remove(community);
	}
	
	public Iterator<Community> iterator(){
		return communities.iterator();
	}
	
	@Override
	public void accept(CommunityVisitor visitor) {
		visitor.visitSubCommunity(this);
	}
	
	
}
