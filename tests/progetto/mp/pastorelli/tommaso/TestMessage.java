package progetto.mp.pastorelli.tommaso;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pastorelli.tommaso.utils.ChatEventPrinter;
import progetto.mp.pastorelli.tommaso.utils.MessagePrinter;
import progetto.mp.pastorelli.tommaso.utils.MockChatEventPrinter;
import progetto.mp.pastorelli.tommaso.utils.MockMessagePrinter;

public class TestMessage {

	private MessagePrinter messagePrinter;
	private ChatEventPrinter chatPrinter;
	
	@Before
	public void before() {
		messagePrinter = new MockMessagePrinter();
		chatPrinter = new MockChatEventPrinter();
	}
	
	@Test
	public void testMessagePrinter() {
		User user = new User("User", "1", chatPrinter);
		Message msg = new Message(user, "Hello");
		msg.printMessage(messagePrinter);
		assertEquals("User 1: Hello", messagePrinter.toString());
	}
	
}
