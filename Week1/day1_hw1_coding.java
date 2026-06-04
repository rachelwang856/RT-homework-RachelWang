import java.util.*;

public class Main {
    public static void main(String[] args) {
        practiceList();
        practiceSet();
        practiceQueue();
        practiceMap();
    }

    // 1. List practice: ordered, allows duplicates, supports index
    private static void practiceList() {
        System.out.println("===== List Practice =====");

        List<String> tasks = new ArrayList<>();

        // add elements
        tasks.add("Finish homework");
        tasks.add("Review Java");
        tasks.add("Finish homework"); // duplicate allowed

        System.out.println("Original tasks: " + tasks);

        // get by index
        System.out.println("First task: " + tasks.get(0));

        // update by index
        tasks.set(1, "Review Collections");
        System.out.println("After update: " + tasks);

        // remove element
        tasks.remove("Finish homework"); // removes the first matching one
        System.out.println("After remove: " + tasks);

        // check size
        System.out.println("Size: " + tasks.size());

        // loop
        for (String task : tasks) {
            System.out.println("Task: " + task);
        }

        System.out.println();
    }

    // 2. Set practice: no duplicates
    private static void practiceSet() {
        System.out.println("===== Set Practice =====");

        Set<String> departments = new HashSet<>();

        // add elements
        departments.add("CS");
        departments.add("ECE");
        departments.add("Math");
        departments.add("CS"); // duplicate ignored

        System.out.println("Departments: " + departments);

        // contains
        System.out.println("Contains CS? " + departments.contains("CS"));
        System.out.println("Contains Biology? " + departments.contains("Biology"));

        // remove
        departments.remove("Math");
        System.out.println("After remove Math: " + departments);

        // loop
        for (String dept : departments) {
            System.out.println("Department: " + dept);
        }

        System.out.println();
    }

    // 3. Queue practice: FIFO
    private static void practiceQueue() {
        System.out.println("===== Queue Practice =====");

        Queue<String> queue = new LinkedList<>();

        // offer: add to queue
        queue.offer("Task 1");
        queue.offer("Task 2");
        queue.offer("Task 3");

        System.out.println("Queue: " + queue);

        // peek: view first element, but not remove
        System.out.println("Peek: " + queue.peek());

        // poll: remove and return first element
        System.out.println("Poll: " + queue.poll());
        System.out.println("After poll: " + queue);

        // process all elements
        while (!queue.isEmpty()) {
            System.out.println("Processing: " + queue.poll());
        }

        System.out.println("Queue after processing: " + queue);
        System.out.println();
    }

    // 4. Map practice: key-value pairs
    private static void practiceMap() {
        System.out.println("===== Map Practice =====");

        Map<String, Integer> scores = new HashMap<>();

        // put key-value pairs
        scores.put("Alice", 95);
        scores.put("Bob", 88);
        scores.put("Rachel", 100);

        System.out.println("Scores: " + scores);

        // get value by key
        System.out.println("Alice score: " + scores.get("Alice"));

        // update value
        scores.put("Bob", 90);
        System.out.println("After updating Bob: " + scores);

        // containsKey
        System.out.println("Contains Rachel? " + scores.containsKey("Rachel"));

        // remove
        scores.remove("Alice");
        System.out.println("After removing Alice: " + scores);

        // loop through map
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println();
    }
}
