package com.example.tms;

import com.example.tms.objects.User;

import java.util.List;

public class LinkedList {
    ListNode headNode;
    int length = 0;

    public LinkedList() {
        headNode = null;
    }

    public void insert(User user) {
        ListNode node = new ListNode(user);
        node.next = headNode;
        headNode = node;
        length++;
    }

    public void bubbleSort() {
        // Kucukten buyuge siralama
        for (int i = 0; i < length; i++ ) {
            ListNode currentNode = headNode;
            ListNode next = headNode.next;
            for (int j = 0; j < length - 1; j++) {
                // Eger headNode'daki kullanicinin odenmemis vergi miktari kendisinden sonraki user'dan fazlaysa
                if (currentNode.user.getOdenmemisVergiMiktari() > next.user.getOdenmemisVergiMiktari()) {
                    int temp = currentNode.user.getOdenmemisVergiMiktari();
                    String temp2 = currentNode.user.getTCKN();
                    currentNode.user.setTCKN(next.user.getTCKN());
                    currentNode.user.setOdenmemisVergiMiktari(next.user.getOdenmemisVergiMiktari());
                    next.user.setTCKN(temp2);
                    next.user.setOdenmemisVergiMiktari(temp);
                }
                currentNode = next;
                next = next.next;
            }
        }
    }

    public ListNode get(int i) {
        // Aranan degere sahip Node döndürülür.
        ListNode node = headNode;
        while (i > 0) {
            node = node.next;
            i--;
        };
        return node;
    }

    public LinkedList findTopThree() {
        // En çok vergiye sahip ilk üç kullanıcı
        this.bubbleSort();

        LinkedList results = new LinkedList();
        results.insert(get(length - 1).user);
        results.insert(get(length - 2).user);
        results.insert(get(length - 3).user);

        return results;
    }

    public User linearSearch(String tckn)
    {
        // Linked list üzerinde lineer bir şekilde gezilerek istenen tckn değerine sahip kullanıcı bulunur.
        ListNode first = headNode;
        ListNode last = null;

        for (int i = 0; i < length; i++)
        {
            if (get(i).user.getTCKN().equals(tckn))
                return get(i).user;
        }
        return null;
    }
}
