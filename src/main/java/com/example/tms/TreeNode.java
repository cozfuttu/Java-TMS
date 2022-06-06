package com.example.tms;

import com.example.tms.objects.User;

public class TreeNode {
    User user;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(User user) {
        this.user = user;
    }
}
