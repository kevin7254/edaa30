package map;

import java.util.Arrays;

public class SimpleHashMap<K, V> implements Map<K, V> {
    private Entry<K, V>[] table;
    private int size = 0;
    private int capacity;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        this.capacity = 16;
        this.table = (Entry<K, V>[]) new Entry[capacity];
    }

    @SuppressWarnings("unchecked")
    public SimpleHashMap(int capacity) {
        this.capacity = capacity;
        this.table = (Entry<K, V>[]) new Entry[capacity];
    }

    public static void main(String[] args) {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>(10);
        map.put("5", 5);
        map.put("6", 1);
        System.out.println(map.put("6", 4));
        map.put("43", 5);
        map.put("12", 1);
        map.put("2", 4);
        map.put("8", 5);
        map.put("10", 1);
        map.put("11", 4);
        System.out.println(map.show());
        System.out.println(map.capacity);
    }

    @Override
    public V get(Object o) {
        if (o == null) return null;
        K key1 = (K) o;

        Entry<K, V> temp = find(index(key1), key1);

        if (temp != null) return temp.getValue();

        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> entry = find(index(key), key);
        V val = null;
        int index = index(key);

        //Om den finns i listan, retunera val
        if (entry != null) {
            val = entry.getValue();
            entry.setValue(value);
        } else {
            Entry<K, V> temp = table[index];
            if (temp == null) { //Om den inte finns alls i listan skapa ett nytt obj på index
                table[index] = new Entry<>(key, value);
                size++;
            } else {
                while (temp.next != null) {
                    temp = temp.next;
                }
                size++;
                temp.next = new Entry<>(key, value);
            }
        }
        if (table.length * 0.75 < size()) rehash();
        return val;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldTable = table;
        this.table = (Entry<K, V>[]) new Entry[capacity * 2];
        size = 0;
        capacity *= 2;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);

                while (entry.next != null) {
                    put(entry.next.key, entry.next.value);
                    entry = entry.next;
                }
            }
        }
    }

    @Override
    public V remove(Object o) {
        K key = (K) o;
        int index = index(key);
        Entry<K, V> entry = table[index];

        if (entry == null) return null;

        if (entry.getKey().equals(key)) {
            V val = entry.getValue();
            table[index] = entry.next;
            size--;
            return val;
        }
        Entry<K, V> temp = entry.next;
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                V val = temp.getValue();
                entry.next = temp.next;
                size--;
                return val;
            }
            entry = entry.next;
            temp = temp.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public String show() {
        StringBuilder s = new StringBuilder();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                s.append(entry).append("\n");
            }
        }
        return s.toString();
    }

    private int index(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    private Entry<K, V> find(int index, K key) {
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleHashMap<?, ?> that = (SimpleHashMap<?, ?>) o;
        return Arrays.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(table);
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{}" + getKey() + "=" + getValue();
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }
    }

}
