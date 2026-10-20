package SomeInterviews.reddit;

import org.w3c.dom.Node;

import java.util.*;

public class ChatMessageMerge {
    /*
    A chat application maintains a history of messages, where each message is identified by a unique, strictly increasing integer id and contains a string content. The messages are stored in ascending order by id.

Each message is represented by a provided data model. You may assume the following structure for a message, and you should treat it as read-only in this problem.

class Message {
    int id;
    String content;
}
You are given access to a chat system that allows querying messages around a specific message ID. Given the getChatMessages API:

This is a provided API class.
     /*
//    class Chat {
//        /**
//         * Returns a list of messages centered at the given message ID.
//         *
//         * @param id The target message ID.
//         * @param windowSize The number of messages to include before and after the target ID.
//         * @return A list containing the message with the given ID and up to windowSize messages
//         *         before and after it. The returned list is sorted by message id in ascending order.
//         *         If the given id does not exist, an empty list is returned.
//         */
//        public List<Message> getChatMessages(int id, int windowSize);
//    }


    /*
    /*
    Implement the ChatMessageMerger class:

ChatMessageMerger(Chat chat, int windowSize) Initializes the merger with a provided chat object and a windowSize. All queries will use this windowSize to determine how many messages before and after each ID are included.

List<Message> mergeMessages(List<Integer> ids) Retrieves and merges all non-duplicate context messages for the given list of message IDs.

For each message ID, its context window includes the message itself and up to windowSize messages before and after it.
The returned list contains unique messages only and must be sorted by message id in ascending order.
Note: Your solution must run in time complexity better than O(M∗logM)
 where M is the total context message of the input ID list.
Constraints:

ids may contain up to k elements (1 ≤ k ≤10^6).
Each call to getChatMessages returns at most 2 × windowSize + 1 messages.
Example

Input:
["ChatMessageMerger", "mergeMessages"]
["Chat([[1, "Hello"], [2, "World"], [3, "How"], [4, "Are"], [5, "You"], [6, "Today"], [7, "Friend"], [8, "Nice"], [9, "Weather"], [10, "Right"]])", 1], [[3, 7]]]

Output:
[null, [[2, "World"], [3, "How"], [4, "Are"], [6, "Today"], [7, "Friend"], [8, "Nice"]]]

Explanation:

Chat chat = new Chat([[1, "Hello"], [2, "World"], [3, "How"], [4, "Are"], [5, "You"], [6, "Today"], [7, "Friend"], [8, "Nice"], [9, "Weather"], [10, "Right"]]); // Initializes the chat history with messages sorted by id.
ChatMessageMerger merger = new ChatMessageMerger(chat, 1); // Creates a merger with windowSize = 1.
merger.mergeMessages([3, 7]); // Returns [[2, "World"], [3, "How"], [4, "Are"], [6, "Today"], [7, "Friend"], [8, "Nice"]].


     */
    //我开始想的是对于ids这个数组，计算出每一个id的start和end，就变成merge interval了，merge完之后挨个interval取message就行了。但其实
    //这里start和end不代表确定的message id，题目是指id的前window个和后window个，并不代表是id恰好是id-window。。。
    //不能直接访问 Chat.messages，只能通过 getChatMessages() 获取已排序好消息。那么就是可以拿一堆已排序的消息，那么就是merge k sorted list
    //具体咋写呢？那还是用弄成类似merge k sorted list那样，minheap里装每个list的node，poll出来最小的然后放进去这个node的next。但是
    //这里不是一个linkedlist的结构，那怎么搞？gpt就创建了一个node节点，他其实就是存了这个list<message>在总的list<list<message>>里的index
    //以及在这个单个的list<message>里的index，以及其message。这样就可以poll出来之后放next了。也方便合并进结果集（带了message）
    class ChatMessageMerger {
        private Chat chat;
        private int windowSize;

        public ChatMessageMerger(Chat chat, int windowSize) {
            this.chat = chat;
            this.windowSize = windowSize;
        }

        public List<Message> mergeMessages(List<Integer> ids) {
            // TODO: Implement mergeMessages logic.
            List<Message> rs=new ArrayList<>();
            List<List<Message>> lists = new ArrayList<>();
            if (ids == null || ids.isEmpty()) {
                return rs;
            }
            for (int id:ids) {
                List<Message> window=chat.getChatMessages(id,windowSize);
                if(window.size()==0) {
                    continue;
                }
                lists.add(window);
            }
            // Min Heap，按照 Message ID 排序
            PriorityQueue<Node> pq = new PriorityQueue<>(
                    (a, b) -> Integer.compare(a.msg.id, b.msg.id)
            );
            for (int i=0;i<lists.size();i++) {
                pq.offer(new Node(i, 0, lists.get(i).get(0)));
            }
            while (!pq.isEmpty()) {
                Node cur=pq.poll();
                int listidx= cur.listIdx;;
                int msgidx= cur.msgIdx;;
                // 去重：由于已经有序，只需和结果最后一个比较,注意不是msgidx，是cur.msg.id
                if(rs.isEmpty()||rs.get(rs.size()-1).id!=cur.msg.id) {
                    rs.add(cur.msg);
                }

                if(lists.get(listidx).size()>msgidx+1) {
                    pq.offer(new Node(listidx, msgidx+1, lists.get(listidx).get(msgidx+1)));
                }
            }

            return rs;

        }
}
    class Node {
        int listIdx;  // 属于哪个 List
        int msgIdx;   // 在这个 List 中的位置
        Message msg;  // 当前 Message

