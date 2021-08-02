import java.util.*;
public class BinaryTree{
    public static class Node{
        int data;
        Node left;
        Node right;

    Node(int data, Node left, Node right){
        this.data=data;
        this.left=left;
        this.right=right;
    }
    Node(int data){
        this(data,null,null);
    }
}
    public static void preOrder(Node root, ArrayList<Integer>ans){
        if(root==null)
           return;
        ans.add(root.data);
        preOrder(root.left,ans);
        preOrder(root.right,ans);
    }

    public static void inOrder(Node root, ArrayList<Integer>ans){
        if(root==null)
           return;
        inOrder(root.left,ans);
        ans.add(root.data);
        inOrder(root.right,ans);
    }

    public static void postOrder(Node root, ArrayList<Integer>ans){
        if(root==null)
           return;
        postOrder(root.left,ans);
        postOrder(root.right,ans);
        ans.add(root.data);
    }

    public static int size(Node node){
       if(node==null)
          return 0;
        int lsize=size(node.left);
        int rsize=size(node.right);
        return lsize + rsize + 1;
    }

    public static int sum(Node node){
        if(node==null)
            return 0;
        int lsum= sum(node.left);
        int rsum= sum(node.right);
        return lsum+rsum+node.data;
    }

    public static int max(Node node){
        if(node==null)
           return -(int)1e9;
        int lmax= max(node.left);
        int rmax= max(node.right);
        return Math.max(Math.max(lmax,rmax),node.data);

    }

    public static int min(Node node){
        if(node==null)
           return (int)1e9;
        int lmin= max(node.left);
        int rmin= max(node.right);
        return Math.min(Math.min(lmin,rmin),node.data);

    }

    public static int height(Node node){
        if(node==null)
           return -1;
        int lheight=height(node.left);
        int rheight=height(node.right);
        return Math.max(lheight,rheight)+1;
    }

    public static int countLeaves(Node node){
        if(node==null)
           return 0;
        if(node.left==null && node.right==null)
          return 1;
        int lleaves= countLeaves(node.left);
        int rleaves= countLeaves(node.right);
        return lleaves+rleaves;
    }

    public static void exactlyOneChild(Node node, ArrayList<Integer>ans){
        if(node==null || node.left==null && node.right==null)
          return;
        if(node.left==null || node.right==null)
           ans.add(node.data);
        exactlyOneChild(node.left,ans);
        exactlyOneChild(node.right,ans);
    }

    public static int countOneChild(Node node){
        if(node==null || node.left==null && node.right==null)
          return 0;
         countOneChild(node.left);
         countOneChild(node.right);
         int sum=0;
         if(node.left==null || node.right==null)
            sum+=1;
         return sum;
    }

    public static Boolean findData(Node node,int data){
        if(node==null)
          return false;
        if(node.data==data)
           return true;
        return findData(node.left,data) || findData(node.right,data);
    }

    public static Boolean nodeToRootPath(Node node, int data, ArrayList<Node>ans){
        if(node==null)
           return false;
        if(node.data==data){
            ans.add(node);
            return true;
        }
        Boolean res= nodeToRootPath(node.left,data,ans) || nodeToRootPath(node.right,data,ans);
        if(res)
           ans.add(node);
        return res;
    }
    public static ArrayList<Node> nodeToRootPath0(Node node, int data){
        ArrayList<Node>ans= new ArrayList<>();
        nodeToRootPath(node,data,ans);
        return ans;
    }
     
    public static ArrayList<Integer> nodetoRootPath2(Node node, int data){
        if(node==null)
           return null;
        if(node.data==data){
            ArrayList<Integer>list= new ArrayList<>();
            list.add(node.data);
            return list;
        }
        ArrayList<Integer> left= nodetoRootPath2(node.left,data);
        if(left!=null){
            left.add(node.data);
            return left;
        }
        ArrayList<Integer>right= nodetoRootPath2(node.right,data);
        if(right!=null){
            right.add(node.data);
            return right;
        }
        return null;
    }
    public static ArrayList<Integer>nodetoRootPath_(Node node, int data){
        ArrayList<Integer>ans= nodetoRootPath2(node,data);
        return ans!=null?ans: new ArrayList<Integer>();
    }

    public static void KlevelDown(Node node, int k, Node block, ArrayList<Integer>ans){
        if(node==null ||  k<0 || node==block)
           return;
        if(k==0){
            ans.add(node.data);
            return;
        }
        KlevelDown(node.left,k-1,block,ans);
        KlevelDown(node.right,k-1,block,ans);
    }

    public static ArrayList<Integer> kaway(Node node, int data, int k){
        ArrayList<Node>list= new ArrayList<>();
        nodeToRootPath(node,data,list);
        Node block=null;
        ArrayList<Integer>ans= new ArrayList<>();
        for(int i=0;i<list.size();i++){
            KlevelDown(list.get(i),k-i,block,ans);
            block=list.get(i);
        }
        return ans;
    }

