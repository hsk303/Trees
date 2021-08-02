import java.util.*;
public class GenericTree {
    public static class Node{
        int data;
        ArrayList<Node>childs;

        Node(int data){
            this.data=data;
            this.childs= new ArrayList<>();
        }
    }

    public static int size(Node node){
        int count=0;
        for(Node child: node.childs){
            count+=size(child);
        }
        return count+1;

    }

    public static int maximum(Node node){
        int max=node.data;
        for(Node child: node.childs){
            max=Math.max(maximum(child),max);
        }
        return max;
    }

    public static int minimum(Node node){
        int min=node.data;
        for(Node child: node.childs){
            min=Math.min(minimum(child),min);
        }
        return min;
    }

    public static int sum(Node node){
        int sum=node.data;
        for(Node child: node.childs){
            sum+=sum(child);
        }
        return sum;
    }

    public static boolean find(Node node, int data){
      if(node.data==data){
          return true;
      }
      boolean res=false;
      for(Node child: node.childs){
          res=res || find(child,data);
      }
      return res;
    }

    public static boolean find2(Node node, int data){
        if(node.data==data)
           return true;
        boolean res=false;
        for(Node child: node.childs){
            if(find(child,data)){
                res=true;
                break;
            }
               
        }
        return res;
    }
    
    public static int countLeaves(Node node){
        if(node.childs.size()==0)
           return 1;
        int count=0;
        for(Node child: node.childs){
            count+=countLeaves(child);
        }
        return count;
    }

    public static boolean nodeToRootPath(Node node, int data, ArrayList<Integer>ans){
       if(node.data==data){
           ans.add(node.data);
           return true;
       }
       boolean res=false;
       for(Node child: node.childs){
           res=res || nodeToRootPath(child, data, ans);
       }
       if(res)
          ans.add(node.data);
       return res;
          
    }

    public static ArrayList<Node> nodeToRootPath2(Node node, int data){
        if(node.data==data){
            ArrayList<Node> ans= new ArrayList<>();
            ans.add(node);
            return ans;
        }
        ArrayList<Node> list= new ArrayList<>();
        for(Node child: node.childs){
            list=nodeToRootPath2(child,data);
            if(list.size()!=0)
               break;
        }
        if(list.size()!=0)
           list.add(node);
        return list;
    }

    public static boolean areSimilar(Node n1, Node n2){
        if(n1.childs.size()!=n2.childs.size())
           return false;
        boolean res=true;
        for(int i=0;i<n1.childs.size();i++){
            Node c1= n1.childs.get(i);
            Node c2= n2.childs.get(i);
            res=res && areSimilar(c1,c2);
        }
        return res;
    }

    public static boolean areMirror(Node n1, Node n2){
        if(n1.childs.size()!=n2.childs.size())
           return false;
        boolean res=true;
        int size=n1.childs.size();
        for(int i=0;i<n1.childs.size();i++){
            Node c1= n1.childs.get(i);
            Node c2= n2.childs.get(size-i-1);
            res=res & areMirror(c1,c2);
        }
        return res;
    }

    public static boolean isSymmetric(Node node){
        return areMirror(node,node);
    }
    static int ceil;
    static int floor;
    public static void ceilAndFloor_(Node node, int data){
        if(node.data<data)
           floor=Math.max(node.data,floor);
        if(node.data>data)
            ceil=Math.min(node.data,ceil);
        for(Node child: node.childs){
            ceilAndFloor(child,data);
        } 
    }
    public static void ceilAndFloor(Node node, int data){
        ceil=(int)1e9;
        floor=-(int)1e9;
        ceilAndFloor_(node,data);
    }

    public static int floor(Node node, int ub){
        int max= -(int)1e9;
        for(Node child: node.childs){
            int recAns= floor(child,ub);
            max= Math.max(recAns,max);
        }
        return node.data < ub ? Math.max(node.data,max): max;
    }

    public static int kthlargest(Node node, int k){
        int ub=(int)1e9;
        for(int i=0;i<k;i++){
            ub=floor(node,ub);

        }
        return ub;
    }

    public static Node getTail(Node node){
        while(node.childs.size()!=0){
            node=node.childs.get(0);

        }
        return node;
    }

    public static void linearize(Node node){
        for(Node child: node.childs){
            linearize(child);
        }
        for(int i=node.childs.size()-1;i>0;i--){
            Node tail=getTail(node.childs.get(i-1));
            tail.childs.add(node.childs.get(i));
            node.childs.remove(i);
        }
    }

    public static Node lca(Node node, int d1, int d2) {
        // write your code here
        ArrayList<Node>p1= nodeToRootPath2(node,d1);
        ArrayList<Node>p2= nodeToRootPath2(node,d2);
        int i=p1.size()-1;
        int j=p2.size()-1;
        while(i>=0 && j>=0 && p1.get(i)==p2.get(j)){
            i--;
            j--;
        }
        i++;
        j++;
        return p1.get(i);
      }

      public static int distanceBtwTwoNodes(Node node, int d1, int d2) {
        // write your code here
        ArrayList<Node>p1= nodeToRootPath2(node,d1);
        ArrayList<Node>p2= nodeToRootPath2(node,d2);
        int i=p1.size()-1;
        int j=p2.size()-1;
        while(i>=0 && j>=0 && p1.get(i)==p2.get(j)){
            i--;
            j--;
        }
        i++;
        j++;
        return i+j;
      }

      public static void levelOrder(Node node){
          LinkedList<Node> qu= new LinkedList<>();
          qu.addLast(node);
        //   int level=0;
          while(qu.size()!=0){
              int currSize=qu.size();
              while(currSize-->0){
                  Node rm= qu.removeFirst();
                  System.out.print(rm.data+" ");
                  for(Node child: rm.childs){
                      qu.addLast(child);
                  }
              }
          }
      }

      public static void levelOrderLineWise(Node node){
        LinkedList<Node> qu= new LinkedList<>();
        qu.addLast(node);
      //   int level=0;
        while(qu.size()!=0){
            int currSize=qu.size();
            while(currSize-->0){
                Node rm= qu.removeFirst();
                System.out.print(rm.data+" ");
                for(Node child: rm.childs){
                    qu.addLast(child);
                }
            }
            System.out.println();
        }
    }

    public static void zigzag(Node root){
        if(root==null)
           return;
        LinkedList<Node>st= new LinkedList<>();
        LinkedList<Node>qu= new LinkedList<>();
        qu.addLast(root);
        int level=0;
        List<List<Integer>> ans= new ArrayList<>();
        while(qu.size()!=0){
            int currSize= qu.size();
            List<Integer>smallAns= new ArrayList<>();
            while(currSize-->0){
                Node rm= qu.removeFirst();
                smallAns.add(rm.data);
                if(level%2==0){
                    for(Node child: rm.childs){
                        st.addFirst(child);
                    }
                }else{
                    for(int i= rm.childs.size()-1;i>=0;i--){
                        Node child= rm.childs.get(i);
                        st.addFirst(child);
                    }
                }
            }level++;
            ans.add(smallAns);
            LinkedList<Node> temp=qu;
            qu=st;
            st=temp;
        }

        for(List<Integer>li: ans){
            for(int ele: li){
                System.out.print(ele+" ");
            }
            System.out.println();
        }
        
    }
    

}
