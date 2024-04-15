import java.util.Date;
import java.util.Iterator;
import java.util.List;

class User implements IterableByUser {
    String name;
    ChatServer server;
    ChatHistory history;

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
        this.history = new ChatHistory();
    }

    void sendMessage(String content, List<String> recipients) {
        server.sendMessage(new Message(name, recipients, new Date().toString(), content));
    }

    void receiveMessage(Message message) {
        history.addMessage(message);
        System.out.println("Message received by " + name + ": " + message.content);
    }

    void undoLastMessage() {
        Message memento = history.getLastMessage();
        server.undoMessage(memento);
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return history.iterator(userToSearchWith);
    }
}
