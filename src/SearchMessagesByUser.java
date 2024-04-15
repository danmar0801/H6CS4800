import java.util.Iterator;
import java.util.List;

class SearchMessagesByUser implements Iterator<Message> {
    private final Iterator<Message> iterator;

    public SearchMessagesByUser(List<Message> messages, User userToSearchWith) {
        this.iterator = messages.stream()
                .filter(m -> m.sender.equals(userToSearchWith.name) || m.recipients.contains(userToSearchWith.name))
                .iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Message next() {
        return iterator.next();
    }
}
