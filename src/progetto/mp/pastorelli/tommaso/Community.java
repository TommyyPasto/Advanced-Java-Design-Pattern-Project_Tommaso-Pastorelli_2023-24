package progetto.mp.pastorelli.tommaso;

import progetto.mp.pastorelli.tommaso.visitor.CommunityVisitor;

public abstract class Community{

	private String name;
	private String description;
	
	public Community(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract void addMessage(Message messaggio);
	
	public abstract void accept(CommunityVisitor visitor);
	
	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public void rename(String name) {
		setName(name);
	}
	
	public void changeDescription(String description) {
		setDescription(description);
	}
	
}
