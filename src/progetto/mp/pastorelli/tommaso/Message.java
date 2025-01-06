package progetto.mp.pastorelli.tommaso;


import progetto.mp.pastorelli.tommaso.utils.MessagePrinter;

public class Message {
	
	private String text;
	private ChatObserver user;
	
	Message(ChatObserver user, String text) {
		this.user = user;
		this.text = text;
	}
	
	public ChatObserver getUser(){
		return user;
	}
	
	public String getText() { 
		return text;
	}
	
	public void printMessage(MessagePrinter printer) {
		printer.print(user.toString() + ": " + text);
	}

}
