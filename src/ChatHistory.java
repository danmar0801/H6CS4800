import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ChatHistory implements IterableByUser {
    List<Message> messages = new ArrayList<>();

    void addMessage(Message message) {
        messages.add(message);
    }

    Message getLastMessage() {
        return messages.size() > 0 ? messages.get(messages.size() - 1) : null;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUser(messages, userToSearchWith);
    }
}
