import java.util.List;

class Message {
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
