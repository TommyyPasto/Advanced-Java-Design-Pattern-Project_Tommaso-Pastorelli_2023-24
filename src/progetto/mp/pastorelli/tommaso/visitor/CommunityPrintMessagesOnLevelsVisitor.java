package progetto.mp.pastorelli.tommaso.visitor;

import java.util.Iterator;

import progetto.mp.pastorelli.tommaso.Message;
import progetto.mp.pastorelli.tommaso.SubCommunity;
import progetto.mp.pastorelli.tommaso.TextChat;
import progetto.mp.pastorelli.tommaso.utils.MessagePrinter;

public class CommunityPrintMessagesOnLevelsVisitor extends CommunityVisitorAdapter{

	private int level = 0;
	private MessagePrinter printer;
	
	public CommunityPrintMessagesOnLevelsVisitor(MessagePrinter printer) {
		this.printer = printer;
	}

	@Override
	public void visitTextChat(TextChat chat) {
		printer.print("	".repeat(level));
		printer.print(chat.getName() + ":\n");
		Iterator<Message> iterator = chat.messageIterator();
		int temp = level;
		while(iterator.hasNext()) {
			printer.print("	".repeat(level+1));
			iterator.next().printMessage(printer);
			printer.print("\n");
		}
		level = temp;
	}

	@Override
	public void visitSubCommunity(SubCommunity subCommunity) {
		printer.print("	".repeat(level));
		printer.print(subCommunity.getName() + ":\n");
		level++;
		super.visitSubCommunity(subCommunity);
	}

}
