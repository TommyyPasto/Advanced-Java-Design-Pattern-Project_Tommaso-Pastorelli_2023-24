package progetto.mp.pastorelli.tommaso;


import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pastorelli.tommaso.utils.ChatEventPrinter;
import progetto.mp.pastorelli.tommaso.utils.MockChatEventPrinter;


public class TestUser {
	
	private User user1;
	private User user2;
	private TextChat chat1;
	
	@Before
	public void before() {
		user1 =  new User("User", "1", new MockChatEventPrinter());
		user2 =  new User("User", "2", new MockChatEventPrinter());
		chat1 = new TextChat("group1", "this is group1");
	}
	
	@Test
	public void testSendMessageToChat() {
		chat1.addUser(user1);
		Message msg1 = user1.sendMessage(chat1, "hello");
		Message msg2 = user2.sendMessage(chat1, "hello2");
		assertEquals(chat1.getMessages().size(), 1);
		assertThat(chat1.getMessages())
			.contains(msg1);
		assertThat(chat1.getMessages())
			.doesNotContain(msg2);
	}
	
	@Test
	public void testJoinChat() {
		user1.joinChat(chat1);
		assertEquals(chat1.getUsers().size(), 1);
		assertThat(chat1.getUsers()).contains(user1);
	}
	
	@Test
	public void testExitChat() {
		user1.joinChat(chat1);
		user1.exitChat(chat1);
		assertThat(chat1.getUsers())
			.isEmpty();
	}
	
	@Test
	public void testAddedUserToChatEvent() {
		ChatEventPrinter printer1 = new MockChatEventPrinter();
		ChatEventPrinter printer2 = new MockChatEventPrinter();
		User user1 = new User("User", "1", printer1);
		User user2 = new User("User", "2", printer2);
		Chat chat1 = new TextChat("chat1", "this is chat1");
		chat1.addUser(user1);
		chat1.addUser(user2);
		assertEquals("AddedUser: User 1\n"
				+ "AddedUser: User 2\n", 
				printer1.toString());
		assertEquals("AddedUser: User 2\n", 
				printer2.toString());
	}
	
	@Test
	public void testAddedMessageToChatEvent() {
		ChatEventPrinter printer1 = new MockChatEventPrinter();
		ChatEventPrinter printer2 = new MockChatEventPrinter();
		User user1 = new User("User", "1", printer1);
		User user2 = new User("User", "2", printer2);
		Chat chat1 = new TextChat("chat1", "this is chat1");
		chat1.addUser(user1);
		chat1.addUser(user2);
		user1.sendMessage(chat1, "hello");
		assertEquals("AddedUser: User 2\n"
				+ "AddedMessage: hello\n", 
				printer2.toString());
		assertEquals("AddedUser: User 1\n"
				+ "AddedUser: User 2\n", 
				printer1.toString());
	}
	
	@Test
	public void testRemovedUserToChatEvent() {
		ChatEventPrinter printer1 = new MockChatEventPrinter();
		ChatEventPrinter printer2 = new MockChatEventPrinter();
		User user1 = new User("User", "1", printer1);
		User user2 = new User("User", "2", printer2);
		Chat chat1 = new TextChat("chat1", "this is chat1");
		chat1.addUser(user1);
		chat1.addUser(user2);
		chat1.removeUser(user1);
		user1.sendMessage(chat1, "hello");
		assertEquals("AddedUser: User 2\n"
				+ "RemovedUser: User 1\n", 
				printer2.toString());
	}
	
	
}
