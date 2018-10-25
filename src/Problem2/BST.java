package Problem2;

public class BST
{
	int numelem;
	private static int size;
	
	class Node 
	{
		Node left;
		Node right;
		int data;
		
		public Node(int n)
		{
			left=null;
			right=null;
			data=n;
		}
	}

	private Node cen;
	
	public BST() 
	{
		cen=null;
	}
	
	public void put(int k)
	{
		size++;
		Node add=new Node(k);
		if(cen==null)
		{
			cen=add;
			return;
		}
		Node par=null;
		Node temp=cen;
		while(true)
		{
			par=temp;
			if(k<temp.data)
			{		
				temp=temp.left;
				if(temp==null)
				{
					par.left=add;
					return;
				}
			}
			else
			{
				temp=temp.right;
				if(temp==null)
				{
					par.right=add;
					return;
				}
			}
		}
		
	}

	public void put(int []a)
	{
		for(int j=0; j<a.length; j++)
		{
			put(a[j]);
		}
		
	}
	
	public int search(int key)
	{
		int count=0;
		Node ptr=cen;
		while(ptr!=null)
		{
			if(ptr.data>key)
			{
				count++;
				ptr=ptr.left;
			}
			else if(ptr.data<key || ptr.data!=key)
			{
				count++;
				ptr=ptr.right;
			}
			else if(ptr.data==key)
			{
				count++;
				System.out.println(count+" comparisons made");
				return ptr.data;
			}
		}
		System.out.println("Key not found");
		System.out.println(count+" comparisons made");
		return 0;
	}
	
	public static int returnSize()
	{
		return size;
	}	
	
	public int[] sortedTree()
	{
		int s=size;
		int[] a=new int[s];
		SortHelper(cen,a,0);
		int n=a.length;
		int l;
		int hold;
        for (int z=0; z<n-1; z++) 
        { 
            l=z; 
            for(int k=z+1; k<n; k++) 
            {
                if (a[k] < a[l]) 
                    l=k; 
            }
            hold=a[l]; 
            a[l]=a[z]; 
            a[z]=hold; 
        }        
		return a;			
	}
	
	private static int SortHelper(Node x, int[] a, int in) 
	{
		if (x.right!=null) 
	    {
	        in=SortHelper(x.right, a, in);
	    }
	    if (x.left!=null) 
	    {
	        in=SortHelper(x.left, a, in);
	    }
	    a[in]=x.data;
	    return in+1;
	}
	
	private Node rotateRight(Node h)
	 {
		if(h.left==null)
		{
			return null;
		}
	 Node temp=h.left;
	 h.left=temp.right;
	 temp.right=h;
	 return temp;
	 }
		
	private Node rotateLeft(Node h)
	 {
		if(h.right==null)
		{
			return null;
		}
	 Node temp=h.right;
	 h.right=temp.left;
	 temp.left=h;
	 temp.data=h.data;
	 return temp;
	 }

	public void transformToList() 
	{
		Node temp=cen;
		while(temp!=null)
		{
			while(temp.left!=null)
			{
				temp=rotateRight(temp);
			}
			temp=temp.right;
		}
    }
	
	public void balanceTreeTwo()
    {
		transformToList();
        int M = size+1-(int)Math.pow(2, Math.floor(Math.log(2)/Math.log(size)));
        Node t=cen;
        Node t2=cen;
        for(int i=1; i<M*2; i++)
        {
            if(i%2==1 && t2!=null)
            {
                rotateLeft(t2);
                t2=t2.right;
            }
        }
        int K=(int)Math.floor(Math.log(2)/Math.log(size))-1;
        while(K>1)
        {
            rotateLeft(cen);
            K--;
        }
        if(K==1)
        {
        	t=rotateLeft(t);
        }
        cen=t;  	
    }

	public static void main(String[] args)
    {         
		//Create BST and add array and elements
		BST b = new BST();
        int[]arr= {53, 4, 14, 22, 16, 304};
        b.put(100);
        b.put(68);
        b.put(98);
        b.put(33);
        b.put(arr);
        
        //Resturn Size
        System.out.println(returnSize());
        
        //Search
        System.out.println(b.search(542));
        
        //Output Sorted Tree
        int[] res;
        res=b.sortedTree();
        for(int i=0;i<res.length;i++)
        {
        	System.out.println(res[i]);
        }
    }
}