import java.util.*;

public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        // Alice sends a message to Bob and Charlie
        alice.sendMessage("Hello, Bob and Charlie!", Arrays.asList("Bob", "Charlie"));
        // Bob sends a reply
        bob.sendMessage("Hello, Alice!", Collections.singletonList("Alice"));
        // Charlie sends a reply
        charlie.sendMessage("Hey Alice and Bob!", Arrays.asList("Alice", "Bob"));

        // Demonstrate undo functionality
        System.out.println("Undoing last message sent by Alice.");
        alice.undoLastMessage();  // Assuming we implement the method to actually remove the last message

        // Block user feature
        System.out.println("Alice blocks Charlie.");
        server.blockUser(alice, "Charlie");
        // Charlie tries to send another message
        charlie.sendMessage("Are you getting this, Alice?", Collections.singletonList("Alice"));

        // Iterate over messages received by Alice from Bob
        System.out.println("Messages received by Alice from Bob:");
        Iterator<Message> it = alice.iterator(new User("Bob", server));
        while (it.hasNext()) {
            Message message = it.next();
            System.out.println("From: " + message.sender + ", Content: " + message.content);
        }
    }
}
