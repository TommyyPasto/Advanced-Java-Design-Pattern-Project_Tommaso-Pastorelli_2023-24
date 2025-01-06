package progetto.mp.pastorelli.tommaso;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pastorelli.tommaso.utils.ChatEventPrinter;
import progetto.mp.pastorelli.tommaso.utils.MockChatEventPrinter;

public class TestTextChat {
	
	private TextChat chat;
	private ChatEventPrinter chatPrinter;
	private User user1;
	private User user2;
	
	
	@Before
	public void before() {
		chat = new TextChat("group1", "this is group1");
		chatPrinter = new MockChatEventPrinter();
		user1 = new User(
				"User", 
				"1", 
				chatPrinter);
		user2 = new User(
				"User", 
				"2", 
				chatPrinter);
	}
	
	@Test
	public void testAddUser() {
		chat.addUser(user1);
		assertEquals(chat.getUsers().size(), 1);
		assertThat(chat.getUsers())
			.contains(user1);
		assertThat(chat.getUsers())
			.doesNotContain(user2);
		assertEquals(chat.addUser(user1), false);
	}
	
	@Test
	public void testRemoveUser() {
		chat.addUser(user1);
		chat.addUser(user2);
		chat.removeUser(user1);
		assertEquals(chat.getUsers().size(), 1);
		assertThat(chat.getUsers())
			.contains(user2);
	}
	
	@Test
	public void testAddMessageDirectlyToChat() {
		chat.getUsers().add(user1);
		Message msg = new Message(user1, "hello");
		chat.addMessage(msg);
		assertThat(chat.getMessages())
			.contains(msg);
		assertThat(chat.messageIterator())
			.toIterable()
				.contains(msg);
		System.out.println(chat.getMessages());
	}
	
	@Test
	public void testFindMessageBySubstringInText() {
		chat.getUsers().add(user1);
		Message msg1 = new Message(
				user1, 
				"hello, i'm home");
		Message msg2 = new Message(
				user1, 
				"oh, it's rainy today, finally i'm home");
		chat.getMessages().add(msg1);
		chat.getMessages().add(msg2);
		assertThat(chat.findBySubstringInText("hello"))
			.contains(msg1);
		assertThat(chat.findBySubstringInText("i'm home"))
			.contains(msg1,msg2);
		assertThat(chat.findBySubstringInText("rainy"))
			.doesNotContain(msg1);
	}
	
	@Test
	public void testTextChatRename() {
		SubCommunity chat = new SubCommunity("group", "this is a group");
		chat.rename("New group");
		assertEquals(chat.getName(), "New group");
	}
	
	@Test
	public void testTextChatChangeDescription() {
		SubCommunity chat = new SubCommunity("group", "this is a group");
		chat.changeDescription("New Description");
		assertEquals(chat.getDescription(), "New Description");
	}
	
	@Test
	public void testUserIterator() {
		chat.addUser(user1);
		chat.addUser(user2);
		assertThat(chat.usersIterator())
			.toIterable()
				.containsExactlyInAnyOrder(user1, user2);
	}
	
	@Test
	public void testMessagesIterator() {
		chat.getUsers().add(user1);
		Message msg1 = new Message(
				user1, 
				"hello, i'm home");
		Message msg2 = new Message(
				user1, 
				"oh, it's rainy today, finally i'm home");
		chat.addMessage(msg1);
		chat.addMessage(msg2);
		assertThat(chat.messageIterator())
			.toIterable()
				.containsExactlyInAnyOrder(msg1,msg2);
	}
	
}
