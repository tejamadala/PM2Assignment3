package Problem1; 

public class DaryHeap   
{
    private int[] h;
    private int child;
    private int dim;
    
    public DaryHeap(int d)
    {
        h = new int[10];
        dim = 0;
        child = d;
        for(int i=0;i<h.length;i++)
        {
        	h[i]=0;
        }
    }
   
    private int par(int x) 
    {
    	int p;
    	p=(x-1)/child;
        return p;
    }
 
    private int childs(int x, int y) 
    {
    	int c;
    	c=y+x*child;
        return c;
    }
    
    public void insert(int k)
    {
    	if(h.length==(dim+1))
    	{
    		doubleArray();
    	}
        h[dim++]=k;
        Swim(dim-1);
    }
    
    private void Swim(int k)
    {
        int n=h[k];    
        while(k>0 && n<h[par(k)])
        {
            h[k]=h[par(k)];
            k=par(k);
        }                   
        h[k]=n;
    }
    
    public int delMax()
    {
    	int in=0;
    	int max=h[0];
    	for(int i=0;i<h.length;i++)
    	{
    		if(h[i]>max)
    		{
    			max=h[i];
    			in=i;
    		}
    	}
    	delhelper(in);
    	return max;
    }
 
    public int delhelper(int in)
    {
        if (dim==0)
        {
            System.out.println("Empty heap");
        }
        int toDel=h[in];
        h[in]=h[dim-1];
        dim--;
        Sink(in);        
        return toDel;
    }

    private void Sink(int k)
    {
        int in=h[k];
        int x;
        while (childs(k,1)<dim)
        {
        	int n=2;
            int a=childs(k,n);
            int c=childs(k,1);
            while ((a<dim) && (n<=child)) 
            {
                if (h[a]<h[c]) 
                {
                    c=a;
                }
                a=childs(k,n+1);
            }    
            x=c;
            if (h[x]<in)
            {
                h[k]=h[x];
            }
            else
            {
                break;
            }
            k=x;
        }
        h[k]=in;
    }
    
    public int[] daryHeapsort()
    { 
    	int x=dim/2-1;
        while (x>=0) 
        {
            helpersort(dim,x);
            x--;
        }
        x=dim-1;
        int temp;
        while(x>=0) 
        { 
            temp=h[0]; 
            h[0]=h[x]; 
            h[x]=temp; 
            helpersort(x,0); 
            x--;
        }
        
        int[] out= new int[dim];
        for(int z=0; z<dim; z++)
        {
        	out[z]=h[z];
        }
        return out;
    }

    void helpersort(int x, int d) 
    { 
        int l=1+d*2;
        int r=2+d*2;
        int d1=d;
        if(l<x && (h[l]>h[d1])) 
        {
        	d1=l;
        }
        if(r<x && (h[r]>h[d1]))
        {
            d1=r; 
        }
        int temp;
        if(d1!=d) 
        { 
            temp=h[d]; 
            h[d]=h[d1]; 
            h[d1]=temp; 
            helpersort(x,d1); 
        } 
    }

    public void doubleArray()
    {
    	int[] doub=new int[h.length*2];
    	for(int i=0;i<h.length;i++)
    	{
    		doub[i]=h[i];
    	}
    	h=doub;
    }
    
    public static void main(String[] args)
    {
    	//Start Heap and add elements
    	DaryHeap d=new DaryHeap(3);
    	d.insert(43);
    	d.insert(6);
    	d.insert(17);
    	d.insert(2);
    	d.insert(19);
    	d.insert(75);
    	d.insert(1);
    	d.insert(22);
    	
    	//Delete Max
    	System.out.println("the deleted max is "+d.delMax());
    	
    	//Output DaryHeapSort
    	int[] res;
    	res=d.daryHeapsort();
    	for(int i=0; i<res.length; i++)
    	{
    		System.out.println(res[i]);
    	}
    }
}