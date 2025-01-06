package progetto.mp.pastorelli.tommaso.visitor;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pastorelli.tommaso.Chat;
import progetto.mp.pastorelli.tommaso.SubCommunity;
import progetto.mp.pastorelli.tommaso.TextChat;
import progetto.mp.pastorelli.tommaso.User;
import progetto.mp.pastorelli.tommaso.utils.MessagePrinter;
import progetto.mp.pastorelli.tommaso.utils.MockChatEventPrinter;
import progetto.mp.pastorelli.tommaso.utils.MockMessagePrinter;


public class TestCommunityPrintMessagesOnLevelsVisitor {

	private CommunityVisitor visitor;
	private MessagePrinter messagePrinter;
	private Chat chat1;
	private Chat chat2;
	private User user1;
	private User user2;
	private SubCommunity subCommunity1;
	private SubCommunity subCommunity2;
	
	@Before
	public void before() {
		messagePrinter = new MockMessagePrinter();
		visitor = new CommunityPrintMessagesOnLevelsVisitor(messagePrinter);
		chat1 = new TextChat("group1", "this is group1");
		chat2 = new TextChat("group2", "this is group2");
		user1 = new User("User", "1", new MockChatEventPrinter());
		user2 = new User("User", "2", new MockChatEventPrinter());
		subCommunity1 = new SubCommunity("Community1", "this is Community1");
		subCommunity2 = new SubCommunity("Community2", "this is Community2");
	}
	
	@Test
	public void testTextChatPrintMessagesWithoutMessages() {
		chat1.accept(visitor);
		assertEquals("group1:\n", messagePrinter.toString());	
	}
	
	@Test
	public void testTextChatPrintMessagesWithMessages() {
		chat1.addUser(user1);
		chat1.addUser(user2);
		user1.sendMessage(chat1, "Hello");
		user1.sendMessage(chat1, "How are you?");
		user2.sendMessage(chat1, "Hey");
		chat1.accept(visitor);
		assertEquals("group1:\n" + 
					"	User 1: Hello\n" + 
					"	User 1: How are you?\n" + 
					"	User 2: Hey\n", 
					messagePrinter.toString());	
	}
	
	@Test
	public void testEmptySubCommunityPrintMessages() {
		subCommunity1.accept(visitor);
		assertEquals("Community1:\n", messagePrinter.toString());	
	}
	
	@Test
	public void testNonEmptySubCommunityPrintMessages() {
		subCommunity1.add(chat1);
		subCommunity1.add(chat2);
		chat1.addUser(user1);
		chat1.addUser(user2);
		chat2.addUser(user1);
		user1.sendMessage(subCommunity1, "Hello");
		user1.sendMessage(subCommunity1, "How are you?");
		user2.sendMessage(subCommunity1, "Hey");
		subCommunity1.accept(visitor);
		assertEquals("Community1:\n" + 
					"	group1:\n" + 
					"		User 1: Hello\n" + 
					"		User 1: How are you?\n" + 
					"		User 2: Hey\n" + 
					"	group2:\n" + 
					"		User 1: Hello\n" + 
					"		User 1: How are you?\n", 
					messagePrinter.toString());	
	}
	
	@Test
	public void testRecursivePrintMessages() {
		subCommunity1.add(chat1);
		subCommunity2.add(chat2);
		subCommunity1.add(subCommunity2);
		chat1.addUser(user1);
		chat1.addUser(user2);
		chat2.addUser(user1);
		user1.sendMessage(subCommunity1, "Hello");
		user1.sendMessage(subCommunity1, "How are you?");
		user2.sendMessage(subCommunity1, "Hey");
		subCommunity1.accept(visitor);
		assertEquals("Community1:\n" + 
					"	group1:\n" + 
					"		User 1: Hello\n" + 
					"		User 1: How are you?\n" + 
					"		User 2: Hey\n" + 
					"	Community2:\n" + 
					"		group2:\n" + 
					"			User 1: Hello\n" + 
					"			User 1: How are you?\n",
					messagePrinter.toString());	
	}
	
	

}
