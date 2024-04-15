import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class test {
    static class Message {
        String sender;
        List<String> recipients;
        String timestamp;
        String content;

        public Message(String sender, List<String> recipients, String timestamp, String content) {
            this.sender = sender;
            this.recipients = recipients;
            this.timestamp = timestamp;
            this.content = content;
        }
    }

    static class User {
        String name;
        ChatServer server;
        ChatHistory history;

        public User(String name, ChatServer server) {
            this.name = name;
            this.server = server;
            this.history = new ChatHistory();
        }

        void sendMessage(String content, List<String> recipients) {
            Message message = new Message(name, recipients, new Date().toString(), content);
            server.sendMessage(message);
        }

        void receiveMessage(Message message) {
            history.addMessage(message);
        }
    }

    static class ChatServer {
        List<User> users = new ArrayList<>();
        Map<String, User> userMap = new HashMap<>();

        void registerUser(User user) {
            users.add(user);
            userMap.put(user.name, user);
        }

        void sendMessage(Message message) {
            message.recipients.forEach(recipient -> {
                if (userMap.containsKey(recipient)) {
                    userMap.get(recipient).receiveMessage(message);
                }
            });
        }
    }

    static class ChatHistory {
        List<Message> messages = new ArrayList<>();

        void addMessage(Message message) {
            messages.add(message);
        }

        Message getLastMessage() {
            return messages.size() > 0 ? messages.get(messages.size() - 1) : null;
        }
    }

    private ChatServer server;
    private User alice, bob;

    @BeforeEach
    void setUp() {
        server = new ChatServer();
        alice = new User("Alice", server);
        bob = new User("Bob", server);
        server.registerUser(alice);
        server.registerUser(bob);
    }

    @Test
    void testMessageCreation() {
        Message message = new Message("Alice", List.of("Bob"), "2022-10-01T12:00:00Z", "Hello, Bob!");
        assertEquals("Alice", message.sender);
        assertTrue(message.recipients.contains("Bob"));
        assertEquals("2022-10-01T12:00:00Z", message.timestamp);
        assertEquals("Hello, Bob!", message.content);
    }

    @Test
    void testSendMessageAndReceive() {
        alice.sendMessage("Hi Bob!", List.of("Bob"));
        assertEquals("Hi Bob!", bob.history.getLastMessage().content);
        assertEquals("Alice", bob.history.getLastMessage().sender);
    }

    @Test
    void testAddAndGetLastMessage() {
        ChatHistory history = new ChatHistory();
        Message message = new Message("Alice", List.of("Bob"), "2022-10-01T12:00:00Z", "Hi Bob!");
        history.addMessage(message);
        assertEquals(message, history.getLastMessage());
    }
}
