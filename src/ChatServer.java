import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ChatServer {
    List<User> users = new ArrayList<>();
    Map<String, User> userMap = new HashMap<>();
    Map<User, List<String>> blockList = new HashMap<>();

    void registerUser(User user) {
        users.add(user);
        userMap.put(user.name, user);
    }

    void unregisterUser(User user) {
        users.remove(user);
        userMap.remove(user.name);
    }

    void sendMessage(Message message) {
        if (blockList.getOrDefault(userMap.get(message.sender), new ArrayList<>()).containsAll(message.recipients)) {
            System.out.println("Message blocked.");
            return;
        }
        message.recipients.forEach(recipient -> {
            if (userMap.containsKey(recipient)) {
                userMap.get(recipient).receiveMessage(message);
            }
        });
    }

    void undoMessage(Message memento) {
        // Assuming it would just not show up for the users who received it (just for demonstration)
        System.out.println("Message undo requested for: " + memento.content);
    }

    void blockUser(User blocker, String blockee) {
        blockList.putIfAbsent(blocker, new ArrayList<>());
        blockList.get(blocker).add(blockee);
    }
}
