package com.example.tms;

import com.example.tms.TreeNode;
import com.example.tms.objects.User;

public class BST {
    TreeNode root;
    User[] max = { new User(), new User(), new User() };

    public BST() { root = null; }

    public void insert(User user){
        TreeNode node=new TreeNode(user);
        if(root==null) {
            root = node;
            return;
        }
        TreeNode prev=null;
        TreeNode temp=root;
        while (temp!=null){
            // Eger kullanicinin vergi miktari kendisinden sonrakinden az ise sol tarafa gidilir.
            if(temp.user.getOdenmemisVergiMiktari()>user.getOdenmemisVergiMiktari()){
                prev=temp;
                temp=temp.left;
            }
            // Eger kullanicinin vergi miktari kendisinden sonrakinden fazla ise sağ tarafa gidilir.
            else if ((temp.user.getOdenmemisVergiMiktari()<user.getOdenmemisVergiMiktari())){
                prev=temp;
                temp=temp.right;
            }
            // Eger kullanicinin vergi miktari kendisinden sonrakine eşit ise boş döndürülür.
            else return;
        }
        if(prev.user.getOdenmemisVergiMiktari()>user.getOdenmemisVergiMiktari())
            prev.left=node;
        else prev.right=node;
    }

    User[] findThreeLargest() { return threelargest(root); }

    User[] threelargest(TreeNode root)
    {
        if (root == null)
            return null;

        // Eger kullanicinin odenmemis vergi miktari, array'in ilk elemanindan fazlaysa array'in ilk elemani shiftlenir.
        if (root.user.getOdenmemisVergiMiktari() > max[0].getOdenmemisVergiMiktari())
        {
            max[2] = max[1];
            max[1] = max[0];
            max[0] = root.user;
        }

        // Eger kullanicinin odenmemis vergi miktari, array'in ikinci elemanindan fazlaysa array'in ikinci elemani shiftlenir.
        else if (root.user.getOdenmemisVergiMiktari() > max[1].getOdenmemisVergiMiktari() &&
                root.user.getOdenmemisVergiMiktari() != max[0].getOdenmemisVergiMiktari())
        {
            max[2] = max[1];
            max[1] = root.user;
        }

        // Eger kullanicinin odenmemis vergi miktari, array'in ucuncu elemanindan fazlaysa array'in ucuncu elemani shiftlenir.
        else if (root.user.getOdenmemisVergiMiktari() > max[2].getOdenmemisVergiMiktari() &&
                root.user.getOdenmemisVergiMiktari() != max[0].getOdenmemisVergiMiktari() &&
                root.user.getOdenmemisVergiMiktari() != max[1].getOdenmemisVergiMiktari())
            max[2] = root.user;

        threelargest(root.left);
        threelargest(root.right);
        return max;
    }
}