    public static int kaway2(Node node, int data, int k, ArrayList<Integer>ans){
        if(node==null)
           return -1;
        if(node.data==data){
            KlevelDown(node, k, null, ans);
            return 1;
        }
        int left= kaway2(node.left, data, k, ans);
        if(left!=-1){
            KlevelDown(node,k-left,node.left,ans);
            return left+1;
        }
        int right= kaway2(node.right,data,k,ans);
        if(right!=-1){
            KlevelDown(node, k-right, node.right, ans);
            return right+1;
        }
        return -1;
    }

    static Node prev=null;
    public static Boolean isBST(Node node){
        if(node==null)
           return true;
        if(!isBST(node.left))
           return false;
        if(prev!=null && prev.data>node.data)
           return false;
        prev=node;
        if(!isBST(node.right))
           return false;
        return true;
    }

    public static class isBSTPair{
        boolean isBST= true;
        int maxele= -(int)1e9;
        int minele= (int)1e9;
    }

    public static isBSTPair isBST_02(Node node){
      if(node==null)
         return new isBSTPair();
       isBSTPair left= isBST_02(node.left);
       if(!left.isBST)
          return left;
       isBSTPair right= isBST_02(node.right);
       if(!right.isBST)
          return right;
       isBSTPair self= new isBSTPair();
       self.isBST= false;
       if(left.isBST && right.isBST && left.maxele< node.data && right.minele> node.data){
           self.maxele= Math.max(right.maxele, node.data);
           self.minele= Math.min(left.minele, node.data);
           self.isBST=true;
       } 
       return self;
    }
    

    public static Boolean isBal(Node node){
        if(node==null)
           return true;
        if(!isBal(node.left))
          return false;
        if(!isBal(node.right))
           return false;
        int lh= height(node.left);
        int rh= height(node.right);
        int diff=Math.abs(lh-rh);
        if(diff>1)
           return false;
        return true;
    }

    public static class balPair{
        boolean isBal= true;
        int height=-1;
    }
    public static balPair isBal2(Node node){
       if(node==null)
         return new balPair();
       balPair left= isBal2(node.left);
       if(!left.isBal)
          return left;
       balPair right= isBal2(node.right);
       if(!right.isBal)
          return right;
       balPair myAns= new balPair();
       if(Math.abs(left.height-right.height)>1){
            myAns.isBal=false;
            return myAns;
       }
       myAns.height= Math.max(left.height,right.height)+1;
       return myAns;
          
    } 

    public static class TiltPair{
        int TiltSF=0;
        int sum=0;
    }

    public static TiltPair findTilt(Node node){
        if(node==null)
           return new TiltPair();
        TiltPair left= findTilt(node.left);
        TiltPair right= findTilt(node.right);
        TiltPair myAns= new TiltPair();
        myAns.TiltSF= left.TiltSF+ right.TiltSF+ Math.abs(left.sum-right.sum);
        myAns.sum= left.sum+right.sum+node.data;
        return myAns;
    }

    public static int diameter(Node node){
        if(node==null)
           return 0;
        int ld= diameter(node.left);
        int rd= diameter(node.right);
        int lh= height(node.left);
        int rh= height(node.right);
        return Math.max(Math.max(ld,rd),lh+rh+2);
    }

    public static int[] diameter2(Node node){
        if(node==null)
           return new int[]{0,-1};
        int[] ld= diameter2(node.left);
        int[] rd= diameter2(node.right);
        int[] myAns= new int[2];
        myAns[0]= Math.max(Math.max(ld[0],rd[0]),ld[1]+rd[1]+2);
        myAns[1]= Math.max(ld[1],rd[1])+1;
        return myAns;
    }

    int diameter=0;
    public int diameter3(Node node){
       if(node==null)
          return -1;
        int ld= diameter3(node.left);
        int rd= diameter3(node.right);
        diameter= Math.max(diameter, ld+rd+2);
        return Math.max(ld,rd)+1;
    }

    public static class lBSTPair{
        boolean isBST=true;
        int max=-(int)1e9;
        int min=(int)1e9;
        int MaxSize=0;
        Node maxBSTNode= null;
    }

    public static lBSTPair largestBST(Node node){
        if(node==null)
           return new lBSTPair();
        lBSTPair left= largestBST(node.left);
        lBSTPair right= largestBST(node.right);
        lBSTPair myAns= new lBSTPair();
        if(left.isBST && right.isBST && left.max<node.data && right.min>node.data){
            myAns.isBST=true;
            myAns.max=Math.max(right.max,node.data);
            myAns.min= Math.min(left.min,node.data);
            myAns.MaxSize= left.MaxSize+right.MaxSize+1;
            myAns.maxBSTNode= node;
        }else{ 
              myAns.isBST=false;
              myAns.MaxSize= Math.max(left.MaxSize, right.MaxSize);
              myAns.maxBSTNode= left.MaxSize > right.MaxSize ? left.maxBSTNode : right.maxBSTNode;
        }
        return myAns;
    }
    
    
}