        Node(int listIdx, int msgIdx, Message msg) {
            this.listIdx = listIdx;
            this.msgIdx = msgIdx;
            this.msg = msg;
        }
    }
    class Message {
        int id;
        String content;

        public Message(int id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return "[" + id + ": " + content + "]";
        }
    }
    class Chat {
        private List<Message> messages;

        public Chat(List<Message> messages) {
            this.messages = messages;
        }

        public List<Message> getChatMessages(int id, int windowSize) {
            List<Message> result = new ArrayList<>();
            int idx = findIndex(id);
            if (idx == -1) {
                return result;
            }

            int start = Math.max(0, idx - windowSize);
            int end = Math.min(messages.size() - 1, idx + windowSize);

            for (int i = start; i <= end; i++) {
                result.add(messages.get(i));
            }

            return result;
        }

        private int findIndex(int id) {
            for (int i = 0; i < messages.size(); i++) {
                if (messages.get(i).id == id) {
                    return i;
                }
            }
            return -1;
        }
    }

    /*
    Follow-up:
Extend the chat system to support editing of each message with a full version history. Every message is uniquely identified by id and can be updated multiple times. Each update creates a new version, but earlier versions must remain accessible.

Extend the Message class and support the following:

Message(int id, String content) Creates a message with a unique id and initial content. The initial version number is 1.

void updateContent(String newContent) Updates the message with newContent, creating a new version. Each call to this method increments the version number by 1, assigns the new content to that version, and preserves all previous versions so they remain accessible.

String getLatestContent() Returns the content of the latest version of the message.

String getContent(int version) Returns the content of the given version. If that version does not exist, return "".

Besides, mergeMessages function continues to sort, deduplicate, and merge messages using only id, and always displays the latest version content. Older versions are not shown unless directly requested via getContent(version) function.

Constraints:

There are up to n messages in the chat,
1
1 ≤ n ≤
10
6
10
6
 .
Each message may have up to m versions,
1
1 ≤ m ≤
10
6
10
6
 .
Example

Input:
["Message", "getLatestContent", "updateContent", "getLatestContent", "updateContent", "getLatestContent", "getContent", "getContent", "getContent", "Chat", "ChatMessageMerger", "mergeMessages"]
[[1, "Draft"], [], ["Draft v2"], [], ["Final"], [], [1], [2], [3], [[Message(1), Message(2), Message(3)]], [chat, 1], [[2]]]

Output:
[null, "Draft", null, "Draft v2", null, "Final", "Draft", "Draft v2", "Final", null, null, [[1, "Final"], [2, "Hello"], [3, "World"]]]

Explanation:

Message msg = new Message(1, "Draft");
msg.getLatestContent(); // Returns "Draft".
msg.updateContent("Draft v2");
msg.getLatestContent(); // Returns "Draft v2".
msg.updateContent("Final");
msg.getLatestContent(); // Returns "Final".
msg.getContent(1); // Returns "Draft".
msg.getContent(2); // Returns "Draft v2".
msg.getContent(3); // Returns "Final".
Chat chat = new Chat([msg, Message(2, "Hello"), Message(3, "World")]);
ChatMessageMerger merger = new ChatMessageMerger(chat, 1);
merger.mergeMessages([2]); // Returns [[1, "Final"], [2, "Hello"], [3, "World"]], using the latest version of each message.
     */
    //那就是每一个msg都有version，那价格map,其中key是version，value是content，key按大小排序.但是其实连map都不用，直接一个arraylist就行
    //mergeMessages不需要变
    class Message {
        int version;
        int id;
        String content;
        List<String> history=new ArrayList<>();
        public Message(int id, String content) {
            // TODO: Implement Message constructor logic.
            this.id=id;
            this.content=content;
            history.add(content);
        }

        public void updateContent(String newContent) {
            // TODO: Implement updateContent logic.
            version+=1;
            history.add(newContent);

        }

        public String getLatestContent() {
            // TODO: Implement getLatestContent logic.
            return history.get(history.size()-1);
        }

        public String getContent(int version) {
            // TODO: Implement getContent logic.
            if(version>=history.size()){
                return "";
            }
            return history.get(version-1);
        }

        @Override
        public String toString() {
            return "[" + id + ": \"" + getLatestContent() + "\"]";
        }
    }


}


